package cn.iruier.sso.service;

import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.user.User;

public interface UserService {
    /*注册*/
    ResultVo save(User user);

    /*登录*/
    ResultVo queryByName(String name, String password);

    /*校验登录*/
    ResultVo checkLogin(String token);

    /*注销*/
    ResultVo loginOut(String token);

    /*检测用户名*/
    ResultVo checkName(String name);

}
