package com.nla.web;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nla.domain.Application;
import com.nla.domain.Schema;
import com.nla.domain.SchemaCb;
import com.nla.domain.SchemaMNGT;
import com.nla.domain.Snapshot;
import com.nla.domain.Violation;
import com.nla.service.CentralBaseListService;
import com.nla.service.ManagementBaseListService;
import com.nla.service.MetricListService;



@Controller
public class ActionPlanDetailMetricController extends AbstractController{

		
		private MetricListService metricListService;
		private CentralBaseListService centralBaseListService;
		private ManagementBaseListService managementBaseListService; 
		
		
		@Autowired
		public void prepare(MetricListService pMetricListService,CentralBaseListService pCentralBaseService,ManagementBaseListService pManagementBaseListService) {
			this.metricListService = pMetricListService;
			this.centralBaseListService = pCentralBaseService;
			this.managementBaseListService = pManagementBaseListService;
		}
		
		/**
		 * link to
		 * actionPlanDetailMetricPage.jsp
		 * @param model
		 * @param pSchemaName
		 * @param snapshotIDslashappliID
		 * @param metricID
		 * @return
		 */
		@RequestMapping("/actionPlanDetailMetric.htm")
		public String redirect(ModelMap model, @RequestParam("schemaName") String pSchemaName, @RequestParam("snapshotIDslashappliID") String snapshotIDslashappliID, @RequestParam("metricID") String metricID) {
			setInformationHeader(pSchemaName, snapshotIDslashappliID, centralBaseListService, model);		
			
			
			
			ArrayList<Violation> violationList = metricListService.getListViolation(pSchemaName,snapshotIDslashappliID,Integer.valueOf(metricID));
			model.addAttribute("violationList",violationList);
			model.addAttribute("schemaName",pSchemaName);		
			model.addAttribute("metricID",metricID);
			model.addAttribute("snapshotIDslashappliID",snapshotIDslashappliID);
			model.addAttribute("breadcrumbs",getBreadcrumbs(pSchemaName,snapshotIDslashappliID));
			return "actionPlanDetailMetricView";
		}
		
		/**
		 * link to
		 * actionPlanDetailMetricPage.jsp
		 * @param model
		 * @param pSchemaName
		 * @param snapshotIDslashappliID
		 * @param metricID
		 * @param objID
		 * @param priority
		 * @param comment
		 * @return
		 */
		@RequestMapping("/actionPlanDetailMetricAddObj.htm")
		public String addObjToAP(
				ModelMap model, 
				@RequestParam("schemaName") String pSchemaName, 
				@RequestParam("snapshotIDslashappliID") String snapshotIDslashappliID, 
				@RequestParam("metricID") String metricID,
				@RequestParam("objID") String objID,
				@RequestParam("priority") String priority,
				@RequestParam("comment") String comment)
				
		{			
			metricListService.addToActionPlan(pSchemaName,snapshotIDslashappliID,Integer.valueOf(metricID),objID,priority,comment);
			ArrayList<Violation> violationList = metricListService.getListViolation(pSchemaName,snapshotIDslashappliID,Integer.valueOf(metricID));
			model.addAttribute("violationList",violationList);
			model.addAttribute("schemaName",pSchemaName);		
			model.addAttribute("metricID",metricID);
			model.addAttribute("snapshotIDslashappliID",snapshotIDslashappliID);
			model.addAttribute("breadcrumbs",getBreadcrumbs(pSchemaName,snapshotIDslashappliID));
			return "actionPlanDetailMetricView";
		}
		
		
		
		@RequestMapping("/actionPlanDetailMetricAddObjList.htm")
		public String actionPlanDetailMetricAddObjList(
				ModelMap model, 
				@RequestParam("schemaName") String pSchemaName, 
				@RequestParam("snapshotIDslashappliID") String snapshotIDslashappliID, 
				@RequestParam("metricID") String metricID,
				@RequestParam("objIDList") String objIDList,
				@RequestParam("priority") String priority,
				@RequestParam("comment") String comment)
				
		{			
			metricListService.addToActionPlan(pSchemaName,snapshotIDslashappliID,Integer.valueOf(metricID),objIDList,priority,comment);
			ArrayList<Violation> violationList = metricListService.getListViolation(pSchemaName,snapshotIDslashappliID,Integer.valueOf(metricID));
			model.addAttribute("violationList",violationList);
			model.addAttribute("schemaName",pSchemaName);		
			model.addAttribute("metricID",metricID);
			model.addAttribute("snapshotIDslashappliID",snapshotIDslashappliID);
			model.addAttribute("breadcrumbs",getBreadcrumbs(pSchemaName,snapshotIDslashappliID));
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
