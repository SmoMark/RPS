package com.hdu.rps.service;

/**
 * Created by SJH on 2017/11/5.
 */

public interface LoginService {

   int findByUserEmailAndUserPasswordAndUserJob(String useremail, String userpassword, String userjob);
}
