package com.soft.gift.serviceImpl;

import com.soft.gift.mapper.CommentDAO;
import com.soft.gift.mapper.GiftDAO;
import com.soft.gift.mapper.UserInfoDAO;
import com.soft.gift.model.Comment;
import com.soft.gift.model.Gift;
import com.soft.gift.model.ScoreNum;
import com.soft.gift.model.UserInfo;
import com.soft.gift.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAO commentDAO;
	@Autowired
	private GiftDAO giftDAO;
	@Autowired
	private UserInfoDAO userinfoDAO;

	@Override
	public Map<Comment,UserInfo> getCommentByGiftID(Integer gift_id) {
		Map<Comment, UserInfo> map = new HashMap<>();
		Comment comment = new Comment();
		comment.setcommented_id(gift_id);
		comment.setCommented_type(0);
		List<Comment> list = new ArrayList<>();
		list = commentDAO.select(comment);
		UserInfo userInfo = new UserInfo();
		for (Comment com : list) {
			userInfo.setAccount(com.getAccount());
			userInfo = userinfoDAO.selectOne(userInfo);
			map.put(com, userInfo);
		}
		return map;
	}

	@Override
	public Double getAverageSorceByGiftID(Integer gift_id) {
		Double aver = commentDAO.getAverSorceByGiftID(gift_id);
		return aver;
	}

	@Override
	public List<Integer> getCommentNum(Integer gift_id) {
		Gift gift = new Gift();
		gift.setId(gift_id);
		gift = giftDAO.selectOne(gift);
		Integer comment = gift.getComment();
		List<ScoreNum> list = new ArrayList<>();
		list = commentDAO.getCommentNumClassifyBySorce(gift_id);
		Integer good = 0;
		Integer middle = 0;
		Integer bad = 0; 
		Integer gr = 0,mr = 0,br = 0;
		if(comment!=0){
			for (ScoreNum scoreNum : list) {
				Integer n = scoreNum.getNum();
				Integer s = scoreNum.getScore();
				if (s <= 1) {
					 bad += n;
				}else if(s <= 3){
					middle += n;
				}else{
					good += n;
				}
			}
			gr = (int) Math.round(((double)good/(double)comment)*100);
			mr = (int) Math.round(((double)middle/(double)comment)*100);
			br = (int) Math.round(((double)bad/(double)comment)*100);
		}
		List<Integer> cl = new ArrayList<>();
		cl.add(good);
		cl.add(middle);
		cl.add(bad);
		cl.add(gr);
		cl.add(mr);
		cl.add(br);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+comment);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+gr);
		System.out.println(mr);
		System.out.println(br);
		return cl;
	}

}
