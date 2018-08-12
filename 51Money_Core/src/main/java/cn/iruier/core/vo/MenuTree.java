package cn.iruier.core.vo;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/8 15:40
 */
public class MenuTree {
    private String name;
    private int value;
    private boolean check;
    private List<MenuTree> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public List<MenuTree> getList() {
        return list;
    }

    public void setList(List<MenuTree> list) {
        this.list = list;
    }
}
