package com.soft.gift.mapper;

import com.soft.gift.model.Care;
import com.soft.gift.util.BaseDAO;

/**
 * Created by fyq on 2017/5/6.
 */
public interface CareDAO extends BaseDAO<Care>{
    public Integer insertCare(Care care);
}
