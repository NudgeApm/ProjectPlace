package com.nla.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nla.domain.Action;
import com.nla.domain.Metric;
import com.nla.service.CentralBaseListService;

@Controller
public class ActionPlanSummaryController extends AbstractController{
	
	private CentralBaseListService centralBaseListService;
	
	@Autowired
	public void setCastLogService(CentralBaseListService pCentralBaseService) {
		this.centralBaseListService = pCentralBaseService;
	}
	
	/**
	 * link to
	 * actionPlanSummaryPage.jsp
	 * @param model
	 * @param pSchemaName
	 * @return
	 */
	@RequestMapping("/actionPlanSummary.htm")
	public String redirect(ModelMap model, @RequestParam("schemaName") String pSchemaName)
	{
		ArrayList<Metric> metricList = centralBaseListService.getActionListFromCB(pSchemaName);
		model.addAttribute("metricList",metricList);
		model.addAttribute("schemaName",pSchemaName);
		model.addAttribute("breadcrumbs",getBreadcrumbs());
		return "actionPlanSummaryView";
	}
	
	private String getBreadcrumbs(){
		
		ArrayList<String> chemin=new ArrayList<String>();
		ArrayList<String> valeur=new ArrayList<String>();
		
		chemin.add("homePage.htm");
		chemin.add("actionPlanBuilder.htm");
		chemin.add("NULL");
		
		valeur.add("Home");
		valeur.add("Action Plan builder");
		valeur.add("Action Plan Summary");
		return getBreadcrumbsTemplate(chemin,valeur);
		
	}

}
