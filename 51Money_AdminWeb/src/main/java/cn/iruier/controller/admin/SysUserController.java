package cn.iruier.controller.admin;

import cn.iruier.core.shiro.ShiroUtil;
import cn.iruier.core.util.EncrypUtil;
import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.SysUser;
import cn.iruier.service.admin.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: iruier
 * @Date: 2018/7/30 23:22
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/login.do")
    @ResponseBody
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
        return new ResultVo(0, "登录成功", sysUserService.login(username));
    }

    @RequestMapping("/loginout.do")
    public String loginout() {
        ShiroUtil.logout();
        return "redirect:/login.html";
    }

    @RequestMapping("/info")
    @ResponseBody
    @RequiresPermissions({"sys:user:info"})
    public SysUser queryById() {
        return sysUserService.queryById(1);
    }

    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions({"sys:user:save"})
    public ResultVo save(SysUser sysUser) {
        return sysUserService.insert(sysUser);
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions({"sys:user:list"})
    public PageVo<SysUser> queryByPage(int page, int limit) {
        return sysUserService.queryByPage(page, limit);
    }

    @RequestMapping("/delete")
    @RequiresPermissions({"sys:user:delete"})
    public ResultVo deleteBatch(int[] user_ids) {
        return sysUserService.deleteBatch(user_ids);
    }
}
