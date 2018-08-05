package cn.iruier.service.user;

import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.user.Account;

/**
 * @Author: iruier
 * @Date: 2018/8/3 16:54
 */
public interface AccountService {

    ResultVo insert(Account account);

    Account queryByUid(int user_id);

    ResultVo update(Account account);
}
