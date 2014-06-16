package com.nla.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nla.domain.Metric;
import com.nla.domain.Schema;
import com.nla.service.CentralBaseListService;
import com.nla.service.MetricListService;


@Controller
public class DashboardComparatorDetailMetric extends AbstractController {
		
		private CentralBaseListService centralBaseListService;
		private MetricListService metricListService;
		
		@Autowired
		public void setCastLogService(CentralBaseListService pCentralBaseService, MetricListService pMetricListService) {
			this.centralBaseListService = pCentralBaseService;
			this.metricListService = pMetricListService;
		}
		
		/**
		 * link to:
		 * actionPlanBuilderPage.jsp
		 * @param model
		 * @return
		 */
		@RequestMapping("/dashboardComparatorDetailMetric.htm")
		public String redirect(ModelMap model, 
				@RequestParam("schemaName") String pSchemaName, 
				@RequestParam("schemaNameComp") String pSchemaNameComp,
				@RequestParam("snapshotIDslashappliID1") String snapshotIDslashappliID1,
				@RequestParam("snapshotIDslashappliID2") String snapshotIDslashappliID2) {
			Schema schemaName = null;
			Schema schemaComp = null;
			if(centralBaseListService.getListSchemaCb()!=null){
				for(Schema s: centralBaseListService.getListSchemaCb()){
					if(s.getName().equals(pSchemaName)){
						schemaName = s;
					}
					if(s.getName().equals(pSchemaNameComp)){
						schemaComp = s;
					}
				}

			}
			
			ArrayList<Metric> metricList = metricListService.getListMetricDiff(schemaName,schemaComp,snapshotIDslashappliID1,snapshotIDslashappliID2);
			
			metricListService.setHealthFactor(60014);
			model.addAttribute("metricList",metricList);		
			model.addAttribute("maxMetricWeight",metricListService.getMaxMetricWeight());
			model.addAttribute("maxTCWeight",metricListService.getMaxTCWeight());
			
			model.addAttribute("breadcrumbs",getBreadcrumbs());
			
			model.addAttribute("schemaName",pSchemaName);
			model.addAttribute("schemaNameComp",pSchemaNameComp);
			model.addAttribute("snapshotIDslashappliID",snapshotIDslashappliID1);
			
			return "dashboardComparatorMetricResultView";
		}
		
		private String getBreadcrumbs(){		
			ArrayList<String> chemin=new ArrayList<String>();
			ArrayList<String> valeur=new ArrayList<String>();		
			chemin.add("homePage.htm");
			chemin.add("NULL");		
			valeur.add("Home");
			valeur.add("Dashboard Comparator result");
			return getBreadcrumbsTemplate(chemin,valeur);		
		}
	}
