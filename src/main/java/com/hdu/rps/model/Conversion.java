package com.hdu.rps.model;

import java.util.Date;

public class Conversion {
    private Integer cvsno;

    private Integer giftno;

    private Integer userno;

    private Integer cvscounts;

    private Date cvstime;

    private Date cvsdeadline;

    private String cvsintro;

    public Integer getCvsno() {
        return cvsno;
    }

    public void setCvsno(Integer cvsno) {
        this.cvsno = cvsno;
    }

    public Integer getGiftno() {
        return giftno;
    }

    public void setGiftno(Integer giftno) {
        this.giftno = giftno;
    }

    public Integer getUserno() {
        return userno;
    }

    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    public Integer getCvscounts() {
        return cvscounts;
    }

    public void setCvscounts(Integer cvscounts) {
        this.cvscounts = cvscounts;
    }

    public Date getCvstime() {
        return cvstime;
    }

    public void setCvstime(Date cvstime) {
        this.cvstime = cvstime;
    }

    public Date getCvsdeadline() {
        return cvsdeadline;
    }

    public void setCvsdeadline(Date cvsdeadline) {
        this.cvsdeadline = cvsdeadline;
    }

    public String getCvsintro() {
        return cvsintro;
    }

    public void setCvsintro(String cvsintro) {
        this.cvsintro = cvsintro == null ? null : cvsintro.trim();
    }
}