package com.dcr.analyse.bean;

import java.util.Date;

public class Comparision {
    private Integer cid;

    private String company;

    private Double targetmoney;

    private String year;

    private String business;

    private String priority;

    private String bad;

    private String status;

    private Integer empcount;

    private Date date;

    private String description;

    private Integer empfk;

    private String empName;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public Double getTargetmoney() {
        return targetmoney;
    }

    public void setTargetmoney(Double targetmoney) {
        this.targetmoney = targetmoney;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad == null ? null : bad.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getEmpcount() {
        return empcount;
    }

    public void setEmpcount(Integer empcount) {
        this.empcount = empcount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getEmpfk() {
        return empfk;
    }

    public void setEmpfk(Integer empfk) {
        this.empfk = empfk;
    }
}