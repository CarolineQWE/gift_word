package com.soft.gift.service;

import com.soft.gift.model.Design;

import java.util.List;

/**
 * Created by fyq on 2017/5/17.
 */
public interface DesignService {
    public void addDesign(Design design);

    public List<Design> getAllDesign();
    public List<Design> getDesign(Design design);

    public void updateDesignById(Design design);
}
