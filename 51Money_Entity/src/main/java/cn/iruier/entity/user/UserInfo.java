package cn.iruier.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    private int id;
    private int user_id;
    private String realName;
    private String idNumber;
    private int gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String address;
    private String idCardImg_pre;
    private String idCardImg_aft;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCardImg_pre() {
        return idCardImg_pre;
    }

    public void setIdCardImg_pre(String idCardImg_pre) {
        this.idCardImg_pre = idCardImg_pre;
    }

    public String getIdCardImg_aft() {
        return idCardImg_aft;
    }

    public void setIdCardImg_aft(String idCardImg_aft) {
        this.idCardImg_aft = idCardImg_aft;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
