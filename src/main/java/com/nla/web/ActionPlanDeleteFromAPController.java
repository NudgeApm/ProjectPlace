package com.nla.web;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.nla.domain.Metric;
import com.nla.domain.Violation;
import com.nla.service.CentralBaseListService;
import com.nla.service.MetricListService;

@Controller
public class ActionPlanDeleteFromAPController extends AbstractController{
	
	private CentralBaseListService centralBaseListService;
	private MetricListService metricListService;
	
	@Autowired
	public void setCastLogService(MetricListService pMetricListService) {
		this.metricListService = pMetricListService;
	}
	@Autowired
	public void setCastLogService(CentralBaseListService pCentralBaseService) {
		this.centralBaseListService = pCentralBaseService;
	}
	
	/**
	 * link to:
	 * actionPlanSummaryPage.jsp
	 * @param model
	 * @param pSchema
	 * @param pMetricID
	 * @param pObjID
	 * @return
	 */
	@RequestMapping("/actionPlanDeleteFromAP.htm")
	public String redirect(ModelMap model, @RequestParam("schemaName") String pSchema, @RequestParam("metricID") String pMetricID, @RequestParam("ObjID") String pObjID)
	{	
		centralBaseListService.deleteFromAP(pSchema,pMetricID,pObjID);
		ArrayList<Metric> metricList = centralBaseListService.getActionListFromCB(pSchema);
		model.addAttribute("metricList",metricList);
		model.addAttribute("schemaName",pSchema);
		return "actionPlanSummaryView";
	}
	
	/**
	 * link to
	 * actionPlanDetailMetricPage.jsp
	 * @param model
	 * @param snapshotIDslashappliID
	 * @param pSchema
	 * @param pMetricID
	 * @param pObjID
	 * @return
	 */
	@RequestMapping("/actionPlanDeleteFromAPFromDetailMetric.htm")
	public String redirectDetailMetric(ModelMap model, @RequestParam("snapshotIDslashappliID") String snapshotIDslashappliID, @RequestParam("schemaName") String pSchema, @RequestParam("metricID") String pMetricID, @RequestParam("ObjID") String pObjID)
	{	
		centralBaseListService.deleteFromAP(pSchema,pMetricID,pObjID);
		model.addAttribute("schemaName",pSchema);
		model.addAttribute("snapshotIDslashappliID",snapshotIDslashappliID);
		ArrayList<Violation> violationList = metricListService.getListViolation(pSchema,snapshotIDslashappliID,Integer.valueOf(pMetricID));
		model.addAttribute("breadcrumbs",getBreadcrumbs(pSchema,snapshotIDslashappliID));
		model.addAttribute("violationList",violationList);
		model.addAttribute("metricID",pMetricID);
		
		
		return "actionPlanDetailMetricView";
	}
	
	private String getBreadcrumbs(String pSchemaName, String snapshotIDslastSnapID){
		ArrayList<String> chemin=new ArrayList<String>();
		ArrayList<String> valeur=new ArrayList<String>();			
		chemin.add("homePage.htm");
		chemin.add("actionPlanBuilder.htm");
		chemin.add("actionPlanMainResult.htm?schemaName="+pSchemaName+"&snapshotIDslashappliID="+snapshotIDslastSnapID);
		chemin.add("NULL");
		
		valeur.add("Home");
		valeur.add("Action Plan builder");
		valeur.add("Rule tracking");
		valeur.add("Metric details");
		return getBreadcrumbsTemplate(chemin,valeur);
	}
	
	
	
}
