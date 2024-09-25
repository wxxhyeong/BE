package com.be.stock.service;

import com.be.stock.domain.StockVO;
import com.be.stock.mapper.StockMapper;
import com.be.stock.repository.StockRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    private final OkHttpClient client = new OkHttpClient();

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.StockUrl}")
    private String StockUrl;

    @Value("${api.StockKey}")
    private String StockKey;


    // 주식시세 fetch
    public void fetchStock() {


        // API 호출 URL
        String url = StockUrl + "?serviceKey=" + StockKey + "&numOfRows=30&pageNo=1&resultType=xml"; // 결과 형식을 xml로 요청
        System.out.println("API 호출 URL: " + url);

        //OkHttp 요청 구성
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Cookie", "SCOUTER=x3qrmbd76uuavd")
                .build();

        try {

            //OkHttp요청실행
            Response reponse = client.newCall(request).execute();

            //응답 내용 확인
            String responseBody = reponse.body().string();



            // 응답이 XML 형식일 경우 XML을 JSON으로 변환
            JSONObject json = XML.toJSONObject(responseBody);
            System.out.println("XML 변환된 JSON: " + json);

            // null 체크를 수행하여 응답이 없을 경우 오류 처리
            if (responseBody == null) {
                throw new RuntimeException("API 응답이 null입니다.");
            }


               //JSON파싱
            if (json.has("response")) {
                JSONObject responseObject = json.getJSONObject("response");
                JSONObject body = responseObject.getJSONObject("body");
                JSONArray items = body.getJSONObject("items").getJSONArray("item");
                List<StockVO> stockList = new ArrayList<>();

                // 각 item 파싱 후 StockVO 객체로 변환하여 리스트에 추가
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    StockVO vo = new StockVO();

                    vo.setClpr(item.optInt("clpr", 0));
                    vo.setHipr(item.optInt("hipr", 0));
                    vo.setStockCode(item.optString("srtnCd"));  // stockCode는 srtnCd로 설정
                    vo.setStockName(item.optString("itmsNm"));  // stockName은 itmsNm으로 설정
                    vo.setMrktCtg(item.optString("mrktCtg"));
                    vo.setDailyPrice(item.optBigDecimal("dailyPrice", BigDecimal.ZERO));  // dailyPrice 필드
                    vo.setField(item.optInt("field", 0));  // field 필드
                    vo.setVs(item.optInt("vs", 0));
                    vo.setFltRt(item.optBigDecimal("fltRt", BigDecimal.ZERO));
                    vo.setMkp(item.optInt("mkp", 0));
                    vo.setLopr(item.optInt("lopr", 0));
                    vo.setTrqu(item.optBigInteger("trqu", BigInteger.ZERO));
                    vo.setTrPrc(item.optBigInteger("trPrc", BigInteger.ZERO));
                    vo.setIstgStCnt(item.optBigInteger("lstgStCnt", BigInteger.ZERO));
                    vo.setMrktTotAmt(item.optBigInteger("mrktTotAmt", BigInteger.ZERO));

                    stockList.add(vo);  // 리스트에 추가
                }

                // DB에 저장
                for (StockVO stockVO : stockList) {
                    stockRepository.insert(stockVO);
                }

                System.out.println("주식 데이터가 성공적으로 저장되었습니다.");

            } else {
                System.out.println("API 응답에 'response' 키가 없습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("API 호출 중 오류 발생: " + e.getMessage());
        }
    }
}
