package com.hdu.rps.model;

import java.util.Date;

public class Recommend {
    private Integer rcdno;

    private Integer userno;

    private Integer repno;

    private Integer rcdstate;

    private Date rcdaddtime;

    private Date rcdmodtime;

    private String rcdintro;

    public Integer getRcdno() {
        return rcdno;
    }

    public void setRcdno(Integer rcdno) {
        this.rcdno = rcdno;
    }

    public Integer getUserno() {
        return userno;
    }

    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    public Integer getRepno() {
        return repno;
    }

    public void setRepno(Integer repno) {
        this.repno = repno;
    }

    public Integer getRcdstate() {
        return rcdstate;
    }

    public void setRcdstate(Integer rcdstate) {
        this.rcdstate = rcdstate;
    }

    public Date getRcdaddtime() {
        return rcdaddtime;
    }

    public void setRcdaddtime(Date rcdaddtime) {
        this.rcdaddtime = rcdaddtime;
    }

    public Date getRcdmodtime() {
        return rcdmodtime;
    }

    public void setRcdmodtime(Date rcdmodtime) {
        this.rcdmodtime = rcdmodtime;
    }

    public String getRcdintro() {
        return rcdintro;
    }

    public void setRcdintro(String rcdintro) {
        this.rcdintro = rcdintro == null ? null : rcdintro.trim();
    }
}