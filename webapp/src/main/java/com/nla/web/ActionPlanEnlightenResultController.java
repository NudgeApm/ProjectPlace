package com.nla.web;

import com.nla.domain.Metric;
import com.nla.service.KnowledgeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ActionPlanEnlightenResultController extends AbstractController {

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    /**
     * link to
     * actionPlanDetailMetricEnlightenPage.jsp
     *
     * @param model
     * @param pSchemaName
     * @param enlightenViewID
     * @return
     */
    @RequestMapping("/actionPlanEnlightenResult.htm")
    public String redirect(ModelMap model, @RequestParam("schemaName") String pSchemaName, @RequestParam("snapshotIDslashappliID") String enlightenViewID) {
        ArrayList<Metric> metricList = knowledgeBaseService.getMetricFromEnlightenView(pSchemaName, Integer.valueOf(enlightenViewID));
        model.addAttribute("metricList", metricList);
        model.addAttribute("breadcrumbs", getBreadcrumbs(pSchemaName));
        model.addAttribute("schemaName", pSchemaName);
        return "actionPlanEnlightenResultView";
    }


    private String getBreadcrumbs(String pSchemaName) {
        ArrayList<String> chemin = new ArrayList<String>();
        ArrayList<String> valeur = new ArrayList<String>();

        chemin.add("homePage.htm");
        chemin.add("actionPlanBuilder.htm");
        chemin.add("NULL");

        valeur.add("Home");
        valeur.add("Action Plan builder");
        valeur.add("Enligthen View (" + pSchemaName + ")");
        return getBreadcrumbsTemplate(chemin, valeur);
    }
}
