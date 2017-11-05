package com.hdu.rps.model;

import java.util.Date;

public class User {
    private Integer userno;

    private Integer userphone;

    private String userpassword;

    private String username;

    private Integer userage;

    private Integer usersex;

    private String useremail;

    private Date userbirthday;

    private String usercompany;

    private String usercreated;

    private String userdepartment;

    private String userjob;

    private Date usertime;

    private Integer userjobage;

    public Integer getUserno() {
        return userno;
    }

    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    public Integer getUserphone() {
        return userphone;
    }

    public void setUserphone(Integer userphone) {
        this.userphone = userphone;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getUserage() {
        return userage;
    }

    public void setUserage(Integer userage) {
        this.userage = userage;
    }

    public Integer getUsersex() {
        return usersex;
    }

    public void setUsersex(Integer usersex) {
        this.usersex = usersex;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail == null ? null : useremail.trim();
    }

    public Date getUserbirthday() {
        return userbirthday;
    }

    public void setUserbirthday(Date userbirthday) {
        this.userbirthday = userbirthday;
    }

    public String getUsercompany() {
        return usercompany;
    }

    public void setUsercompany(String usercompany) {
        this.usercompany = usercompany == null ? null : usercompany.trim();
    }

    public String getUsercreated() {
        return usercreated;
    }

    public void setUsercreated(String usercreated) {
        this.usercreated = usercreated == null ? null : usercreated.trim();
    }

    public String getUserdepartment() {
        return userdepartment;
    }

    public void setUserdepartment(String userdepartment) {
        this.userdepartment = userdepartment == null ? null : userdepartment.trim();
    }

    public String getUserjob() {
        return userjob;
    }

    public void setUserjob(String userjob) {
        this.userjob = userjob == null ? null : userjob.trim();
    }

    public Date getUsertime() {
        return usertime;
    }

    public void setUsertime(Date usertime) {
        this.usertime = usertime;
    }

    public Integer getUserjobage() {
        return userjobage;
    }

    public void setUserjobage(Integer userjobage) {
        this.userjobage = userjobage;
    }
}