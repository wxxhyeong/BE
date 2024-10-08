package com.be.finance.service;


import com.be.finance.domain.ProductVO;
import com.be.finance.domain.SavingProductRatesVO;
import com.be.finance.domain.SavingProductVO;
import com.be.finance.mapper.ProductMapper;
import com.be.finance.mapper.SavingProductMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SavingProductService {

    @Autowired
    private SavingProductMapper savingProductMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.depositProductUrl}")
    private String depositProductApiUrl;

    @Value("${api.savingProductUrl}")
    private String savingProductApiUrl;

    @Value("${api.authKey}")
    private String authKey;

    // 금융감독원 예금 API에서 데이터 Fetch
    public void fetchAndSaveDepositProducts() {
        // API 호출 URL
        String url = depositProductApiUrl + "?auth=" + authKey + "&topFinGrpNo=020000" + "&pageNo=1";
        System.out.println(url);

        try {
            // RestTemplate로 API 호출
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

            // 응답 상태 코드 확인
            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("API 호출 실패: 상태 코드 " + responseEntity.getStatusCode());
            }

            String responseJson = responseEntity.getBody();

            // null 체크를 수행하여 응답이 없을 경우 오류 처리
            if (responseJson == null) {
                throw new RuntimeException("API 응답이 null입니다.");
            }

            // org.json.JSONObject를 이용해 JSON 응답을 파싱
            JSONObject jsonResponse = new JSONObject(responseJson);

            // "result" 부분 추출
            if (!jsonResponse.has("result")) {
                throw new RuntimeException("API 응답에서 'result' 필드를 찾을 수 없습니다.");
            }

            JSONObject result = jsonResponse.getJSONObject("result");

            // "baseList"와 "optionList" 추출
            JSONArray baseList = result.getJSONArray("baseList");
            JSONArray optionList = result.getJSONArray("optionList");

            // 상품 처리 로직
            processBaseAndOptionLists(baseList, optionList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("API 호출 중 오류 발생: " + e.getMessage());
        }
    }


    // 금융감독원 적금 API에서 데이터 Fetch
    public void fetchAndSaveSavingProducts() {
        // API 호출 URL
        String url = savingProductApiUrl + "?auth=" + authKey + "&topFinGrpNo=020000" + "&pageNo=1";
        System.out.println(url);

        try {
            // RestTemplate로 API 호출
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

            // 응답 상태 코드 확인
            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("API 호출 실패: 상태 코드 " + responseEntity.getStatusCode());
            }

            String responseJson = responseEntity.getBody();

            // null 체크를 수행하여 응답이 없을 경우 오류 처리
            if (responseJson == null) {
                throw new RuntimeException("API 응답이 null입니다.");
            }

            // org.json.JSONObject를 이용해 JSON 응답을 파싱
            JSONObject jsonResponse = new JSONObject(responseJson);

            // "result" 부분 추출
            if (!jsonResponse.has("result")) {
                throw new RuntimeException("API 응답에서 'result' 필드를 찾을 수 없습니다.");
            }

            JSONObject result = jsonResponse.getJSONObject("result");

            // "baseList"와 "optionList" 추출
            JSONArray baseList = result.getJSONArray("baseList");
            JSONArray optionList = result.getJSONArray("optionList");

            // 상품 처리 로직
            processBaseAndOptionLists(baseList, optionList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("API 호출 중 오류 발생: " + e.getMessage());
        }
    }

    // 상품 처리 메소드
    private void processBaseAndOptionLists(JSONArray baseList, JSONArray optionList) {
        for (int i = 0; i < baseList.length(); i++) {
            JSONObject baseProductJson = baseList.getJSONObject(i);

            // 1. 금융 상품(Product 테이블)에 데이터 저장
            ProductVO productVO = new ProductVO();
            productVO.setProductType('S');
            productMapper.insertProduct(productVO);

            int savedProductId = productVO.getProductId();

            // 2. 예/적금 상품(SavingProduct 테이블)에 저장
            SavingProductVO savingProductVO = new SavingProductVO();
            savingProductVO.setProductId(savedProductId);
            savingProductVO.setFinPrdtNm(baseProductJson.getString("fin_prdt_nm"));
            savingProductVO.setFinPrdtCd(baseProductJson.getString("fin_prdt_cd"));
            savingProductVO.setFinCoNo(baseProductJson.getString("fin_co_no"));
            savingProductVO.setKorCoNm(baseProductJson.getString("kor_co_nm"));
            savingProductVO.setFinPrdtNm(baseProductJson.getString("fin_prdt_nm"));
            savingProductVO.setDclsMonth(baseProductJson.getString("dcls_month"));
            savingProductVO.setJoinWay(baseProductJson.getString("join_way"));
            savingProductVO.setMtrtInt(baseProductJson.getString("mtrt_int"));
            savingProductVO.setSpclCnd(baseProductJson.getString("spcl_cnd"));
            savingProductVO.setJoinDeny(baseProductJson.getInt("join_deny"));
            savingProductVO.setJoinMember(baseProductJson.getString("join_member"));
            savingProductVO.setEtcNote(baseProductJson.getString("etc_note"));
            savingProductVO.setMaxLimit(!baseProductJson.isNull("max_limit") ? baseProductJson.getInt("max_limit") : null);
            savingProductVO.setDclsStrtDay(baseProductJson.getString("dcls_strt_day"));
            savingProductVO.setDclsEndDay(baseProductJson.isNull("dcls_end_day") ? null : baseProductJson.getString("dcls_end_day"));

            // 예/적금 상품 저장
            savingProductMapper.insertSavingProduct(savingProductVO);


            // 3. 기간별 수익률(SavingProductRates 테이블)에 데이터 저장
            for (int j = 0; j < optionList.length(); j++) {
                JSONObject optionProductJson = optionList.getJSONObject(j);

                if (optionProductJson.getString("fin_prdt_cd").equals(baseProductJson.getString("fin_prdt_cd"))) {
                    SavingProductRatesVO rateVO = new SavingProductRatesVO();
                    rateVO.setProductId(savedProductId);
                    rateVO.setSaveTrm(optionProductJson.getInt("save_trm"));
                    rateVO.setIntrRateType(optionProductJson.getString("intr_rate_type").charAt(0));
                    rateVO.setIntrRateTypeNm(optionProductJson.getString("intr_rate_type_nm"));  // 금리 유형명
                    rateVO.setIntrRate(optionProductJson.isNull("intr_rate") ? null : optionProductJson.getBigDecimal("intr_rate")); // intr_rate 처리
                    rateVO.setIntrRate2(optionProductJson.isNull("intr_rate2") ? null : optionProductJson.getBigDecimal("intr_rate2")); // intr_rate2 처리
                    // rsrv_type과 rsrv_type_nm 처리
                    rateVO.setRsrvType(optionProductJson.has("rsrv_type") && !optionProductJson.isNull("rsrv_type")
                            ? optionProductJson.getString("rsrv_type")
                            : "null"); // rsrv_type 처리 (String)
                    rateVO.setRsrvTypeNm(optionProductJson.has("rsrv_type_nm") && !optionProductJson.isNull("rsrv_type_nm")
                            ? optionProductJson.getString("rsrv_type_nm")
                            : "null"); // rsrv_type_nm 처리 (String)

                    savingProductMapper.insertSavingProductRate(rateVO);
                }
            }
        }
    }

    // 예금 리스트
    public Map<String, Object> getDepositProducts() {
        // 예금 상품 조회
        List<SavingProductVO> depositProducts = savingProductMapper.getDepositProducts();

        // 예금 상품 기간별 금리 조회
        List<SavingProductRatesVO> depositRates = savingProductMapper.getDepositRates();

        // 결과를 담을 Map 생성
        Map<String, Object> result = new HashMap<>();
        result.put("products", depositProducts);
        result.put("rates", depositRates);
        return result;
    }

    // 적금 상품 조회 (WHERE 조건에 따라 적금 상품만 조회)
    public Map<String, Object> getSavingProducts() {
        // 적금 상품 조회
        List<SavingProductVO> savingProducts = savingProductMapper.getSavingProducts();
        // 적금 상품 기간별 금리 조회
        List<SavingProductRatesVO> savingRates = savingProductMapper.getSavingRates();

        // 결과를 담을 Map 생성
        Map<String, Object> result = new HashMap<>();
        result.put("products", savingProducts);
        result.put("rates", savingRates);
        return result;
    }
}
