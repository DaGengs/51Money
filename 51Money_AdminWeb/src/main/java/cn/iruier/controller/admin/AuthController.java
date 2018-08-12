package cn.iruier.controller.admin;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.core.vo.UserRiskVo;
import cn.iruier.entity.user.Account;
import cn.iruier.entity.user.Risk;
import cn.iruier.entity.user.UserInfo;
import cn.iruier.mapper.user.RiskMapper;
import cn.iruier.service.admin.AuthService;
import cn.iruier.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/2 11:25
 */
@RestController
@RequestMapping("/sys/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private AccountService accountService;

    @GetMapping("/list")
    public PageVo<UserInfo> queryByPage(int page, int limit) {
        return service.queryByPage(page, limit);
    }

    @GetMapping("/update")
    public ResultVo update(int user_id, int status) {
        ResultVo resultVo = service.update(user_id, status);
        if (resultVo.getCode() == 0) {
            Account account = new Account();
            account.setUser_id(user_id);
            account.setTotalMoney(0);
            account.setRedPackage(0);
            account.setForceMoney(0);
            account.setCarditMoney(0);
            account.setStatus(1);
            accountService.insert(account);
        }
        return resultVo;
    }

    @GetMapping("/riskList")
    public PageVo<UserRiskVo> queryRiskList(int page, int limit) {
        return service.queryRisk(page, limit);
    }

    @GetMapping("/updateRiskStatus")
    public ResultVo updateRisk(int user_id, int status) {
        List<Risk> risks = service.queryByUid(user_id);
        Account account = new Account();
        int carditMoney = 0;
        for (Risk risk : risks) {
            switch (risk.getType()) {
                case 1:
                    carditMoney = carditMoney + risk.getScore() * 500;
                    break;
                case 2:
                    carditMoney = carditMoney + risk.getScore() * 400;
                    break;
                case 3:
                    carditMoney = carditMoney + risk.getScore() * 300;
                    break;
                case 4:
                    carditMoney = carditMoney + risk.getScore() * 200;
                    break;
                case 5:
                    carditMoney = carditMoney + risk.getScore() * 100;
                    break;
            }
            account.setUser_id(user_id);
            account.setCarditMoney(carditMoney);
            accountService.update(account);
        }
        return service.updateRiskStatus(user_id, status);
    }
}
