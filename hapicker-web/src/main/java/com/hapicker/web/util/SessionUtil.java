package com.hapicker.web.util;

import com.hapicker.common.constant.SessionConstant;
import com.hapicker.common.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author yuyufeng
 * @date 2017/8/2
 */
@Component
public class SessionUtil {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOs;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public Object getSession(HttpServletRequest httpServletRequest, String key) {
        String cookieValue = CookieUtil.getCookieValue(httpServletRequest, SessionConstant.SESSION_DATA + key);
        if (cookieValue == null) {
            return null;
        }
        Object t = null;
        try {
            t = valueOs.get(SessionConstant.SESSION_DATA + cookieValue);
        } catch (Exception e) {
            LOG.error("从redis获取session数据失败 " + e);
        }
        return t;
    }

    public void setSession(HttpServletResponse httpServletResponse, Object t, String key) {
        String uuid = UUIDUtil.getUUIDString();
        CookieUtil.setCookie(httpServletResponse, SessionConstant.SESSION_DATA + key, uuid);
        valueOs.set(SessionConstant.SESSION_DATA + uuid, t, 30, TimeUnit.MINUTES);
    }

    public void deleteSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String key) {
        String cookieValue = CookieUtil.getCookieValue(httpServletRequest, SessionConstant.SESSION_DATA + key);
        if (!StringUtils.isEmpty(cookieValue)) {
            try {
                redisTemplate.delete(SessionConstant.SESSION_DATA + cookieValue);
            } catch (Exception e) {
                LOG.error("从redis删除session数据失败 " + e);
            }
        }
        CookieUtil.clearCookie(httpServletResponse, SessionConstant.SESSION_DATA + key);
    }

}
