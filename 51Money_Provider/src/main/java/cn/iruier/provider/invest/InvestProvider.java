package cn.iruier.provider.invest;

import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.invest.Invest;
import cn.iruier.mapper.invest.InvestMapper;
import cn.iruier.service.invest.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: iruier
 * @Date: 2018/8/11 11:33
 */
@Service
public class InvestProvider implements InvestService {

    @Autowired
    private InvestMapper mapper;

    @Override
    public ResultVo saveInvest(Invest invest) {
        return ExecuteUtil.getResult(mapper.save(invest), "投资");
    }
}
