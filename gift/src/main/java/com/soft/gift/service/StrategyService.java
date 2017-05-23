package com.soft.gift.service;

import com.soft.gift.model.Strategy;

import java.util.List;

/**
 * Created by fyq on 2017/4/23.
 */
public interface StrategyService {
    public List<Strategy> getLatestStras();
    public List<Strategy> getHotestStras();
    public void addStrategy(Strategy strategy);

    public List<Strategy> findStraByKeyword(String keyword);
    public List<Strategy> findStra(String relationship,String occasion,String style);

    public Strategy findStraById(Integer id);

    public void addLike(Integer id);

    public void addDislike(Integer id);

    public void addCollect(Integer id);

    public List<Strategy> searchStrategyByKeyword(String keyword);
}
