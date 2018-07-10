package com.sun.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sun.action.abs.AbstractAction;
import com.sun.service.IItemService;
import com.sun.tools.internal.ws.processor.model.Request;
import com.sun.vo.Item;

@RestController
@RequestMapping("item/*")
public class ItemAction extends AbstractAction{
	@Resource
	private IItemService itemService;
	@RequestMapping(value="/list")
	public void getAll(HttpServletResponse response){
		List list = null;
		try {
			list = (List) this.itemService.getAll().get("item");
			super.printJSONList(response, list, "item");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/getItems")
	public void getAllItem(HttpServletResponse response){
		try {
			List list = this.itemService.getItemAll();
			super.printJSONList(response, list, "Items");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getFlag() {
		return null;
	}

}
