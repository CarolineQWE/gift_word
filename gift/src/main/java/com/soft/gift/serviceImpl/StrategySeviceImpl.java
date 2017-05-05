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

    @Override
    public List<Strategy> findStraByKeyword(String keyword) {
        List<Strategy> list = new ArrayList<Strategy>();
        list = strategyDAO.selectStrategyByKeyword(keyword);
        return list;
    }

    @Override
    public List<Strategy> findStra(String relationship, String occasion, String style) {
        List<Strategy> strategies = new ArrayList<>();
        Strategy strategy = new Strategy();
        strategy.setRelationship(relationship);
        strategy.setOccasion(occasion);
        strategy.setStyle(style);
        strategies = strategyDAO.select(strategy);
        return strategies;
    }

    @Override
    public Strategy findStraById(Integer id) {
        Strategy strategy = new Strategy();
        strategy.setId(id);
        Strategy strategy1 = strategyDAO.selectOne(strategy);
        return strategy1;
    }

    @Override
    public void addLike(Integer id) {
        Strategy strategy = strategyDAO.selectByPrimaryKey(id);
        strategy.setLike(strategy.getLike()+1);
        strategyDAO.updateByPrimaryKey(strategy);
    }

    @Override
    public void addDislike(Integer id) {
        Strategy strategy = strategyDAO.selectByPrimaryKey(id);
        strategy.setDislike(strategy.getDislike()+1);
        strategyDAO.updateByPrimaryKey(strategy);
    }

    @Override
    public void addCollect(Integer id) {
        Strategy strategy = strategyDAO.selectByPrimaryKey(id);
        System.out.println("collect"+strategy.getCollect());
        strategy.setCollect(strategy.getCollect()+1);
        System.out.println("collect"+strategy.getCollect());
        strategyDAO.updateByPrimaryKey(strategy);
    }
}
