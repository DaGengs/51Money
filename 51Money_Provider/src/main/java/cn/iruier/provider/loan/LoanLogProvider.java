package cn.iruier.provider.loan;

import cn.iruier.core.vo.PageVo;
import cn.iruier.entity.loan.LoanLog;
import cn.iruier.mapper.loan.LoanLogMapper;
import cn.iruier.service.loan.LoanLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: iruier
 * @Date: 2018/8/4 20:22
 */
@Service
public class LoanLogProvider implements LoanLogService {

    @Autowired
    private LoanLogMapper loanLogMapper;

    @Override
    public void insert(LoanLog loanLog) {
        loanLogMapper.insert(loanLog);
    }

    @Override
    public PageVo<LoanLog> queryAll(int page, int size) {
     /*   int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }*/
        return null;
    }
}
