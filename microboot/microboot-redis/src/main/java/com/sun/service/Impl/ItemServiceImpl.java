package com.sun.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.sun.dao.IItemDAO;
import com.sun.redisservice.IRedisService;
import com.sun.service.IItemService;
import com.sun.vo.Item;

@Service
public class ItemServiceImpl implements IItemService {
	@Resource
	private IItemDAO itempDAO;
	@Resource
	private IRedisService redisService;
	@Override
	@Cacheable(cacheNames="items", keyGenerator="KeyGenerator")
	public Map<String, Object> getAll() throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("item", this.itempDAO.findAll());
		map.put("count", this.itempDAO.getAllCount());
		return map;
	}
	@Cacheable(cacheNames="allItem",keyGenerator="KeyGenerator")
	@Override
	public List<Item> getItemAll(){
		try {
			return this.itempDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean setRedis(){
		boolean flag = this.redisService.set("hello", "hello word");
		return flag;
	}
}
