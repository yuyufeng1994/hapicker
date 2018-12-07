package com.hapicker.web.action;

import com.alibaba.fastjson.JSONObject;
import com.hapicker.common.constant.SessionConstant;
import com.hapicker.common.constant.StatusEnum;
import com.hapicker.common.constant.UserConnectInfoPlatformEnum;
import com.hapicker.common.dto.ResponseDTO;
import com.hapicker.common.dto.ResponseJson;
import com.hapicker.common.dto.UserConnectInfoDTO;
import com.hapicker.common.dto.UserInfoDTO;
import com.hapicker.common.exception.BaseException;
import com.hapicker.common.util.HttpUtils;
import com.hapicker.common.util.MD5Util;
import com.hapicker.common.util.UUIDUtil;
import com.hapicker.common.util.ValidationUtil;
import com.hapicker.web.client.UserClient;
import com.hapicker.web.util.SessionUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuyufeng
 * @date 2018/8/20.
 */
@Controller
public class LoginAction {
    @Value("${base.salt}")
    private String salt;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOs;

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private UserClient userClient;

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    String login(String returnUrl, Model model, HttpServletRequest httpServletRequest) {
        if (sessionUtil.getSession(httpServletRequest, SessionConstant.SESSION_USER) != null) {
            return "redirect:/index";
        }
        //如果已经登录，则跳转到主页
        model.addAttribute("returnUrl", returnUrl);
        return "login";
    }

    @RequestMapping(value = "logout", method = {RequestMethod.GET, RequestMethod.POST})
    String logout(String returnUrl, Model model, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        //如果已经登录，则跳转到主页
        model.addAttribute("returnUrl", returnUrl);
        sessionUtil.deleteSession(httpServletRequest, httpServletResponse, SessionConstant.SESSION_USER);
        if (!StringUtils.isEmpty(returnUrl)) {
            return "redirect:" + returnUrl;
        }
        return "login";
    }

    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    String doLogin(String userAccount, String userPwd, Boolean rememberMe, String returnUrl, Model model, HttpServletResponse httpServletResponse) {
        model.addAttribute("returnUrl", returnUrl);
        if (!ValidationUtil.checkAccount(userAccount) && ValidationUtil.checkPwd(userPwd)) {
            throw new BaseException(100, "登录数据不正确");
        }

        UserInfoDTO userInfo = new UserInfoDTO();

        try {
            if (ValidationUtil.checkEmail(userAccount)) {
                userInfo.setUserEmail(userAccount);
                userInfo = userClient.getUserInfo(userInfo).getContent();
            } else {
                userInfo.setUserName(userAccount);
                userInfo = userClient.getUserInfo(userInfo).getContent();
            }
            if (!userInfo.getUserPwd().equals(MD5Util.getMd5(salt + userPwd))) {
                model.addAttribute("errorMessage", "密码不正确");
                return "login";
            }
            sessionUtil.setSession(httpServletResponse, userInfo, SessionConstant.SESSION_USER);
//            System.out.println(userInfo);
//            System.out.println(rememberMe);
//            System.out.println(salt);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "用户不存在");
            return "login";
        }

        return "redirect:" + returnUrl;
    }


    @RequestMapping(value = "register", method = RequestMethod.GET)
    String register() {
        return "register";
    }

    @RequestMapping(value = "redirectPage", method = RequestMethod.GET)
    String redirectPage() {
        return "login-redirect";
    }


    @RequestMapping(value = "hasLogined", method = RequestMethod.GET)
    @ResponseBody
    ResponseJson hasLogined(HttpServletRequest httpServletRequest) {
        UserInfoDTO userInfoDTO = (UserInfoDTO) sessionUtil.getSession(httpServletRequest, SessionConstant.SESSION_USER);
        if (userInfoDTO == null) {
            return ResponseJson.fail();
        }
        return ResponseJson.success();
    }

