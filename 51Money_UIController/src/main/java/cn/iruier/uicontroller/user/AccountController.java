package cn.iruier.uicontroller.user;

import cn.iruier.entity.user.Account;
import cn.iruier.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: iruier
 * @Date: 2018/8/11 18:01
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("getAccount")
    public Account getAccount(int user_id) {
        return service.queryByUid(user_id);
    }
}
