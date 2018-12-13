package com.hapicker.common.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hapicker.common.dto.TrainInfoDTO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author yuyufeng
 * @date 2018/12/13.
 */
public class TrainServices {
    private final static Logger LOG = LoggerFactory.getLogger(TrainServices.class);
    private static String host = "http://huoche.tuniu.com";
    private static Properties stationNameIDs;

    static {
        //初始化
        stationNameIDs = new Properties();
        try {
            String file = TrainServices.class.getResource("/data/tuniu_station.properties").getFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fileInputStream, "utf-8");
            stationNameIDs.load(reader);
            reader.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<TrainInfoDTO> searchTrainList(String begin, String end, String trainDate) throws Exception {
        String beginCode = null;
        String endCode = null;
        try {
            beginCode = stationNameIDs.get(begin).toString();
            endCode = stationNameIDs.get(end).toString();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }


        if (StringUtils.isEmpty(beginCode) || StringUtils.isEmpty(endCode)) {
            throw new Exception("找不到城市CODE");
        }
        String path = "/tn?r=train/trainTicket/getTickets&primary[departureDate]=" + trainDate + "&primary[departureCityCode]=" + beginCode + "&primary[departureCityName]=" + begin + "&primary[arrivalCityCode]=" + endCode + "&primary[arrivalCityName]=" + end + "&start=0&limit=0";
        List<TrainInfoDTO> list = new ArrayList<>(100);
        Map<String, String> headers = new HashMap<String, String>(20);
        headers = new HashMap<>();
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        headers.put("Cache-Control", "no-cache");
        headers.put("Connection", "keep-alive");
        headers.put("Host", "huoche.tuniu.com");
        headers.put("If-Modified-Since", "0");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        headers.put("Referer", "http://huoche.tuniu.com/station_" + beginCode + "_" + endCode);

        LOG.info("开始抓取：" + host + path);
        Connection conn = Jsoup.connect(host + path)
                .followRedirects(true)
                .method(Connection.Method.GET)
                .timeout(5000)
                .ignoreContentType(true)
                .headers(headers);

        Connection.Response response = conn.execute();
        Document doc = response.parse();
        String textResult = doc.body().text();
        LOG.debug(textResult);

        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(textResult);
        } catch (Exception e) {
            LOG.error(begin + end + trainDate + " 车次获取失败" + e.getMessage() + "返回报文：" + textResult);
            throw new Exception("网络可能存在问题，请您重试一下");
        }

        String code = jsonObject.getString("code");
        JSONObject jsonData = jsonObject.getJSONObject("data");
        JSONArray jsonArray = jsonData.getJSONArray("list");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject trainParams = jsonArray.getJSONObject(i);
//            LOG.info(trainParams.toString());
            TrainInfoDTO trainDTO = new TrainInfoDTO();
            trainDTO.seatBD = "";         //商务
            trainDTO.seat1 = "";          //一等座
            trainDTO.seat2 = "";      //二等座
            trainDTO.bedSoftHigh = "";      //高级软卧
            trainDTO.bedSoft = "";      //软卧
            trainDTO.bedMove = "";     //动卧
            trainDTO.bedHard = "";   //硬卧
            trainDTO.seatSoft = "";
            trainDTO.seatHard = "";
            trainDTO.seatNo = "";
            trainDTO.seatOther = "";
            JSONArray prices = trainParams.getJSONArray("prices");
            for (int j = 0; j < prices.size(); j++) {
                JSONObject price = prices.getJSONObject(j);
                String seatName = price.getString("seatName");
                String seatStatus = price.getString("seatStatus");
                if ("一等座".indexOf(seatName) != -1) {
                    if (StringUtils.isEmpty(seatStatus)) {
                        trainDTO.seat1 = price.getString("leftNumber");
                    } else {
                        trainDTO.seat1 = seatStatus;
                    }
                } else if ("二等座".indexOf(seatName) != -1) {
                    if (StringUtils.isEmpty(seatStatus)) {
                        trainDTO.seat2 = price.getString("leftNumber");
                    } else {
                        trainDTO.seat2 = seatStatus;
                    }
                } else if ("商务座".indexOf(seatName) != -1) {
                    if (StringUtils.isEmpty(seatStatus)) {
                        trainDTO.seatBD = price.getString("leftNumber");
                    } else {
                        trainDTO.seatBD = seatStatus;
                    }
                } else if ("无座".indexOf(seatName) != -1) {
                    if (StringUtils.isEmpty(seatStatus)) {
                        trainDTO.seatNo = price.getString("leftNumber");
                    } else {
                        trainDTO.seatNo = seatStatus;
                    }
                } else if ("硬座".indexOf(seatName) != -1) {
                    if (StringUtils.isEmpty(seatStatus)) {
                        trainDTO.seatHard = price.getString("leftNumber");
                    } else {
                        trainDTO.seatHard = seatStatus;
                    }
                } else if ("硬卧".indexOf(seatName) != -1) {
                    if (StringUtils.isEmpty(seatStatus)) {
                        trainDTO.bedHard = price.getString("leftNumber");
                    } else {
                        trainDTO.bedHard = seatStatus;
                    }
                } else if ("软卧".indexOf(seatName) != -1) {
                    if (StringUtils.isEmpty(seatStatus)) {
                        trainDTO.bedSoft = price.getString("leftNumber");
                    } else {
                        trainDTO.bedSoft = seatStatus;
                    }
                } else if ("无座".indexOf(seatName) != -1) {
                    if (StringUtils.isEmpty(seatStatus)) {
                        trainDTO.seatNo = price.getString("leftNumber");
                    } else {
                        trainDTO.seatNo = seatStatus;
                    }
                }
            }

            trainDTO.trainNo = trainParams.getString("trainNum"); //trainParams.getString("");
            trainDTO.startStation = trainParams.getString("");
            trainDTO.lastStation = trainParams.getString("");
            trainDTO.beginStation = trainParams.getString("departStationName");
            trainDTO.endStation = trainParams.getString("destStationName");
            trainDTO.beginTime = trainParams.getString("departDepartTime");    //发车时间
            trainDTO.endTime = trainParams.getString("destArriveTime");   //到达时间
            trainDTO.overTime = trainParams.getString("durationStr");    //历经时间

            list.add(trainDTO);
        }

        LOG.info(begin + end + trainDate + "查询到车次：" + list.size());
        return list;
    }

    public static void main(String[] args) throws Exception {
        List<TrainInfoDTO> list = TrainServices.searchTrainList("杭州", "广州", "2018-12-22");
        for (TrainInfoDTO serviceTrainDTO : list) {
            System.out.println(serviceTrainDTO);
        }
    }
}
