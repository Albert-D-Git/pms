package com.dcr.analysis.bean;

import com.dcr.pro.bean.Project;

import java.util.Date;

public class Analysis {
    private Integer id;

    private String proname;

    private String title;

    private String simpledis;

    private String detaileddis;

    private Date addtime;

    private Date updatetime;

    private String remark;

    //添加======================
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
//===================================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname == null ? null : proname.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSimpledis() {
        return simpledis;
    }

    public void setSimpledis(String simpledis) {
        this.simpledis = simpledis == null ? null : simpledis.trim();
    }

    public String getDetaileddis() {
        return detaileddis;
    }

    public void setDetaileddis(String detaileddis) {
        this.detaileddis = detaileddis == null ? null : detaileddis.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "id=" + id +
                ", proname='" + proname + '\'' +
                ", title='" + title + '\'' +
                ", simpledis='" + simpledis + '\'' +
                ", detaileddis='" + detaileddis + '\'' +
                ", addtime=" + addtime +
                ", updatetime=" + updatetime +
                ", remark='" + remark + '\'' +
                ", project=" + project +
                '}';
    }
}