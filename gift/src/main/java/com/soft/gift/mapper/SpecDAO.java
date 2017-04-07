package com.soft.gift.mapper;

import com.soft.gift.model.Spec;
import com.soft.gift.util.BaseDAO;

import java.util.List;

public interface SpecDAO extends BaseDAO<Spec> {

	public List<String> getSpecNames(int i);
	
}
