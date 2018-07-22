package com.sun.action.abs;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.sun.util.JSONUtil;

public abstract class AbstractAction {
	@Resource
	private MessageSource messageSource;
	//进行分页信息的处理
	private Integer cp=1;
	private Integer ls=3;
	//处理分页的参数
	public void handerSplit(HttpServletRequest request) {
		cp = Integer.parseInt(request.getParameter("cp"));
	}
	public Integer getCp() {
		return cp;
	}
	public Integer getLs() {
		return ls;
	}
	//读取资源文件信息
	public String getMsg(String key) {
		return this.messageSource.getMessage(key, new Object[] {this.getFlag()},Locale.getDefault());
	}
	/**
	 * 输出普通对象的信息
	 * @param response
	 * @param msg
	 */
	public void printMsg(HttpServletResponse response,Object msg) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(msg);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			out.flush();
			out.close();
		}
	}
	/**
	 * 输出json对象信息
	 * @param response
	 * @param obj
	 */
	public void printJSONObj(HttpServletResponse response,Object obj) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(JSONUtil.objToJson(obj));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			out.flush();
			out.close();
		}
	}
	/**
	 * 输出JSON数组
	 * @param response
	 * @param all
	 * @param jsonName
	 */
	public void printJSONList(HttpServletResponse response,List<?> all,String jsonName) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(JSONUtil.listToJson(all,jsonName));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			out.flush();
			out.close();
		}
	}
	/**
	 * 输出JSON数组和数量
	 * @param response
	 * @param all
	 * @param jsonName
	 * @param count
	 */
	public void printJSONList(HttpServletResponse response,List<?> all,String jsonName,int count) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(JSONUtil.listToJson(all,count,jsonName));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			out.flush();
			out.close();
		}
	}
	//时间格式的转换
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	public abstract String getFlag();
}
