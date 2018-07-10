package com.sun.service;

import java.util.List;
import java.util.Map;

import com.sun.vo.Item;

public interface IItemService {
	/**
	 * 查询所有意见类别信息
	 * @return
	 */
	public Map<String,Object> getAll()throws Exception;
	/**
	 * 查询所有意见类别信息
	 * @return
	 */
	public List<Item> getItemAll()throws Exception;
}
