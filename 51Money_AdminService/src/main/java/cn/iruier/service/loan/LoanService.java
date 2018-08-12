package cn.iruier.service.loan;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.loan.Loan;

/**
 * @Author: iruier
 * @Date: 2018/8/11 10:49
 */
public interface LoanService {

    PageVo<Loan> queryAll(int page, int size);

    ResultVo updateStatus(int id, int status);
}
