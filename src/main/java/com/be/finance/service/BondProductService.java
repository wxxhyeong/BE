package com.be.finance.service;

import com.be.finance.domain.BondProductVO;
import com.be.finance.domain.ProductVO;
import com.be.finance.mapper.BondProductMapper;
import com.be.finance.mapper.ProductMapper;
import lombok.extern.log4j.Log4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Log4j
public class BondProductService {

    @Autowired
    private BondProductMapper bondProductMapper;

    @Autowired
    private PaginationService paginationService;

    @Autowired
    private ProductMapper productMapper;

    @Value("${api.bondProductUrl}")
    private String bondProductApiUrl;

    @Value("${api.bondAuthKey}")
    private String bondAuthKey;

    @Value("${api.bondProductPriceUrl}")
    private String bondProductPriceApiUrl;

    @Value("${api.bondPriceAuthKey}")
    private String bondPriceAuthKey;

    // OkHttpClient에 타임아웃 설정 추가
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)  // 연결 타임아웃 설정
            .writeTimeout(30, TimeUnit.SECONDS)    // 쓰기 타임아웃 설정
            .readTimeout(60, TimeUnit.SECONDS)     // 읽기 타임아웃 설정
            .build();

    public void fetchAndSaveBondProducts() {
        int totalPages = 870; // 총 10페이지
        int numOfRows = 30; // 1페이지당 30개의 데이터
//        LocalDate currentDate = LocalDate.now();
//        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
//
//        LocalDate previousBusinessDay;
//        if (dayOfWeek == DayOfWeek.MONDAY) {
//            previousBusinessDay = currentDate.minusDays(3);
//        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
//            previousBusinessDay = currentDate.minusDays(2);
//        } else {
//            previousBusinessDay = currentDate.minusDays(1);
//        }
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        String formattedDate = previousBusinessDay.format(formatter);

        for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
            // 요청 URL
            String url = bondProductApiUrl + "?serviceKey=" + bondAuthKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&resultType=json" + "&pageSize=" + numOfRows + "&basDt=20241004";
            System.out.println("Fetching page: " + pageNo + "form URL: " + url);

            // OkHttp 요청 구성
            Request request = new Request.Builder()
                    .url(url)
                    .get() // GET 요청
                    .addHeader("Cookie", "SCOUTER=x7nhrjlt939t2f")
                    .build();
            try {
                // OkHttp 요청 실행
                Response response = client.newCall(request).execute();

                if (!response.isSuccessful() || response.body() == null) {
                    throw new RuntimeException("API 호출 실패 : 상태 코드 : " + response.code());
                }

                // 응답 내용 확인
                String responseJson = response.body().string();
                System.out.println("API 응답 데이터 : " + responseJson);

                // 응답 예외 처리
                if (responseJson == null || responseJson.isEmpty()) {
                    throw new RuntimeException("API 응답이 없습니다.");
                }

                // JSON 파싱
                JSONObject jsonResponse = new JSONObject(responseJson);  // JSON 변환 시도
                JSONObject responseObj = jsonResponse.getJSONObject("response");
                JSONObject body = responseObj.getJSONObject("body");
                JSONArray items = body.getJSONObject("items").getJSONArray("item");

//                // DB 저장 메소드 호출
                processBondItems(items);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("API 호출 중 오류 발생 : " + e.getMessage());
            }
        }
    }

    public void fetchAndSaveBondProductPrices() {
        int totalPages = 14; // 총 10페이지
        int numOfRows = 30; // 1페이지당 30개의 데이터
//        LocalDate currentDate = LocalDate.now();
//        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
//
//        LocalDate previousBusinessDay;
//        if (dayOfWeek == DayOfWeek.MONDAY) {
//            previousBusinessDay = currentDate.minusDays(3);
//        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
//            previousBusinessDay = currentDate.minusDays(2);
//        } else {
//            previousBusinessDay = currentDate.minusDays(1);
//        }
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        String formattedDate = previousBusinessDay.format(formatter);

        for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
            // 요청 URL
            String url = bondProductPriceApiUrl + "?serviceKey=" + bondPriceAuthKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&resultType=json" + "&basDt=20241004";
            System.out.println("Fetching page: " + pageNo + "form URL: " + url);


            // OkHttp 요청 구성
            Request request = new Request.Builder()
                    .url(url)
                    .get() // GET 요청
                    .addHeader("Cookie", "SCOUTER=x7nhrjlt939t2f")
                    .build();

            try {
                // OkHttp 요청 실행
                Response response = client.newCall(request).execute();

                if (!response.isSuccessful() || response.body() == null) {
                    throw new RuntimeException("API 호출 실패 : 상태 코드 : " + response.code());
                }

                // 응답 내용 확인
                String responseJson = response.body().string();
                System.out.println("API 응답 데이터 : " + responseJson);

                // 응답 예외 처리
                if (responseJson == null || responseJson.isEmpty()) {
                    throw new RuntimeException("API 응답이 없습니다.");
                }

                // JSON 파싱
                JSONObject jsonResponse = new JSONObject(responseJson);  // JSON 변환 시도
                JSONObject responseObj = jsonResponse.getJSONObject("response");
                JSONObject body = responseObj.getJSONObject("body");
                JSONArray items = body.getJSONObject("items").getJSONArray("item");

                // DB 저장 메소드 호출
                processBondItemPrices(items);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("API 호출 중 오류 발생 : " + e.getMessage());
            }
        }
    }



    // 채권 상품 DB 저장 메소드
    private void processBondItems(JSONArray items) {
        for (int i = 0; i < items.length(); i++) {
            JSONObject bondItem = items.getJSONObject(i);
            BondProductVO bondProductVO = new BondProductVO();

            bondProductVO.setBasDt(bondItem.isNull("basDt") ? null : bondItem.getString("basDt"));
            bondProductVO.setCrno(bondItem.isNull("crno") ? null : bondItem.getString("crno"));
            bondProductVO.setScrsItmsKcd(bondItem.isNull("scrsItmsKcd") ? null : bondItem.getString("scrsItmsKcd"));
            bondProductVO.setIsinCd(bondItem.isNull("isinCd") ? null : bondItem.getString("isinCd"));
            bondProductVO.setBondIsurNm(bondItem.isNull("bondIsurNm") ? null : bondItem.getString("bondIsurNm"));
            bondProductVO.setIsinCdNm(bondItem.isNull("isinCdNm") ? null : bondItem.getString("isinCdNm"));
            bondProductVO.setBondIssuDt(bondItem.isNull("bondIssuDt") ? null : bondItem.getString("bondIssuDt"));
            bondProductVO.setBondIssuAmt(bondItem.isNull("bondIssuAmt") ? null : Long.parseLong(bondItem.getString("bondIssuAmt")));
            bondProductVO.setBondIssuCurCd(bondItem.isNull("bondIssuCurCd") ? null : bondItem.getString("bondIssuCurCd"));
            bondProductVO.setBondIssuCurCdNm(bondItem.isNull("bondIssuCurCdNm") ? null : bondItem.getString("bondIssuCurCdNm"));
            bondProductVO.setBondExprDt(bondItem.isNull("bondExprDt") ? null : bondItem.getString("bondExprDt"));
            bondProductVO.setBondPymtAmt(bondItem.isNull("bondPymtAmt") ? null : Long.parseLong(bondItem.getString("bondPymtAmt")));

            // char 형 처리: null 값일 경우 'N'으로 기본 설정
            bondProductVO.setIrtChngDcd(bondItem.isNull("irtChngDcd") || bondItem.getString("irtChngDcd").isEmpty()
                    ? 'N' : bondItem.getString("irtChngDcd").charAt(0));

            bondProductVO.setIrtChngDcdNm(bondItem.isNull("irtChngDcdNm") ? null : bondItem.getString("irtChngDcdNm"));

            // BigDecimal 처리: String으로 받아와 BigDecimal로 변환
            bondProductVO.setBondSrfcInrt(bondItem.isNull("bondSrfcInrt") ? null : new BigDecimal(bondItem.getString("bondSrfcInrt")));

            bondProductVO.setBondIntTcd(bondItem.isNull("bondIntTcd") || bondItem.getString("bondIntTcd").isEmpty()
                    ? 'N' : bondItem.getString("bondIntTcd").charAt(0));

            bondProductVO.setBondIntTcdNm(bondItem.isNull("bondIntTcdNm") ? null : bondItem.getString("bondIntTcdNm"));
            bondProductVO.setIntPayCyclCtt(bondItem.isNull("intPayCyclCtt") ? null : bondItem.getString("intPayCyclCtt"));
            bondProductVO.setNxtmCopnDt(bondItem.isNull("nxtmCopnDt") ? null : bondItem.getString("nxtmCopnDt"));
            bondProductVO.setKbpScrsItmsKcdNm(bondItem.isNull("kbpScrsItmsKcdNm") ? null : bondItem.getString("kbpScrsItmsKcdNm"));
            bondProductVO.setNiceScrsItmsKcdNm(bondItem.isNull("niceScrsItmsKcdNm") ? null : bondItem.getString("niceScrsItmsKcdNm"));
            bondProductVO.setFnScrsItmsKcdNm(bondItem.isNull("fnScrsItmsKcdNm") ? null : bondItem.getString("fnScrsItmsKcdNm"));
//            bondProductVO.setHit(0); // 조회수 기본값

            // 데이터베이스에 저장
            bondProductMapper.updateBondProductPrice(bondProductVO);
        }
    }

    // 채권 가격 DB 저장 메소드
    private void processBondItemPrices(JSONArray items) {
        for (int i = 0; i < items.length(); i++) {
            JSONObject bondItem = items.getJSONObject(i);

            // 1. Product 테이블 저장
            ProductVO productVO = new ProductVO();
            productVO.setProductType('B');
            productMapper.insertProduct(productVO);

            int productId = productVO.getProductId();

            // 채권 종가 데이터 추출
            BondProductVO bondProductVO = new BondProductVO();
            bondProductVO.setProductId(productId);
            bondProductVO.setIsinCd(bondItem.isNull("isinCd") ? null : bondItem.getString("isinCd"));
            bondProductVO.setClprPrc(bondItem.isNull("clprPrc") ? 0 : Double.parseDouble(bondItem.getString("clprPrc")));

            bondProductMapper.insertBondProduct(bondProductVO);
        }
    }

    // 전체 채권 리스트 조회
    public Map<String, Object> getBondProductsList(int page, int pageSize) {
        List<BondProductVO> bondproducts = bondProductMapper.getBondProductsList();
        return paginationService.paginate(bondproducts, page, pageSize);
    }

    // 채권 상품 검색
    public Map<String, Object> searchBondProducts(String keyword, int page, int pageSize) {
        // 검색어가 포함된 상품명 검색
        String searchKeyword = "%" + keyword + "%";
        List<BondProductVO> bondProducts = bondProductMapper.searchBondProducts(searchKeyword);
        return paginationService.paginate(bondProducts, page, pageSize);
    }

    // 특정 채권 상품 상세 정보 조회
    public BondProductVO getBondProductDetail(int productId) {
        return bondProductMapper.getBondProductDetail(productId);
    }

    // 채권 기본 정보가 Null인 상품 제거
    public void deleteNullProduct() {
        String deletedIds = bondProductMapper.getNullProductIds();
        List<Integer> deletedIdList = Arrays.stream(deletedIds.split(","))
                .map(Integer::parseInt)
                .toList();

        bondProductMapper.deleteNullProduct();
        for(int deletedId: deletedIdList) {
            productMapper.deleteProduct(deletedId);
        }
    }
}
