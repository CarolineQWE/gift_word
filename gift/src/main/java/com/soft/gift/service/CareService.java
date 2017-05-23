package com.soft.gift.service;

import com.soft.gift.model.Care;

/**
 * Created by fyq on 2017/5/6.
 */
public interface CareService {
    public Integer addCare(Care care);

    Care getCare(String cared_account, String account);
}
