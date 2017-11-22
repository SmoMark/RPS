package com.hdu.rps.service;

import com.hdu.rps.model.Counts;

import java.util.ArrayList;

/**
 * Created by SJH on 2017/11/22.
 */
public interface CountsService {
    ArrayList<Counts> findAllOrderByCounts();
}
