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
	public List<Comment> getCommentByGiftID(Integer gift_id) {
		Map<Comment, UserInfo> map = new HashMap<>();
		Comment comment = new Comment();
		comment.setCommented_id(gift_id);
		comment.setCommented_type(0);
		List<Comment> list = new ArrayList<>();
		list = commentDAO.select(comment);
		return list;
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

	@Override
	public void addComment(Comment comment) {
		commentDAO.insert(comment);
	}

	@Override
	public Comment getCommentByID(Integer commented_id) {
		Comment comment = commentDAO.selectByPrimaryKey(commented_id);
		return comment;
	}

	@Override
	public Map<Comment, List<Comment>> getComments(Integer commented_id) {
		Map<Comment,List<Comment>> map = new HashMap<>();
		List<Comment> firstLevelComments = new ArrayList<>();//评论文章或者礼物的
		firstLevelComments = commentDAO.getCommentsOrderByTime(commented_id,0);
		for (Comment c:firstLevelComments) {
			List<Comment> list = new ArrayList<>();
			list = commentDAO.getCommentsOrderByTime(c.getId(),1);//评论评论的
			map.put(c,list);
		}
		return map;
	}
}
