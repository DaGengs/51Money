package cn.iruier.service.invest;

import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.invest.Invest;

/**
 * @Author: iruier
 * @Date: 2018/8/11 11:33
 */
public interface InvestService {
    ResultVo saveInvest(Invest invest);
}
