package com.sun.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sun.vo.Item;

@Mapper
public interface IItemDAO extends IDAO<Integer, Item> {

	/**
	 * 统计数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer getAllCount() throws Exception;
}
