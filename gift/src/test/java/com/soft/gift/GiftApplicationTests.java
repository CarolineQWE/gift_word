package com.soft.gift;

import com.soft.gift.mapper.StrategyDAO;
import com.soft.gift.model.Strategy;
import com.soft.gift.service.StrategyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GiftApplicationTests {
	@Autowired
	public StrategyDAO strategyDAO;
	@Autowired
	public StrategyService strategyService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getStra(){
		String rel = "爸爸妈妈";
		String style = null;
		Strategy strategy = new Strategy();
		strategy.setRelationship(rel);
		strategy.setStyle(style);
		List<Strategy> strategies = strategyDAO.select(strategy);
		System.out.println(strategies);
	}

	@Test
	public  void get(){
		/*String rel = null;
		String style = "萌萌哒";
		String occasion = null;
		List<Strategy> list = strategyService.findStra(rel,occasion,style);
		System.out.println(list);*/

		Strategy strategy = strategyDAO.selectByPrimaryKey(20);
		strategy.setLike(strategy.getLike()+1);
		Integer a = strategyDAO.updateByPrimaryKey(strategy);
		System.out.println("a:"+a);
	}

}
