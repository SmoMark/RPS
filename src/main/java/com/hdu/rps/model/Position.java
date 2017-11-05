package com.hdu.rps.model;

import java.util.Date;

public class Position {
    private Integer posno;

    private Integer postype;

    private Integer posstate;

    private String posoffice;

    private Date postime;

    private Date posdeadline;

    private Integer posneeds;

    private String posintro;

    public Integer getPosno() {
        return posno;
    }

    public void setPosno(Integer posno) {
        this.posno = posno;
    }

    public Integer getPostype() {
        return postype;
    }

    public void setPostype(Integer postype) {
        this.postype = postype;
    }

    public Integer getPosstate() {
        return posstate;
    }

    public void setPosstate(Integer posstate) {
        this.posstate = posstate;
    }

    public String getPosoffice() {
        return posoffice;
    }

    public void setPosoffice(String posoffice) {
        this.posoffice = posoffice == null ? null : posoffice.trim();
    }

    public Date getPostime() {
        return postime;
    }

    public void setPostime(Date postime) {
        this.postime = postime;
    }

    public Date getPosdeadline() {
        return posdeadline;
    }

    public void setPosdeadline(Date posdeadline) {
        this.posdeadline = posdeadline;
    }

    public Integer getPosneeds() {
        return posneeds;
    }

    public void setPosneeds(Integer posneeds) {
        this.posneeds = posneeds;
    }

    public String getPosintro() {
        return posintro;
    }

    public void setPosintro(String posintro) {
        this.posintro = posintro == null ? null : posintro.trim();
    }
}