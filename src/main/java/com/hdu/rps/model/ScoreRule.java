package com.hdu.rps.model;

public class ScoreRule {
    private Integer id;

    private Integer pass1;

    private Integer pass2;

    private Integer pass3;

    private Integer pass4;

    private Integer pass5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPass1() {
        return pass1;
    }

    public void setPass1(Integer pass1) {
        this.pass1 = pass1;
    }

    public Integer getPass2() {
        return pass2;
    }

    public void setPass2(Integer pass2) {
        this.pass2 = pass2;
    }

    public Integer getPass3() {
        return pass3;
    }

    public void setPass3(Integer pass3) {
        this.pass3 = pass3;
    }

    public Integer getPass4() {
        return pass4;
    }

    public void setPass4(Integer pass4) {
        this.pass4 = pass4;
    }

    public Integer getPass5() {
        return pass5;
    }

    public void setPass5(Integer pass5) {
        this.pass5 = pass5;
    }

    @Override
    public String toString() {
        return "ScoreRule{" +
                "id=" + id +
                ", pass1=" + pass1 +
                ", pass2=" + pass2 +
                ", pass3=" + pass3 +
                ", pass4=" + pass4 +
                ", pass5=" + pass5 +
                '}';
    }
}