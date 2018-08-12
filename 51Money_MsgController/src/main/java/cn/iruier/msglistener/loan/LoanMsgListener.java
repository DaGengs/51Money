package cn.iruier.msglistener.loan;

import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.loan.Loan;
import cn.iruier.entity.loan.LoanLog;
import cn.iruier.entity.user.Account;
import cn.iruier.service.loan.LoanLogService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Author: iruier
 * @Date: 2018/8/4 20:07
 */
@Service
public class LoanMsgListener implements MessageListener {

    @Autowired
    private LoanLogService service;
    /*
    @Autowired
    private Account account;*/

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("收到消息：" + textMessage.getText());
            ResultVo resultVo = JSON.parseObject(textMessage.getText(), ResultVo.class);
            switch (resultVo.getCode()) {
                case 10001:
                    LoanLog loanLog = new LoanLog();
                    loanLog.setType(1);
                    loanLog.setSysUser_id(1);
                    loanLog.setMsg("借款日志：" + textMessage.getText());
                    service.insert(loanLog);
                    break;
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
