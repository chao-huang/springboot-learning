package com.sun.service;

import java.util.List;

import com.sun.vo.Emp;

public interface IEmpService {
	public List<Emp> list() throws Exception;
	public Emp getEmp(Integer eid)throws Exception;
}
