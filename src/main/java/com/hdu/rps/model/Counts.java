package com.hdu.rps.model;

public class Counts {
    private Integer countsno;

    private Integer userno;

    private Integer countstype;

    private Integer countsreason;

    private Integer countsquantity;

    private String countstime;

    private String countsintro;

    public Integer getCountsno() {
        return countsno;
    }

    public void setCountsno(Integer countsno) {
        this.countsno = countsno;
    }

    public Integer getUserno() {
        return userno;
    }

    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    public Integer getCountstype() {
        return countstype;
    }

    public void setCountstype(Integer countstype) {
        this.countstype = countstype;
    }

    public Integer getCountsreason() {
        return countsreason;
    }

    public void setCountsreason(Integer countsreason) {
        this.countsreason = countsreason;
    }

    public Integer getCountsquantity() {
        return countsquantity;
    }

    public void setCountsquantity(Integer countsquantity) {
        this.countsquantity = countsquantity;
    }

    public String getCountstime() {
        return countstime;
    }

    public void setCountstime(String countstime) {
        this.countstime = countstime;
    }

    public String getCountsintro() {
        return countsintro;
    }

    public void setCountsintro(String countsintro) {
        this.countsintro = countsintro == null ? null : countsintro.trim();
    }
}