package cn.iruier.provider.invest;

import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.invest.Pay;
import cn.iruier.mapper.invest.PayMapper;
import cn.iruier.service.invest.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: iruier
 * @Date: 2018/8/11 11:42
 */

@Service
public class PayProvider implements PayService {

    @Autowired
    private PayMapper mapper;

    @Override
    public ResultVo savePay(Pay pay) {
        return ExecuteUtil.getResult(mapper.save(pay), "充值");
    }
}
