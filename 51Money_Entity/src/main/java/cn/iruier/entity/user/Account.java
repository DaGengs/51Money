package cn.iruier.entity.user;

import java.io.Serializable;

/**
 * @Author: iruier
 * @Date: 2018/8/3 16:45
 */
public class Account implements Serializable {
    private int id;
    private int user_id;
    private int totalMoney;
    private int redPackage;
    private int forceMoney;
    private int carditMoney;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getRedPackage() {
        return redPackage;
    }

    public void setRedPackage(int redPackage) {
        this.redPackage = redPackage;
    }

    public int getForceMoney() {
        return forceMoney;
    }

    public void setForceMoney(int forceMoney) {
        this.forceMoney = forceMoney;
    }

    public int getCarditMoney() {
        return carditMoney;
    }

    public void setCarditMoney(int carditMoney) {
        this.carditMoney = carditMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
