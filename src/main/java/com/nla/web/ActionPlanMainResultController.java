package com.nla.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.nla.domain.Metric;
import com.nla.domain.ObjectCAST;
import com.nla.service.CentralBaseListService;
import com.nla.service.MetricListService;



@Controller
public class ActionPlanMainResultController extends AbstractController {
	
	private MetricListService metricListService;
	private CentralBaseListService centralBaseListService;
	
	@Autowired
	public void setCastLogService(MetricListService pMetricListService, CentralBaseListService pCentralBaseService) {
		this.metricListService = pMetricListService;
		this.centralBaseListService = pCentralBaseService;
	}
	
	/**
	 * link to
	 * actionPlanSearchPage.jsp
	 * actionPlanMainResultPage.jsp
	 * @param model
	 * @param pSchemaName
	 * @param snapshotIDslashappliID
	 * @return
	 */
	@RequestMapping("/actionPlanMainResult.htm")
	public String redirect(ModelMap model, @RequestParam("schemaName") String pSchemaName, @RequestParam("snapshotIDslashappliID") String snapshotIDslashappliID) {
		setInformationHeader(pSchemaName, snapshotIDslashappliID, centralBaseListService, model);		
		
		
		ArrayList<Metric> metricList = metricListService.getListMetric(pSchemaName,snapshotIDslashappliID);
		// par defaut, on afifche la performance.
		metricListService.setHealthFactor(60014);
		model.addAttribute("metricList",metricList);		
		model.addAttribute("maxMetricWeight",metricListService.getMaxMetricWeight());
		model.addAttribute("maxTCWeight",metricListService.getMaxTCWeight());
		model.addAttribute("breadcrumbs",getBreadcrumbs());
		
		model.addAttribute("schemaName",pSchemaName);
		model.addAttribute("snapshotIDslashappliID",snapshotIDslashappliID);
		return "actionPlanMainResultView";
	}

	/**
	 * link to
	 * actionPlanMainResultObjPage.jsp
	 * actionPlanSearchObjPage.jsp
	 * @param model
	 * @param pSchemaName
	 * @param snapshotIDslashappliID
	 * @param hfForm
	 * @param weightForm
	 * @param thresholdtForm
	 * @param criticaltForm
	 * @param moduleForm
	 * @param objNameForm
	 * @param metricNameForm
	 * @param metricIDForm
	 * @return
	 */
	@RequestMapping("/actionPlanMainResultObj.htm")
	public String redirectObj(
			ModelMap model, 
			@RequestParam("schemaName") String pSchemaName, 
			@RequestParam("snapshotIDslashappliID") String snapshotIDslashappliID, 
			@RequestParam("hfForm") String hfForm,
			@RequestParam("weightForm") String weightForm,
			@RequestParam("thresholdtForm") String thresholdtForm,
			@RequestParam("criticaltForm") String criticaltForm,
			@RequestParam("moduleForm")String moduleForm,
			@RequestParam("objNameForm")String objNameForm,
			@RequestParam("metricNameForm")String metricNameForm,
			@RequestParam("metricIDForm")String metricIDForm) {
		ArrayList<ObjectCAST> objectList = metricListService.getListObjCAST(pSchemaName,snapshotIDslashappliID,hfForm,weightForm,thresholdtForm,criticaltForm,moduleForm,objNameForm,metricNameForm,metricIDForm);
		// par defaut, on afifche la performance.
		//metricListService.setHealthFactor(60014);
		model.addAttribute("objectList",objectList);		
		//model.addAttribute("maxMetricWeight",metricListService.getMaxMetricWeight());
		//model.addAttribute("maxTCWeight",metricListService.getMaxTCWeight());
		model.addAttribute("breadcrumbs",getBreadcrumbsObj(pSchemaName));
		
		model.addAttribute("schemaName",pSchemaName);
		model.addAttribute("snapshotIDslashappliID",snapshotIDslashappliID);
		return "actionPlanMainResultObjView";
	}

	
	
	@RequestMapping("/actionPlanMainResult2.htm")
	public @ResponseBody String redirect2(ModelMap model){
		return "<p>HTML TEST</p>";
	}
	
	@RequestMapping(value = "/time", method = RequestMethod.POST)
	public @ResponseBody String getTime(ModelMap model) {
	    String result = "Time for " ;
	    return result;
	}
	  
	private String getBreadcrumbs(){		
		ArrayList<String> chemin=new ArrayList<String>();
		ArrayList<String> valeur=new ArrayList<String>();
		
		chemin.add("homePage.htm");
		chemin.add("actionPlanBuilder.htm");
		chemin.add("NULL");
		
		valeur.add("Home");
		valeur.add("Action Plan builder");
		valeur.add("Rule tracking");
		return getBreadcrumbsTemplate(chemin,valeur);
	}

	private String getBreadcrumbsObj(String pSchemaName){		
		ArrayList<String> chemin=new ArrayList<String>();
		ArrayList<String> valeur=new ArrayList<String>();
		
		chemin.add("homePage.htm");
		chemin.add("actionPlanBuilder.htm");
		chemin.add("NULL");
		
		valeur.add("Home");
		valeur.add("Action Plan builder");
		valeur.add("Object Tracking("+pSchemaName+")");
		return getBreadcrumbsTemplate(chemin,valeur);
	}

	
		
}
