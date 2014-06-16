package com.nla.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nla.domain.ViewCentralBaseList;
import com.nla.service.CentralBaseListService;

@Controller
public class HomePageTechAnalysisAdmin {
	
	private CentralBaseListService centralBaseListService;
	
	@Autowired
	public void setCastLogService(CentralBaseListService pCentralBaseService) {
		this.centralBaseListService = pCentralBaseService;
	}
	
	/**
	 * link to
	 * homePageTechAnalysisAdmin.jsp
	 * @param model
	 * @return
	 */
	@RequestMapping("/homePageTechAnalysisAdmin.htm")
	public String redirect(ModelMap model)
	{
		ViewCentralBaseList cbList = new ViewCentralBaseList();
		model.addAttribute("cbList",cbList);
		centralBaseListService.loadSchema();
		cbList.setListeSchemaCb(centralBaseListService.getListSchemaCb());
		cbList.setListeSchemaKb(centralBaseListService.getListSchemaKb());
		return "homePageTechAnalysisAdminView";
	}
}
