package com.soft.gift.service;

import com.soft.gift.model.Comment;
import com.soft.gift.model.UserInfo;

import java.util.List;
import java.util.Map;

public interface CommentService{

	public Map<Comment, UserInfo> getCommentByGiftID(Integer gift_id);

	public Double getAverageSorceByGiftID(Integer gift_id);

	public List<Integer> getCommentNum(Integer gift_id);
}
