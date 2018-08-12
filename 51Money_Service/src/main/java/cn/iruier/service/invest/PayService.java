package cn.iruier.service.invest;

import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.invest.Pay;

/**
 * @Author: iruier
 * @Date: 2018/8/11 11:41
 */
public interface PayService {

    ResultVo savePay(Pay pay);
}
