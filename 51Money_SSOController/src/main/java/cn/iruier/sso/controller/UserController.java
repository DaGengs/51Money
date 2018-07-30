package cn.iruier.sso.controller;


import cn.iruier.core.util.CookieUtil;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.user.User;
import cn.iruier.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("doRegister.do")
    public ResultVo register(User user) {
        return service.save(user);
    }

    @PostMapping("doLogin.do")
    public ResultVo login(String name, String password, HttpServletResponse response, HttpSession session) {
        ResultVo resultVo = service.queryByName(name, password);
        if (resultVo.getCode() == 0) {
            CookieUtil.setCookie("myToken", resultVo.getMsg(), -1, response);
            resultVo.setMsg("登录成功");
            User user = (User) resultVo.getData();
            session.setAttribute("user", user);
        }
        return resultVo;
    }

    @GetMapping("checkLogin.do")
    public ResultVo checkLogin(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtil.getCookie("myToken", request);
        ResultVo resultVo = service.checkLogin(token);
        if (resultVo.getCode() == 1) {
            CookieUtil.delCookie("myToken",  response);
        }
        return resultVo;
    }

    @GetMapping("loginOut.do")
    public ResultVo loginOut(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtil.getCookie("myToken", request);
        ResultVo resultVo = service.loginOut(token);
        if (resultVo.getCode() == 0) {
            CookieUtil.delCookie("myToken",  response);
        }
        return resultVo;
    }

    @GetMapping("checkName.do")
    public ResultVo checkName(String username) {
        return service.checkName(username);
    }

    @GetMapping("getUser.do")
    public User getUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user;
    }
}
