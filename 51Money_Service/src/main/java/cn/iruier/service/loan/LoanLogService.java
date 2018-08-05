package cn.iruier.service.loan;

import cn.iruier.core.vo.PageVo;
import cn.iruier.entity.loan.LoanLog;

/**
 * @Author: iruier
 * @Date: 2018/8/4 20:22
 */
public interface LoanLogService {

    void insert(LoanLog loanLog);

    PageVo<LoanLog> queryAll(int page, int size);
}
