package com.hapicker.web.action;

import com.hapicker.common.constant.BusWarningStatusEnum;
import com.hapicker.common.constant.SessionConstant;
import com.hapicker.common.dto.BusWarningInfoDTO;
import com.hapicker.common.dto.ResponseJson;
import com.hapicker.common.dto.UserInfoDTO;
import com.hapicker.web.client.BusWarningClient;
import com.hapicker.web.service.BusService;
import com.hapicker.web.util.SessionUtil;
import com.hapicker.web.vo.ScheduleBusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private BusWarningClient busWarningClient;

    @Autowired
    private SessionUtil sessionUtil;

    /**
     * 跳转到汽车票页面
     *
     * @return
     */
    @RequestMapping(value = "index")
    public String busIndex() {
        return "bus/index";
    }

    /**
     * 查询汽车票
     *
     * @param scheduleBusVO
     * @return
     * @throws Exception
     */
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

    @RequestMapping(value = "insertWarningBus")
    public @ResponseBody
    ResponseJson insertWarningBus(@RequestBody BusWarningInfoDTO busWarningInfoDTO, HttpServletRequest request) throws Exception {
        try {
            UserInfoDTO userInfoDTO = (UserInfoDTO) sessionUtil.getSession(request, SessionConstant.SESSION_USER);
            if (userInfoDTO == null) {
                return ResponseJson.fail("请先登录!");
            }
            busWarningInfoDTO.setUserId(userInfoDTO.getUserId());
            busWarningClient.insert(busWarningInfoDTO);
            return ResponseJson.success("车次监听成功");
        } catch (Exception e) {
            return ResponseJson.fail("车次监听失败");
        }
    }


}
