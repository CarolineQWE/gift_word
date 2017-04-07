package com.soft.gift.mapper;

import com.soft.gift.model.ScSpec;
import com.soft.gift.model.Spec;
import com.soft.gift.util.BaseDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScSpecDAO extends BaseDAO<ScSpec> {
	public List<Spec> getSpecByShID(@Param("sh_id") String sh_id);
}
