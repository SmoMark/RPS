package com.hdu.rps.model;

import java.util.Date;

public class Bonus {
    private Integer bonusno;

    private Integer userno;

    private Integer bonussupports;

    private Float bonusmoney;

    private Date bonustime;

    private Date bonusdeadline;

    private String bonusintro;

    public Integer getBonusno() {
        return bonusno;
    }

    public void setBonusno(Integer bonusno) {
        this.bonusno = bonusno;
    }

    public Integer getUserno() {
        return userno;
    }

    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    public Integer getBonussupports() {
        return bonussupports;
    }

    public void setBonussupports(Integer bonussupports) {
        this.bonussupports = bonussupports;
    }

    public Float getBonusmoney() {
        return bonusmoney;
    }

    public void setBonusmoney(Float bonusmoney) {
        this.bonusmoney = bonusmoney;
    }

    public Date getBonustime() {
        return bonustime;
    }

    public void setBonustime(Date bonustime) {
        this.bonustime = bonustime;
    }

    public Date getBonusdeadline() {
        return bonusdeadline;
    }

    public void setBonusdeadline(Date bonusdeadline) {
        this.bonusdeadline = bonusdeadline;
    }

    public String getBonusintro() {
        return bonusintro;
    }

    public void setBonusintro(String bonusintro) {
        this.bonusintro = bonusintro == null ? null : bonusintro.trim();
    }
}