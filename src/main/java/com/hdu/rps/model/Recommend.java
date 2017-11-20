package com.hdu.rps.model;

public class Recommend {
    private Integer rcdno;

    private Integer userno;

    private Integer repno;

    private Integer rcdstate;

    private String rcdaddtime;

    private String rcdmodtime;

    private String rcdintro;

    private Integer posno;

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

    public String getRcdaddtime() {
        return rcdaddtime;
    }

    public void setRcdaddtime(String rcdaddtime) {
        this.rcdaddtime = rcdaddtime == null ? null : rcdaddtime.trim();
    }

    public String getRcdmodtime() {
        return rcdmodtime;
    }

    public void setRcdmodtime(String rcdmodtime) {
        this.rcdmodtime = rcdmodtime == null ? null : rcdmodtime.trim();
    }

    public String getRcdintro() {
        return rcdintro;
    }

    public void setRcdintro(String rcdintro) {
        this.rcdintro = rcdintro == null ? null : rcdintro.trim();
    }

    public Integer getPosno() {
        return posno;
    }

    public void setPosno(Integer posno) {
        this.posno = posno;
    }
}