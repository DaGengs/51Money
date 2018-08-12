package cn.iruier.uicontroller.loan;

import cn.iruier.core.vo.LoanVo;
import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.core.vo.StatusVo;
import cn.iruier.entity.loan.Loan;
import cn.iruier.service.activemq.ActiveMQService;
import cn.iruier.service.loan.LoanService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/4 18:05
 */
@RestController
public class LoanController {

    @Autowired
    private LoanService service;

    @Autowired
    private ActiveMQService activeMQService;

    @PostMapping("/loan/save")
    public ResultVo saveLoan(Loan loan) {
        ResultVo resultVo = service.insert(loan);
        if (resultVo.getCode() == 0) {
            activeMQService.sendMsg(JSON.toJSONString(new ResultVo(10001, "借款成功", loan)));
        }
        return resultVo;
    }

    @GetMapping("/loan/getLoan")
    public Loan getLoan(int account_id) {
        return service.queryByAID(account_id);
    }

    @GetMapping("/loan/getStatus")
    public StatusVo getStatus(int user_id) {
        return service.queryStatus(user_id);
    }

    @GetMapping("/loan/getList")
    public List<Loan> queryList() {
        return service.queryList();
    }

    @GetMapping("/loan/detail")
    public LoanVo getDetail(int id) {
        return service.queryDetail(id);
    }
}
