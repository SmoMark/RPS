package com.hdu.rps.model;

public class Position {
    private Integer posno;

    private Integer postype;

    private Integer posstate;

    private String posoffice;

    private String postime;

    private String posdeadline;

    private Integer posneeds;

    private String posintro;

    private Integer possalary1;

    private Integer possalary2;

    private String posskill;

    private String posmessage;

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

    public String getPostime() {
        return postime;
    }

    public void setPostime(String postime) {
        this.postime = postime;
    }

    public String getPosdeadline() {
        return posdeadline;
    }

    public void setPosdeadline(String posdeadline) {
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

    public Integer getPossalary1() {
        return possalary1;
    }

    public void setPossalary1(Integer possalary1) {
        this.possalary1 = possalary1;
    }

    public Integer getPossalary2() {
        return possalary2;
    }

    public void setPossalary2(Integer possalary2) {
        this.possalary2 = possalary2;
    }

    public String getPosskill() {
        return posskill;
    }

    public void setPosskill(String posskill) {
        this.posskill = posskill == null ? null : posskill.trim();
    }

    public String getPosmessage() {
        return posmessage;
    }

    public void setPosmessage(String posmessage) {
        this.posmessage = posmessage == null ? null : posmessage.trim();
    }
}