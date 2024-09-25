package com.be.stock.service;

import com.be.stock.domain.StockVO;
import com.be.stock.dto.StockDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class StockDataFetcher {

    @Value("${stock.api.url}")
    private String apiUrl;

    @Value("${stock.api.key}")
    private String apiKey;

    // 주식 데이터를 API에서 가져와 파싱하는 메서드
    public List<StockVO> fetchStockData() {
        String parameter = "?serviceKey=" + apiKey + "&numOfRows=10&pageNo=1";
        List<StockVO> stockList = new ArrayList<>();

        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(apiUrl + parameter, String.class);

            // API 응답 데이터 확인
            System.out.println("API 응답: " + response);

            JSONObject json = XML.toJSONObject(response);
            if (json.has("response")) {
                JSONObject responseObject = json.getJSONObject("response");
                JSONObject body = responseObject.getJSONObject("body");
                JSONArray items = body.getJSONArray("items");

                for (int i = 0; i < items.length(); i++) {
                    StockDTO dto = new StockDTO();
                    JSONObject item = items.getJSONObject(i);

                    dto.setClpr(item.optInt("clpr", 0));
                    dto.setHipr(item.optInt("hipr", 0));
                    dto.setStockCode(item.optString("stockCode", "N/A"));
                    dto.setStockName(item.optString("stockName", "N/A"));
                    dto.setDailyPrice(item.optBigDecimal("dailyPrice", BigDecimal.ZERO));
                    dto.setField(item.optInt("field", 0));
                    dto.setMrktCtg(item.optString("mrktCtg", "N/A"));
                    dto.setVs(item.optInt("vs", 0));
                    dto.setFltRt(item.optBigDecimal("fltRt", BigDecimal.ZERO));
                    dto.setMkp(item.optInt("mkp", 0));
                    dto.setLopr(item.optInt("lopr", 0));
                    dto.setTrqu(item.optBigInteger("trqu", BigInteger.ZERO));
                    dto.setTrPrc(item.optBigInteger("trPrc", BigInteger.ZERO));
                    dto.setIstgStCnt(item.optBigInteger("istgStCnt", BigInteger.ZERO));
                    dto.setMrktTotAmt(item.optBigInteger("mrktTotAmt", BigInteger.ZERO));

                    stockList.add(dtoToVo(dto));  // DTO -> VO 변환 후 리스트에 추가
                }
            } else {
                System.out.println("API 응답에 'response' 키가 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("API 호출 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }

        return stockList;
    }

    // DTO -> VO 변환 메서드
    private StockVO dtoToVo(StockDTO dto) {
        StockVO vo = new StockVO();
        vo.setClpr(dto.getClpr());
        vo.setHipr(dto.getHipr());
        vo.setStockCode(dto.getStockCode());
        vo.setStockName(dto.getStockName());
        vo.setDailyPrice(dto.getDailyPrice());
        vo.setField(dto.getField());
        vo.setMrktCtg(dto.getMrktCtg());
        vo.setVs(dto.getVs());
        vo.setFltRt(dto.getFltRt());
        vo.setMkp(dto.getMkp());
        vo.setLopr(dto.getLopr());
        vo.setTrqu(dto.getTrqu());
        vo.setTrPrc(dto.getTrPrc());
        vo.setIstgStCnt(dto.getIstgStCnt());
        vo.setMrktTotAmt(dto.getMrktTotAmt());
        return vo;
    }
}
