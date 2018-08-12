package cn.iruier.uicontroller.invest;

import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.invest.Pay;
import cn.iruier.service.invest.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: iruier
 * @Date: 2018/8/11 11:44
 */
@RestController
public class PayController {

    @Autowired
    private PayService service;

    @PostMapping("savePay")
    public ResultVo pay(Pay pay) {
        return service.savePay(pay);
    }
}