    /**
     * qq登录回调
     *
     * @return
     */
    @RequestMapping(value = "/doCallbackByTencent", method = RequestMethod.GET)
    public String doCallbackByTencent(String code, String state, HttpServletResponse httpServletResponse) throws Exception {
        String appId = "101516738";
        String appKey = "889ce2f8e3bc37d7a59ba17b1f06e589";
        //第一步，获取 access_token
        Map<String, String> querys = new HashMap<>();
        String host = "https://graph.qq.com";
        Map<String, String> headers = new HashMap<>();
        String path = "/oauth2.0/token";
        querys.put("grant_type", "authorization_code");
        querys.put("client_id", appId);
        querys.put("client_secret", appKey);
        querys.put("code", code);
        querys.put("state", state);
        querys.put("redirect_uri", "https://www.hapicker.com/doCallbackByTencent");
        HttpResponse response = HttpUtils.doGet(host, path, "GET", headers, querys);
        String result = EntityUtils.toString(response.getEntity());
        Map<String, String> resultMap = new HashMap<>(20);
        for (String str : result.split("&")) {
            resultMap.put(str.split("=")[0], str.split("=")[1]);
        }
        String acessToken = resultMap.get("access_token");


        //第二步 获取OpenId
        querys = new HashMap<>();
        host = "https://graph.qq.com";
        path = "/oauth2.0/me";
        querys.put("access_token", acessToken);
        response = HttpUtils.doGet(host, path, "GET", headers, querys);
        result = EntityUtils.toString(response.getEntity());
        result = result.substring(result.indexOf("{"), result.indexOf("}") + 1);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String openId = jsonObject.getString("openid");

        //第三步，老用户直接登录
        UserConnectInfoDTO userConnectInfo = new UserConnectInfoDTO();
        userConnectInfo.setPlatformKey(UserConnectInfoPlatformEnum.TENCENT_QQ.getKey());
        userConnectInfo.setOpenId(openId);
        UserConnectInfoDTO userConnectInfoDTOResponseDTO = userClient.getUserConnectInfo(userConnectInfo).getContent();
        if (userConnectInfoDTOResponseDTO != null) {
            UserInfoDTO userInfoDTOQuery = new UserInfoDTO();
            userInfoDTOQuery.setUserId(userConnectInfoDTOResponseDTO.getUserId());
            UserInfoDTO userInfo = userClient.getUserInfo(userInfoDTOQuery).getContent();
            if (userInfo == null) {
                throw new BaseException(405, "找不到用户");
            }
            sessionUtil.setSession(httpServletResponse, userInfo, SessionConstant.SESSION_USER);
            return "redirect:" + "/redirectPage";
        }

        //第四步 从qq获取用户信息 为新用户注册账号
        querys = new HashMap<>();
        host = "https://graph.qq.com";
        path = "/user/get_user_info";
        querys.put("access_token", acessToken);
        querys.put("oauth_consumer_key", appId);
        querys.put("openid", openId);
        response = HttpUtils.doGet(host, path, "GET", headers, querys);
        result = EntityUtils.toString(response.getEntity());
        jsonObject = JSONObject.parseObject(result);
       /* System.out.println("开始查询用户信息 openId：" + openId);
        System.out.println(jsonObject);
        for (String key : jsonObject.keySet()) {
            System.out.println(key + " " + jsonObject.get(key));
        }*/
        String userName = jsonObject.getString("nickname");
        String userFigure = jsonObject.getString("figureurl_qq_2");
        if (StringUtils.isEmpty(userName)) {
            userName = "NONAME";
        } else {
            userName = userName.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");  //过滤掉emoj表情
        }

        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setUserNick(userName);
        userInfo.setUserAvatar(userFigure);
        userInfo.setUserStatus(StatusEnum.NORMAL.getKey());

        userInfo.setPlatformKey(UserConnectInfoPlatformEnum.TENCENT_QQ.getKey());
        userInfo.setOpenId(openId);
        userInfo = userClient.insertUserInfo(userInfo).getContent();
        sessionUtil.setSession(httpServletResponse, userInfo, SessionConstant.SESSION_USER);
        return "redirect:" + "/redirectPage";
    }
}
