package com.sun.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sun.dao.IEmpDAO;
import com.sun.service.IEmpService;
import com.sun.vo.Emp;
@Service
public class EmpServiceImpl implements IEmpService{
	@Resource
	private IEmpDAO empDAO;

	@Override
	public List<Emp> list() throws Exception {
		return this.empDAO.findAllEmp();
	}

	@Override
	public Emp getEmp(Integer eid) throws Exception {
		return this.empDAO.findById(eid);
	}
	
}
