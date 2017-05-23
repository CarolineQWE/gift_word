package com.soft.gift.serviceImpl;

import com.soft.gift.mapper.DesignDAO;
import com.soft.gift.model.Design;
import com.soft.gift.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fyq on 2017/5/17.
 */
@Service
public class DesignServiceImpl implements DesignService {
    @Autowired
    private DesignDAO designDAO;
    @Override
    public void addDesign(Design design) {
        designDAO.insert(design);
    }

    @Override
    public List<Design> getAllDesign() {
        List<Design> designs = designDAO.selectAll();
        return designs;
    }

    @Override
    public List<Design> getDesign(Design design) {
        List<Design> designs = designDAO.select(design);
        return designs;
    }

    @Override
    public void updateDesignById(Design design) {
        designDAO.updateByPrimaryKeySelective(design);
    }
}
