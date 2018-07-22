package com.sun.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.sun.service.IDeptService;
import com.sun.vo.Dept;
import com.sun.dao.IDAO;
import com.sun.dao.IDeptDAO;
import com.sun.db.DS;
@Service
public class DeptServiceImpl implements IDeptService {
	@Resource
	private IDeptDAO deptDAO;
	@Override
	@DS(value="datasource1")
	public List<Dept> list() throws Exception {
		return this.deptDAO.findAll();
	}
	@Override
	@DS(value="datasource2")
	public Dept getDept(Integer did) throws Exception {
		return this.deptDAO.findById(did);
	}
	
}
