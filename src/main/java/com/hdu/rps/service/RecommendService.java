package com.hdu.rps.service;

import com.hdu.rps.model.RecommendedPerson;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/**
 * Created by SJH on 2017/11/17.
 */
public interface RecommendService {
    ArrayList<RecommendedPerson> findAll();
    void addToRepos( String name,  String sex,  String birthdate,
                     String minzu,  String mianmao,  String province,
                     String city,  String telphone,  String email,
                     String address,  String school,  String major,
                     String xueli,  String computer,  String english,
                     String interest, MultipartFile fil);
    RecommendedPerson findByID(int id);
}
