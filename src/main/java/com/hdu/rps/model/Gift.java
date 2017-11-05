package com.hdu.rps.model;

import java.util.Date;

public class Gift {
    private Integer giftno;

    private String giftname;

    private Integer giftnums;

    private Integer giftcounts;

    private Integer giftstate;

    private Date gifttime;

    private Date giftdeadline;

    private String giftintro;

    public Integer getGiftno() {
        return giftno;
    }

    public void setGiftno(Integer giftno) {
        this.giftno = giftno;
    }

    public String getGiftname() {
        return giftname;
    }

    public void setGiftname(String giftname) {
        this.giftname = giftname == null ? null : giftname.trim();
    }

    public Integer getGiftnums() {
        return giftnums;
    }

    public void setGiftnums(Integer giftnums) {
        this.giftnums = giftnums;
    }

    public Integer getGiftcounts() {
        return giftcounts;
    }

    public void setGiftcounts(Integer giftcounts) {
        this.giftcounts = giftcounts;
    }

    public Integer getGiftstate() {
        return giftstate;
    }

    public void setGiftstate(Integer giftstate) {
        this.giftstate = giftstate;
    }

    public Date getGifttime() {
        return gifttime;
    }

    public void setGifttime(Date gifttime) {
        this.gifttime = gifttime;
    }

    public Date getGiftdeadline() {
        return giftdeadline;
    }

    public void setGiftdeadline(Date giftdeadline) {
        this.giftdeadline = giftdeadline;
    }

    public String getGiftintro() {
        return giftintro;
    }

    public void setGiftintro(String giftintro) {
        this.giftintro = giftintro == null ? null : giftintro.trim();
    }
}