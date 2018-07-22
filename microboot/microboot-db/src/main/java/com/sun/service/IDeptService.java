package com.sun.service;

import java.util.List;

import com.sun.vo.Dept;

public interface IDeptService {
	
	public List<Dept> list() throws Exception;
	public Dept getDept(Integer did)throws Exception;
}
