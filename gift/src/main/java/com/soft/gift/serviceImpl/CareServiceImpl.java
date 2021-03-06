package com.soft.gift.serviceImpl;

import com.soft.gift.mapper.CareDAO;
import com.soft.gift.model.Care;
import com.soft.gift.service.CareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fyq on 2017/5/6.
 */
@Service
public class CareServiceImpl implements CareService {
    @Autowired
    private CareDAO careDAO;

    @Override
    public Integer addCare(Care care) {
        careDAO.insertCare(care);
        Integer a = care.getId();
        return a;
    }

    @Override
    public Care getCare(String cared_account, String account) {
        Care care = new Care();
        care.setCared_account(cared_account);
        care.setAccount(account);
        Care result = careDAO.selectOne(care);
        return result;
    }
}
