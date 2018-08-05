package cn.iruier.entity.loan;

import java.io.Serializable;

/**
 * @Author: iruier
 * @Date: 2018/8/4 20:18
 */
public class LoanLog implements Serializable {
    private int id;
    private int type;
    private int sysUser_id;
    private String msg;

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

    public int getSysUser_id() {
        return sysUser_id;
    }

    public void setSysUser_id(int sysUser_id) {
        this.sysUser_id = sysUser_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
