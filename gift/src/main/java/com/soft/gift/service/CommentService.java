package com.soft.gift.service;

import com.soft.gift.model.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService{

	public List<Comment> getCommentByGiftID(Integer gift_id);

	public Double getAverageSorceByGiftID(Integer gift_id);

	public List<Integer> getCommentNum(Integer gift_id);

    public void addComment(Comment comment);

	public Comment getCommentByID(Integer commented_id);

	public Map<Comment,List<Comment>> getComments(Integer commented_id);

}
