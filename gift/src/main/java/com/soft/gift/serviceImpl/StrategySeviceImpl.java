package com.soft.gift.serviceImpl;

import com.soft.gift.mapper.StrategyDAO;
import com.soft.gift.model.Strategy;
import com.soft.gift.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fyq on 2017/4/23.
 */
@Service
public class StrategySeviceImpl implements StrategyService {
    /**
     * strategyDAO
     */
    @Autowired
    private StrategyDAO strategyDAO;

    @Override
    public List<Strategy> getLatestStras() {
        List<Strategy> list = new ArrayList<>();
        list = strategyDAO.getAllStrategyOrderByTime();
        System.out.println("list"+list);
      /*  for (Strategy stra:list) {
            stra.setContent(stra.getContent().substring(0,50));
        }*/
        return list;
    }

    @Override
    public List<Strategy> getHotestStras() {
        List<Strategy> list = new ArrayList<>();
        list = strategyDAO.getAllStrategyOrderByHot();
        return list;
    }

    @Override
    public void addStrategy(Strategy strategy) {
        strategyDAO.insert(strategy);
    }
}
