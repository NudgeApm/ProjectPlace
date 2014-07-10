package com.nla.web;


import com.nla.domain.ViewCentralBaseList;
import com.nla.service.CentralBaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DashboardComparatorController extends AbstractController {

    @Autowired
    private CentralBaseListService centralBaseListService;

    /**
     * link to:
     * actionPlanBuilderPage.jsp
     *
     * @param model
     * @return
     */
    @RequestMapping("/dashboardComparator.htm")
    public String redirect(ModelMap model) {
        ViewCentralBaseList cbList = new ViewCentralBaseList();
        model.addAttribute("cbList", cbList);
        model.addAttribute("cbListRef", cbList);
        model.addAttribute("breadcrumbs", getBreadcrumbs());
        centralBaseListService.loadSchema();
        cbList.setListeSchemaCb(centralBaseListService.getListSchemaCb());
        return "dashboardComparatorBuilderView";
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
