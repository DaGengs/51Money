package cn.iruier.uicontroller.base;

import cn.iruier.core.jedis.JedisUtil;
import cn.iruier.core.util.CookieUtil;
import cn.iruier.entity.user.User;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: iruier
 * @Date: 2018/8/11 16:50
 */
public class BaseUtil {

    public static User getUser(JedisUtil jedisUtil, HttpServletRequest request) {
        String json = jedisUtil.getStr("userToken:" + CookieUtil.getCookie("myToken", request));
        return JSON.parseObject(json, User.class);
    }
}
