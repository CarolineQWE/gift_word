package com.soft.gift;

import com.soft.gift.mapper.CareDAO;
import com.soft.gift.mapper.GiftDAO;
import com.soft.gift.mapper.StrategyDAO;
import com.soft.gift.mapper.UserInfoDAO;
import com.soft.gift.model.Strategy;
import com.soft.gift.model.UserInfo;
import com.soft.gift.service.CareService;
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
	private StrategyDAO strategyDAO;
	@Autowired
	private StrategyService strategyService;
	@Autowired
	private CareDAO careDAO;
	@Autowired
	private CareService careService;
	@Autowired
	private GiftDAO giftDAO;
	@Autowired
	private UserInfoDAO userInfoDAO;

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

		/*Strategy strategy = strategyDAO.selectByPrimaryKey(20);
		strategy.setLike(strategy.getLike()+1);
		Integer a = strategyDAO.updateByPrimaryKey(strategy);
		System.out.println("a:"+a);*/
		/*Care care = new Care("qwe","qwe");
		Integer a = careService.addCare(care);
		System.out.println("<<<<<<<<<<<<<<<<<<care.id:"+care.getId());*/

		/*Gift gift = new Gift();
		gift.setId(13);
		gift.setStatus(0);
		gift.setPrice(12.1);
		gift.setStock(459999);
		giftDAO.updateByPrimaryKeySelective(gift);*/

		UserInfo userInfo = new UserInfo("18752001960","wang",22,1,"江苏","jj","jj",null,null,null,null);
		userInfoDAO.updateByPrimaryKeySelective(userInfo);
	}

}
