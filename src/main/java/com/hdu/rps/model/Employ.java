package com.hdu.rps.model;

import java.util.Date;

public class Employ {
    private Integer employno;

    private Integer rcdno;

    private Integer posno;

    private Date employtime;

    private Integer employstate;

    private String employintro;

    public Integer getEmployno() {
        return employno;
    }

    public void setEmployno(Integer employno) {
        this.employno = employno;
    }

    public Integer getRcdno() {
        return rcdno;
    }

    public void setRcdno(Integer rcdno) {
        this.rcdno = rcdno;
    }

    public Integer getPosno() {
        return posno;
    }

    public void setPosno(Integer posno) {
        this.posno = posno;
    }

    public Date getEmploytime() {
        return employtime;
    }

    public void setEmploytime(Date employtime) {
        this.employtime = employtime;
    }

    public Integer getEmploystate() {
        return employstate;
    }

    public void setEmploystate(Integer employstate) {
        this.employstate = employstate;
    }

    public String getEmployintro() {
        return employintro;
    }

    public void setEmployintro(String employintro) {
        this.employintro = employintro == null ? null : employintro.trim();
    }
}