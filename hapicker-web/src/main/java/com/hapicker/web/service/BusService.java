package com.hapicker.web.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hapicker.common.util.HttpUtils;
import com.hapicker.web.vo.ScheduleBusVO;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 汽车票查询服务
 * @author yuyufeng
 * @date 2018/12/6.
 */
@Service
public class BusService {

    public List<ScheduleBusVO> queryByBababus(String begin, String end, String date) throws Exception {
        int page = 1;
        List<ScheduleBusVO> ServiceBusDetailDTOList = new ArrayList<>();
        Map<String, String> querys = new HashMap<>();
        querys.put("type", "ticket");
        querys.put("from", begin);
        querys.put("to", end);
        querys.put("date", date);
        String host = "http://bus.bababus.com";
        String path = "/ticket/ticketList.htm";
        Map<String, String> headers = new HashMap<>();
        boolean hasNextPage = true;
        while (hasNextPage) {

            hasNextPage = false;
            HttpResponse response = HttpUtils.doGet(host, path, "GET", headers, querys);
            String result = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSONObject.parseObject(result);
            try {
                //查出所有页数
                JSONArray jsonArray = jsonObject.getJSONObject("content").getJSONArray("busNumberList");
                if (jsonArray != null && jsonArray.size() >= 10) {
                    hasNextPage = true;
                    querys.put("page", ++page + "");
                }
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject innerObject = jsonArray.getJSONObject(i);
                    ScheduleBusVO scheduleBus = new ScheduleBusVO();
                    scheduleBus.setBusDate(innerObject.getString("leaveDate"));
                    scheduleBus.setBusTime(innerObject.getString("leaveTime"));
                    scheduleBus.setBusType(innerObject.getString("vehicleMode"));
                    scheduleBus.setDeparture(begin);
                    scheduleBus.setDepartureStation(innerObject.getString("beginStationName"));
                    scheduleBus.setDestination(innerObject.getString("endStationName"));
                    scheduleBus.setTicketPrice(innerObject.getString("fullPrice"));
                    scheduleBus.setTicketLeft(innerObject.getString("remainSeat"));
                    scheduleBus.setBusNo(innerObject.getString("busId"));
                    scheduleBus.setDataFrom("http://bus.bababus.com");
                    ServiceBusDetailDTOList.add(scheduleBus);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return ServiceBusDetailDTOList;
    }

}
