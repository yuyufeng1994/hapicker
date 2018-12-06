package com.hapicker.web.action;

import com.hapicker.common.dto.ResponseJson;
import com.hapicker.web.service.BusService;
import com.hapicker.web.vo.ScheduleBusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 汽车票服务相关
 *
 * @author yuyufeng
 * @date 2018/12/6.
 */
@Controller
@RequestMapping("bus")
public class BusAction {

    @Autowired
    private BusService busService;

    /**
     * 跳转到汽车票页面
     *
     * @return
     */
    @RequestMapping(value = "index")
    public String busIndex() {
        return "bus/index";
    }

    @RequestMapping(value = "querySchedule")
    public @ResponseBody
    ResponseJson querySchedule(@RequestBody ScheduleBusVO scheduleBusVO) throws Exception {
        try {
            List<ScheduleBusVO> result = busService.queryByBababus(scheduleBusVO.getDeparture(), scheduleBusVO.getDestination(), scheduleBusVO.getBusDate());
            return ResponseJson.success(result);
        } catch (Exception e) {
            return ResponseJson.fail("车次未找到");
        }

    }

}
