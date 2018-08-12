package cn.iruier.provider.user;

import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.user.Account;
import cn.iruier.mapper.user.AccountMapper;
import cn.iruier.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: iruier
 * @Date: 2018/8/3 16:57
 */
@Service
public class AccountProvider implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResultVo insert(Account account) {
        return ExecuteUtil.getResult(accountMapper.insert(account), "开通账户");
    }

    @Override
    public Account queryByUid(int user_id) {
        return accountMapper.queryByUid(user_id);
    }

    @Override
    public ResultVo update(Account account) {
        return ExecuteUtil.getResult(accountMapper.update(account), "账户更新");
    }

    @Override
    public Account queryByAid(int account_id) {
        return accountMapper.quertByAid(account_id);
    }
}
