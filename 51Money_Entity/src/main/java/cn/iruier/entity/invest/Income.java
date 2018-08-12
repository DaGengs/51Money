package cn.iruier.entity.invest;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: iruier
 * @Date: 2018/8/11 11:19
 */
public class Income implements Serializable {
    private int id;
    private int invest_id;
    private int expectMoney;
    private int overdueMoney;
    private Date createDate;
    private Date startDate;
    private int status;
    private Date endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvest_id() {
        return invest_id;
    }

    public void setInvest_id(int invest_id) {
        this.invest_id = invest_id;
    }

    public int getExpectMoney() {
        return expectMoney;
    }

    public void setExpectMoney(int expectMoney) {
        this.expectMoney = expectMoney;
    }

    public int getOverdueMoney() {
        return overdueMoney;
    }

    public void setOverdueMoney(int overdueMoney) {
        this.overdueMoney = overdueMoney;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
