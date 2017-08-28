package com.hzjytech.operation.entity;

/**
 * Created by hehongcan on 2017/5/11.
 */
public class GroupList {
    /**
     * id : 1
     * name : techGrou
     * isSuper : false
     */

    private int id;
    private String name;
    private boolean isSuper;

    public GroupList() {
    }

    public GroupList(int id, String name, boolean isSuper) {
        this.id = id;
        this.name = name;
        this.isSuper = isSuper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsSuper() {
        return isSuper;
    }

    public void setIsSuper(boolean isSuper) {
        this.isSuper = isSuper;
    }
}
