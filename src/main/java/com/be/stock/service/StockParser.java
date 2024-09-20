package com.be.stock.service;

import com.be.stock.domain.StockVO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;

public class StockParser {
    public ArrayList<StockVO> parse() {
        // 발급받은 Decoding 발급키 (serviceKey)
        String serviceKey = "YOUR_DECODING_SERVICE_KEY";

        // API 요청 URL 및 파라미터 설정
        String url = "https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo";
        String parameter = "?serviceKey=" + serviceKey +
                "&numOfRows=10" +   // 한번에 10개의 데이터를 요청
                "&pageNo=1";        // 페이지 번호 1


        //http요청 ->응답(xml)
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url+parameter, String.class);

        //xml -> json 변경
        JSONObject json = XML.toJSONObject(response);
        System.out.println(json);

        //json분석 후 추출(parser, 파서)
        //json바깥쪽부터 안으로 들어오면서 추출을 함.
        JSONObject getStockPriceInfo=json.getJSONObject("getStockPriceInfo");
        JSONArray arr = getStockPriceInfo.getJSONArray("stock");

        // 응답 구조에 맞춰 수정
        JSONObject responseObject = json.getJSONObject("response");
        JSONObject body = responseObject.getJSONObject("body");
        JSONArray items = body.getJSONArray("items");

        ArrayList<StockVO> list = new ArrayList<>();



        for (int i=0; i<arr.length(); i++){
            StockVO vo = new StockVO();
            vo.setClpr(arr.getJSONObject(i).getInt("clpr"));
            vo.setHipr(arr.getJSONObject(i).getInt("hipr"));
            vo.setStockCode(arr.getJSONObject(i).getString("stockCode"));
            vo.setStockName(arr.getJSONObject(i).getString("stockName"));
            vo.setDailyPrice(arr.getJSONObject(i).getBigDecimal("dailyPrice"));
            vo.setField(arr.getJSONObject(i).getInt("field"));
            vo.setMrktCtg(arr.getJSONObject(i).getString("mrktCtg"));
            vo.setVs(arr.getJSONObject(i).getInt("vs"));
            vo.setFltRt(arr.getJSONObject(i).getBigDecimal("fltRt"));
            vo.setMkp(arr.getJSONObject(i).getInt("mkp"));
            vo.setLopr(arr.getJSONObject(i).getInt("lopr"));
            vo.setTrqu(arr.getJSONObject(i).getBigInteger("trqu"));
            vo.setTrPrc(arr.getJSONObject(i).getBigInteger("trPrc"));
            vo.setIstgStCnt(arr.getJSONObject(i).getBigInteger("istgStCnt"));
            vo.setMrktTotAmt(arr.getJSONObject(i).getBigInteger("mrktTotAmt"));
        }
        return list;
    }
}
