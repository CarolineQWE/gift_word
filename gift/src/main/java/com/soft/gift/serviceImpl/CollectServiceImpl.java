package com.soft.gift.serviceImpl;

import com.soft.gift.mapper.CollectionsDAO;
import com.soft.gift.model.Collections;
import com.soft.gift.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fyq on 2017/5/11.
 */
@Service
public class CollectServiceImpl implements CollectService{
    @Autowired
    private CollectionsDAO collectionsDAO;

    @Override
    public void addCollect(Collections collections) {
        collectionsDAO.insert(collections);
    }
}
