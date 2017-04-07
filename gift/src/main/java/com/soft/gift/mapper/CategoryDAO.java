package com.soft.gift.mapper;

import com.soft.gift.model.Category;
import com.soft.gift.util.BaseDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDAO extends BaseDAO<Category> {
	public List<Category> getMenu(@Param("p_id") Integer p_id);
}
