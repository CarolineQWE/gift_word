package com.soft.gift.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * UserDAO�ӿڣ��̳�ͨ�õ�BaseDAO�ӿڣ�����ӵ��BaseDAO���������з���
 *
 * @param <T>
 */
public interface BaseDAO<T> extends Mapper<T>, MySqlMapper<T> {

}
