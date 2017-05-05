package com.soft.gift.mapper;

import com.soft.gift.model.Strategy;
import com.soft.gift.util.BaseDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by fyq on 2017/4/23.
 */
public interface StrategyDAO extends BaseDAO<Strategy> {
    public List<Strategy> getAllStrategyOrderByTime();
    public List<Strategy> getAllStrategyOrderByHot();
    public List<Strategy> selectStrategyByKeyword(@Param("keyword") String keyword);

}
