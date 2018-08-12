package cn.iruier.entity.invest;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: iruier
 * @Date: 2018/8/11 11:21
 */
public class Pay implements Serializable {
    private int id;
    private int invest_id;
    private int money;
    private int oid;
    private Date createtime;
    private Date payTime;

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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
