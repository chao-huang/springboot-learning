package com.sun.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.action.abs.AbstractAction;
import com.sun.service.IDeptService;
import com.sun.vo.Dept;
@RestController
@RequestMapping("dept/*")
public class DeptAction extends AbstractAction {
	@Resource
	private IDeptService deptService;
	@RequestMapping("list")
	public void getDeptList(HttpServletResponse response){
		List<Dept> list = new ArrayList();
		try {
			list = this.deptService.list();
			super.printJSONList(response, list, "allDepts");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@RequestMapping("dept")
	public Dept getDept(){
		Dept dept = new Dept();
		try {
			System.out.println("查询成功"+this.deptService.getDept(1));
			dept = this.deptService.getDept(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dept;
	}
	@Override
	public String getFlag() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
