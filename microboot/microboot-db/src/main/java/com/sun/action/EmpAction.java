package com.sun.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.action.abs.AbstractAction;
import com.sun.service.IEmpService;
import com.sun.vo.Emp;

@RestController
@RequestMapping("/emp/*")
public class EmpAction extends AbstractAction {
	@Resource
	private IEmpService empService;
	@RequestMapping("list")
	public void getAllEmp(HttpServletResponse response){
		List<Emp> list= new ArrayList();
		try {
			list = this.empService.list();
			super.printJSONList(response, list, "allEmps");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("emp")
	public void getEmp(HttpServletResponse response){
		try {
			
			System.out.println("查询的结果"+this.empService.getEmp(1));
			//super.printMsg(response, "sussess");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String getFlag() {
		// TODO Auto-generated method stub
		return null;
	}

}
