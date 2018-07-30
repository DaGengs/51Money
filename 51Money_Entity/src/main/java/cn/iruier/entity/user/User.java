package cn.iruier.entity.user;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String phone;
    private int referrer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getReferrer() {
        return referrer;
    }

    public void setReferrer(int referrer) {
        this.referrer = referrer;
    }
}
