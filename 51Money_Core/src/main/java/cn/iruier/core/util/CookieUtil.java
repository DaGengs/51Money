package cn.iruier.core.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    //获取Cookie
    public static String getCookie(String name, HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(name)){
                return cookie.getValue();
            }
        }
        return null;
    }
    //设置Cookie
    public static void setCookie(String name,String value,int age, HttpServletResponse response){
        Cookie cookie=new Cookie(name,value);
        cookie.setMaxAge(age);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    //删除Cookie
    public static void delCookie(String name, HttpServletResponse response){
        Cookie cookie=new Cookie(name,"");
        cookie.setMaxAge(0);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
