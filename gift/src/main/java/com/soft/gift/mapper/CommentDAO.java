package com.soft.gift.mapper;

import com.soft.gift.model.Comment;
import com.soft.gift.model.ScoreNum;
import com.soft.gift.util.BaseDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDAO extends BaseDAO<Comment> {
	public Double getAverSorceByGiftID(@Param("gift_id") Integer gift_id);
	public List<ScoreNum> getCommentNumClassifyBySorce(@Param("gift_id") Integer gift_id);
	public List<Comment> getCommentsOrderByTime(@Param("commented_id")Integer commented_id,@Param("level")Integer level);
}
