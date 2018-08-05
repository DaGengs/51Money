package cn.iruier.entity.loan;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: iruier
 * @Date: 2018/8/4 17:45
 */
public class Loan implements Serializable {
    private int id;
    private int type;
    private int money;
    private float rate;
    private int monthes;
    private int returnType;
    private int minMoney;
    private int disableDays;
    private String title;
    private String description;
    private int account_id;
    private Date createDate;
    private int status;
    private Date authDate;

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
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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

    public int getReturnType() {
        return returnType;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }

    public int getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(int minMoney) {
        this.minMoney = minMoney;
    }

    public int getDisableDays() {
        return disableDays;
    }

    public void setDisableDays(int disableDays) {
        this.disableDays = disableDays;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }
}
