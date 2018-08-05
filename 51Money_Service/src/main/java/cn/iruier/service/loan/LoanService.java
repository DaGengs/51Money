package cn.iruier.service.loan;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.loan.Loan;

/**
 * @Author: iruier
 * @Date: 2018/8/4 17:58
 */
public interface LoanService {

    ResultVo insert(Loan loan);

    PageVo<Loan> queryAll(int page, int size);

//    int update(Loan loan);

    ResultVo updateStatus(int id, int status);

    Loan queryByAID(int account_id);
}
