package cn.iruier.service.loan;

import cn.iruier.core.vo.LoanVo;
import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.core.vo.StatusVo;
import cn.iruier.entity.loan.Loan;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/4 17:58
 */
public interface LoanService {

    ResultVo insert(Loan loan);

//    int update(Loan loan);

    Loan queryByAID(int account_id);

    StatusVo queryStatus(int user_id);

    List<Loan> queryList();

    LoanVo queryDetail(int id);
}
