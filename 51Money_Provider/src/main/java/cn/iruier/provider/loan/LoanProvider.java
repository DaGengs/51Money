package cn.iruier.provider.loan;

import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.util.PageUtil;
import cn.iruier.core.vo.LoanVo;
import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.core.vo.StatusVo;
import cn.iruier.entity.loan.Loan;
import cn.iruier.mapper.loan.LoanMapper;
import cn.iruier.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public StatusVo queryStatus(int user_id) {
        return loanMapper.queryStatus(user_id);
    }

    @Override
    public Loan queryByAID(int account_id) {
        return loanMapper.queryByAID(account_id);
    }

    @Override
    public List<Loan> queryList() {
        return loanMapper.queryList();
    }

    @Override
    public LoanVo queryDetail(int id) {
        return loanMapper.queryDetail(id);
    }
}
