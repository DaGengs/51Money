package cn.iruier.service.loan.impl;

import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.util.PageUtil;
import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.loan.Loan;
import cn.iruier.mapper.loan.LoanMapper;
import cn.iruier.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: iruier
 * @Date: 2018/8/11 10:50
 */
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanMapper loanMapper;

    @Override
    public PageVo<Loan> queryAll(int page, int size) {
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        return PageUtil.getPageVo(loanMapper.queryAll(index, size), loanMapper.queryCount());
    }

    @Override
    public ResultVo updateStatus(int id, int status) {
        return ExecuteUtil.getResult(loanMapper.updateStatus(id, status), "借款申请审核");
    }

}
