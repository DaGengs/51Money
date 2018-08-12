package cn.iruier.controller.loan;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.loan.Loan;
import cn.iruier.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: iruier
 * @Date: 2018/8/11 10:52
 */
@RestController
@RequestMapping("/sys/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @GetMapping("list")
    public PageVo<Loan> queryList(int page, int limit) {
        return service.queryAll(page, limit);
    }

    @GetMapping("/update")
    public ResultVo update(int id, int status) {
        return service.updateStatus(id, status);
    }
}
