package cn.iruier.uicontroller.invest;

import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.invest.Invest;
import cn.iruier.service.activemq.ActiveMQService;
import cn.iruier.service.invest.InvestService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: iruier
 * @Date: 2018/8/11 11:35
 */

@RestController
public class InvestController {

    @Autowired
    private InvestService service;

    @Autowired
    private ActiveMQService activeMQService;

    @PostMapping("saveInvest")
    public ResultVo save(Invest invest) {
        invest.setMoney(invest.getMoney()*100);
        ResultVo resultVo = service.saveInvest(invest);
        if (resultVo.getCode() == 0) {
            /*activeMQService.sendObject(new ResultVo(20001, "投资成功", invest));*/
            activeMQService.sendMsg(JSON.toJSONString(new ResultVo(20001, JSON.toJSONString(invest), null)));
        }
        return resultVo;
    }
}
