package com.hdu.rps.model;

/**
 * Created by SJH on 2017/11/23.
 */
public class FollowDetail {
    private String rdpName;
    private String positionName;
    private int state;
    private int posno;

    public int getPosno() {
        return posno;
    }

    public void setPosno(int posno) {
        this.posno = posno;
    }

    public String getRdpName() {
        return rdpName;
    }

    public void setRdpName(String rdpName) {
        this.rdpName = rdpName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
