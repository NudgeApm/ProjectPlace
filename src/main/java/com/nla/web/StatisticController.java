package com.nla.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nla.domain.ViewCentralBaseList;
import com.nla.domain.ViewMngtBaseList;
import com.nla.service.CentralBaseListService;
import com.nla.service.ManagementBaseListService;

@Controller
public class StatisticController {

	private CentralBaseListService centralBaseListService;
	private ManagementBaseListService managementBaseListService; 
	
	@Autowired
	public void setCastLogService(CentralBaseListService pCentralBaseService) {
		this.centralBaseListService = pCentralBaseService;
	}
	
	@Autowired
	public void setManagementBaseListService(ManagementBaseListService pManagementBaseListService) {
		this.managementBaseListService = pManagementBaseListService;
	}
	
	@RequestMapping("/statistic.htm")
	public String redirect(ModelMap model)
	{
		ViewCentralBaseList cbList = new ViewCentralBaseList();
		model.addAttribute("cbList",cbList);
		centralBaseListService.loadSchema();
		cbList.setListeSchemaCb(centralBaseListService.getListSchemaCb());
		cbList.setListeSchemaKb(centralBaseListService.getListSchemaKb());
		
		ViewMngtBaseList mngtList = new ViewMngtBaseList();
		model.addAttribute("mngtList",mngtList);
		managementBaseListService.loadSchema();
		mngtList.setListeSchemaMngt(managementBaseListService.getListSchemaMNGT());
		
		return "statisticView";
	}
}
