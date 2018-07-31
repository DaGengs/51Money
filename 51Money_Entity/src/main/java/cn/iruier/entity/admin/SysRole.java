package cn.iruier.entity.admin;

import java.util.Date;

/**
 * @Author: iruier
 * @Date: 2018/7/30 21:15
 */
public class SysRole {
    private int role_id;
    private int role_name;
    private String remark;
    private int create_user_id;
    private Date create_time;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getRole_name() {
        return role_name;
    }

    public void setRole_name(int role_name) {
        this.role_name = role_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(int create_user_id) {
        this.create_user_id = create_user_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
