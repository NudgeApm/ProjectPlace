package com.nla.web;

import com.nla.domain.ViewCentralBaseList;
import com.nla.service.CentralBaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageTechAnalysisAdmin {

    @Autowired
    private CentralBaseListService centralBaseListService;

    /**
     * link to
     * homePageTechAnalysisAdmin.jsp
     *
     * @param model
     * @return
     */
    @RequestMapping("/homePageTechAnalysisAdmin.htm")
    public String redirect(ModelMap model) {
        ViewCentralBaseList cbList = new ViewCentralBaseList();
        model.addAttribute("cbList", cbList);
        centralBaseListService.loadSchema();
        cbList.setListeSchemaCb(centralBaseListService.getListSchemaCb());
        cbList.setListeSchemaKb(centralBaseListService.getListSchemaKb());
        return "homePageTechAnalysisAdminView";
    }
}
