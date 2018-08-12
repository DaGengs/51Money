package cn.iruier.core.vo;

import java.io.Serializable;

/**
 * @Author: iruier
 * @Date: 2018/8/5 18:01
 */
public class StatusVo implements Serializable {
    private int user_id;
    private int infoStatus;
    private int riskStatus;
    private int score;
    private int carditMoney;
    private int account_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(int infoStatus) {
        this.infoStatus = infoStatus;
    }

    public int getRiskStatus() {
        return riskStatus;
    }

    public void setRiskStatus(int riskStatus) {
        this.riskStatus = riskStatus;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCarditMoney() {
        return carditMoney;
    }

    public void setCarditMoney(int carditMoney) {
        this.carditMoney = carditMoney;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
}
