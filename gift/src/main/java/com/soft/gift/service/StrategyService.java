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
}
