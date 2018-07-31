package cn.iruier.controller.admin;

import cn.iruier.core.util.EncrypUtil;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.service.admin.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: iruier
 * @Date: 2018/7/30 23:22
 */
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/sys/login.do")
    public ResultVo login(String username, String password) {
        try {
            Subject subject = SecurityUtils.getSubject();
            password = EncrypUtil.md5Pass(password);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        }catch (UnknownAccountException e) {
            return ResultVo.setError(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return ResultVo.setError(e.getMessage());
        }catch (LockedAccountException e) {
            return ResultVo.setError(e.getMessage());
        } catch (AuthenticationException e) {
            return ResultVo.setError(e.getMessage());
        }
        return ResultVo.setSuccess("登录成功");
    }
}
