package cn.iruier.provider.loan;

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
 * @Date: 2018/8/4 18:01
 */
@Service
public class LoanProvider implements LoanService {

    @Autowired
    private LoanMapper loanMapper;

    @Override
    public ResultVo insert(Loan loan) {
        loan.setAccount_id(1);
        loan.setMoney(loan.getMoney() * 100);
        loan.setMinMoney(loan.getMinMoney() * 100);
        loan.setRate(loan.getRate()/100);
        return ExecuteUtil.getResult(loanMapper.insert(loan), "借款申请提交");
    }

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

    @Override
    public Loan queryByAID(int account_id) {
        return loanMapper.queryByAID(account_id);
    }
}
