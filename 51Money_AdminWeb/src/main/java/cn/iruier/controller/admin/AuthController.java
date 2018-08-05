package cn.iruier.controller.admin;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.core.vo.UserRiskVo;
import cn.iruier.entity.user.UserInfo;
import cn.iruier.service.admin.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: iruier
 * @Date: 2018/8/2 11:25
 */
@RestController
@RequestMapping("/sys/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @GetMapping("/list")
    public PageVo<UserInfo> queryByPage(int page, int limit) {
        return service.queryByPage(page, limit);
    }

    @GetMapping("/update")
    public ResultVo update(int user_id, int status) {

        return service.update(user_id, status);
    }

    @GetMapping("/riskList")
    public PageVo<UserRiskVo> queryRiskList(int page, int limit) {
        return service.queryRisk(page, limit);
    }

    @GetMapping("/updateRiskStatus")
    public ResultVo updateRisk(int user_id, int status) {
        return service.updateRiskStatus(user_id, status);
    }
}
