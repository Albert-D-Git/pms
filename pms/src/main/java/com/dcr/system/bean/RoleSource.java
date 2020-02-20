package com.dcr.system.bean;

public class RoleSource {
    private Integer rid;
    private Integer sid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "RoleSource{" +
                "rid=" + rid +
                ", sid=" + sid +
                '}';
    }
}
