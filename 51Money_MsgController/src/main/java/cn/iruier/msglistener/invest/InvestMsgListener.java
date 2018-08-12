package cn.iruier.msglistener.invest;

import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.invest.Invest;
import cn.iruier.entity.loan.LoanLog;
import cn.iruier.entity.user.Account;
import cn.iruier.service.invest.InvestService;
import cn.iruier.service.loan.LoanLogService;
import cn.iruier.service.user.AccountService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.Serializable;

/**
 * @Author: iruier
 * @Date: 2018/8/4 20:07
 */
@Service
public class InvestMsgListener implements MessageListener {

    @Autowired
    private AccountService service;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            System.out.println(textMessage.getText());
            ResultVo resultVo = JSON.parseObject(textMessage.getText(), ResultVo.class);
            Invest invest = JSON.parseObject(resultVo.getMsg(), Invest.class);
            Account currAccount = service.queryByAid(invest.getAccount_id());
            switch (resultVo.getCode()) {
                case 20001:
                    Account account = new Account();
                    account.setId(invest.getAccount_id());
                    account.setTotalMoney(currAccount.getTotalMoney() - invest.getMoney());
                    account.setForceMoney(currAccount.getForceMoney() + invest.getMoney());
                    System.out.println(account);
                    service.update(account);
                    break;
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
