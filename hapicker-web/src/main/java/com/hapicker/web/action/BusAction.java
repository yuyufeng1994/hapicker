package com.hapicker.web.action;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.constant.SessionConstant;
import com.hapicker.common.dto.*;
import com.hapicker.common.service.BusServices;
import com.hapicker.common.vo.ScheduleBusVO;
import com.hapicker.web.client.BusWarningClient;
import com.hapicker.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 汽车票服务相关
 *
 * @author yuyufeng
 * @date 2018/12/6.
 */
@Controller
public class BusAction {

    @Autowired
    private BusWarningClient busWarningClient;

    @Autowired
    private SessionUtil sessionUtil;

    /**
     * 跳转到汽车票页面
     *
     * @return
     */
    @RequestMapping(value = "bus/index")
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
    @RequestMapping(value = "bus/querySchedule")
    public @ResponseBody
    ResponseJson querySchedule(@RequestBody ScheduleBusVO scheduleBusVO) throws Exception {
        try {
            List<ScheduleBusVO> result = BusServices.queryByBababus(scheduleBusVO.getDeparture(), scheduleBusVO.getDestination(), scheduleBusVO.getBusDate());
            return ResponseJson.success(result);
        } catch (Exception e) {
            return ResponseJson.fail("车次未找到");
        }
    }

    @RequestMapping(value = "bus/insertWarningBus")
    public @ResponseBody
    ResponseJson<Integer> insertWarningBus(@RequestBody BusWarningInfoDTO busWarningInfoDTO, HttpServletRequest request) throws Exception {
        try {
            UserInfoDTO userInfoDTO = (UserInfoDTO) sessionUtil.getSession(request, SessionConstant.SESSION_USER);
            if (userInfoDTO == null) {
                return ResponseJson.fail("请先登录");
            }
            busWarningInfoDTO.setUserId(userInfoDTO.getUserId());
            ResponseDTO responseDTO = busWarningClient.insert(busWarningInfoDTO);
            if (!responseDTO.getSuccess()) {
                return ResponseJson.fail(responseDTO.getMsg());
            }
            return ResponseJson.success("车次监听成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseJson.fail("车次监听失败");
        }
    }

    @RequestMapping(value = "user/{userId}/bus/warningBusList/{pageNo}")
    public String warningBusList(@PathVariable("userId") Integer userId, @PathVariable("pageNo") Integer pageNo, Model model) throws Exception {
        BusWarningInfoDTO busWarningInfoDTO = new BusWarningInfoDTO();
        busWarningInfoDTO.setUserId(userId);
        RequestPageDTO requestPageDTO = new RequestPageDTO<>(pageNo, busWarningInfoDTO, "create_time desc");
        ResponseDTO<PageInfo<BusWarningInfoDTO>> responseDTO = busWarningClient.query(requestPageDTO);
        PageInfo<BusWarningInfoDTO> pageInfo = responseDTO.getContent();
        model.addAttribute("page", pageInfo);
        model.addAttribute("pageUrl", "/user/" + userId + "/bus/warningBusList/");
        return "user/bus/list";
    }


}
