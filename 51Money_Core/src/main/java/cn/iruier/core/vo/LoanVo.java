package cn.iruier.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: iruier
 * @Date: 2018/8/10 20:15
 */
public class LoanVo implements Serializable {
    private int id;
    private int type;
    private String typeName;
    private float rate;
    private int monthes;
    private int money;
    private int completeMoney;
    private int investPersion;
    private int minMoney;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date authDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enddate;
    private int disableDays;
    private int totalMoney;
    private int status;
    private String statusName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        switch (type){
            case 0:
                typeName="信用标";break;
            case 1:
                typeName="抵押标";break;
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getMonthes() {
        return monthes;
    }

    public void setMonthes(int monthes) {
        this.monthes = monthes;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCompleteMoney() {
        return completeMoney;
    }

    public void setCompleteMoney(int completeMoney) {
        this.completeMoney = completeMoney;
    }

    public int getInvestPersion() {
        return investPersion;
    }

    public void setInvestPersion(int investPersion) {
        this.investPersion = investPersion;
    }

    public int getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(int minMoney) {
        this.minMoney = minMoney;
    }

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthdate(Date authDate) {
        this.authDate = authDate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public int getDisableDays() {
        return disableDays;
    }

    public void setDisableDays(int disableDays) {
        this.disableDays = disableDays;
      /*  Calendar calendar= Calendar.getInstance();
        System.out.println(authDate);
        calendar.setTime(authDate);
        calendar.add(Calendar.DAY_OF_MONTH,disableDays);
        enddate=calendar.getTime();*/
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        switch (status){
            case 0:statusName="未审核";break;
            case 1:statusName="投标中";break;
            case 2:statusName="收益中";break;
        }
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
