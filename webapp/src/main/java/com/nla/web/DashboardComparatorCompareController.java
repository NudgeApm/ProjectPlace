package com.nla.web;

import com.nla.domain.Schema;
import com.nla.service.CentralBaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class DashboardComparatorCompareController extends AbstractController {

    @Autowired
    private CentralBaseListService centralBaseListService;

    /**
     * link to:
     * actionPlanBuilderPage.jsp
     *
     * @param model
     * @return
     */
    @RequestMapping("/dashboardComparatorCompare.htm")
    public String redirect(ModelMap model, @RequestParam("schemaName") String pSchemaName, @RequestParam("schemaNameRef") String pSchemaNameRef) {
        Schema schemaRef = null;
        Schema schemaComp = null;
        if (centralBaseListService.getListSchemaCb() != null) {
            for (Schema s : centralBaseListService.getListSchemaCb()) {
                if (s.getName().equals(pSchemaName)) {
                    schemaComp = s;
                }
                if (s.getName().equals(pSchemaNameRef)) {
                    schemaRef = s;
                }
            }

        }


        centralBaseListService.compareSchema(schemaComp, schemaRef);

        model.addAttribute("schema", schemaComp);
        model.addAttribute("schemaRef", schemaRef);
        model.addAttribute("breadcrumbs", getBreadcrumbs());

        return "dashboardComparatorResultView";
    }

    private String getBreadcrumbs() {
        ArrayList<String> chemin = new ArrayList<String>();
        ArrayList<String> valeur = new ArrayList<String>();
        chemin.add("homePage.htm");
        chemin.add("NULL");
        valeur.add("Home");
        valeur.add("Dashboard Comparator");
        return getBreadcrumbsTemplate(chemin, valeur);
    }
}
