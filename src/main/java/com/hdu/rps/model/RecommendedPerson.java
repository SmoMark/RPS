package com.hdu.rps.model;

public class RecommendedPerson {
    private Integer rdpno;

    private String rdpname;

    private Integer rdpnation;

    private String rdplocate;

    private String rdpbirthday;

    private Integer rdpsex;

    private Integer rdpdeal;

    private Integer rdpinsurance;

    private Integer rdpmarriage;

    private String rdpenglishlevel;

    private String rdpcomputlevel;

    private Integer rdpjobage;

    private String rdpphone;

    private String rdpaddress;

    private String rdpschool;

    private String rdpmajor;

    private String rdpemail;

    private String rdpphoto;

    private String rdpbrief;

    private String rdpgraduation;

    private int rdpincompany;

    private int rdphavechecked;
    /************************转换****************************/
    private String gender;
    private String deal;
    private String insurance;
    private String nation;

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public Integer getRdpno() {
        return rdpno;
    }

    public void setRdpno(Integer rdpno) {
        this.rdpno = rdpno;
    }

    public String getRdpname() {
        return rdpname;
    }

    public void setRdpname(String rdpname) {
        this.rdpname = rdpname == null ? null : rdpname.trim();
    }

    public Integer getRdpnation() {
        return rdpnation;
    }

    public void setRdpnation(Integer rdpnation) {
        this.rdpnation = rdpnation;
    }

    public String getRdplocate() {
        return rdplocate;
    }

    public void setRdplocate(String rdplocate) {
        this.rdplocate = rdplocate == null ? null : rdplocate.trim();
    }

    public String getRdpbirthday() {
        return rdpbirthday;
    }

    public void setRdpbirthday(String rdpbirthday) {
        this.rdpbirthday = rdpbirthday;
    }

    public Integer getRdpsex() {
        return rdpsex;
    }

    public void setRdpsex(Integer rdpsex) {
        this.rdpsex = rdpsex;
    }

    public Integer getRdpdeal() {
        return rdpdeal;
    }

    public void setRdpdeal(Integer rdpdeal) {
        this.rdpdeal = rdpdeal;
    }

    public Integer getRdpinsurance() {
        return rdpinsurance;
    }

    public void setRdpinsurance(Integer rdpinsurance) {
        this.rdpinsurance = rdpinsurance;
    }

    public Integer getRdpmarriage() {
        return rdpmarriage;
    }

    public void setRdpmarriage(Integer rdpmarriage) {
        this.rdpmarriage = rdpmarriage;
    }

    public String getRdpenglishlevel() {
        return rdpenglishlevel;
    }

    public void setRdpenglishlevel(String rdpenglishlevel) {
        this.rdpenglishlevel = rdpenglishlevel;
    }

    public String getRdpcomputlevel() {
        return rdpcomputlevel;
    }

    public void setRdpcomputlevel(String rdpcomputlevel) {
        this.rdpcomputlevel = rdpcomputlevel;
    }

    public Integer getRdpjobage() {
        return rdpjobage;
    }

    public void setRdpjobage(Integer rdpjobage) {
        this.rdpjobage = rdpjobage;
    }

    public String getRdpphone() {
        return rdpphone;
    }

    public void setRdpphone(String rdpphone) {
        this.rdpphone = rdpphone;
    }

    public String getRdpaddress() {
        return rdpaddress;
    }

    public void setRdpaddress(String rdpaddress) {
        this.rdpaddress = rdpaddress == null ? null : rdpaddress.trim();
    }

    public String getRdpschool() {
        return rdpschool;
    }

    public void setRdpschool(String rdpschool) {
        this.rdpschool = rdpschool == null ? null : rdpschool.trim();
    }

    public String getRdpmajor() {
        return rdpmajor;
    }

    public void setRdpmajor(String rdpmajor) {
        this.rdpmajor = rdpmajor == null ? null : rdpmajor.trim();
    }

    public String getRdpemail() {
        return rdpemail;
    }

    public void setRdpemail(String rdpemail) {
        this.rdpemail = rdpemail == null ? null : rdpemail.trim();
    }

    public String getRdpphoto() {
        return rdpphoto;
    }

    public void setRdpphoto(String rdpphoto) {
        this.rdpphoto = rdpphoto == null ? null : rdpphoto.trim();
    }

    public String getRdpbrief() {
        return rdpbrief;
    }

    public void setRdpbrief(String rdpbrief) {
        this.rdpbrief = rdpbrief == null ? null : rdpbrief.trim();
    }

    public String getRdpgraduation() {
        return rdpgraduation;
    }

    public void setRdpgraduation(String rdpgraduation) {
        this.rdpgraduation = rdpgraduation == null ? null : rdpgraduation.trim();
    }

    public int getRdpincompany() {
        return rdpincompany;
    }

    public void setRdpincompany(int rdpincompany) {
        this.rdpincompany = rdpincompany;
    }

    public int getRdphavechecked() {
        return rdphavechecked;
    }

    public void setRdphavechecked(int rdphavechecked) {
        this.rdphavechecked = rdphavechecked;
    }

    @Override
    public String toString() {
        return "RecommendedPerson{" +
                "rdpno=" + rdpno +
                ", rdpname='" + rdpname + '\'' +
                ", rdpnation=" + rdpnation +
                ", rdplocate='" + rdplocate + '\'' +
                ", rdpbirthday=" + rdpbirthday +
                ", rdpsex=" + rdpsex +
                ", rdpdeal=" + rdpdeal +
                ", rdpinsurance=" + rdpinsurance +
                ", rdpmarriage=" + rdpmarriage +
                ", rdpenglishlevel=" + rdpenglishlevel +
                ", rdpcomputlevel=" + rdpcomputlevel +
                ", rdpjobage=" + rdpjobage +
                ", rdpphone=" + rdpphone +
                ", rdpaddress='" + rdpaddress + '\'' +
                ", rdpschool='" + rdpschool + '\'' +
                ", rdpmajor='" + rdpmajor + '\'' +
                ", rdpemail='" + rdpemail + '\'' +
                ", rdpphoto='" + rdpphoto + '\'' +
                ", rdpbrief='" + rdpbrief + '\'' +
                ", rdpgraduation='" + rdpgraduation + '\'' +
                '}';
    }
}