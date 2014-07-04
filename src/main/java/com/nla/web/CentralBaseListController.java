package com.nla.web;

import com.nla.domain.ViewCentralBaseList;
import com.nla.service.CentralBaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/centralBaseList.htm")
@SessionAttributes("log")
public class CentralBaseListController extends AbstractController {

    @Autowired
    private CentralBaseListService centralBaseListService;

    /**
     * link to
     * homePageTechAnalysisAdmin.jsp
     *
     * @param model
     * @param pTypeAccess
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getCentralBaseList(ModelMap model, @RequestParam("typeAccess") String pTypeAccess) {
        if (TYPEACCESS_TECHANALYSISADMIN.equals(pTypeAccess)) {
            ViewCentralBaseList cbList = new ViewCentralBaseList();
            model.addAttribute("cbList", cbList);
            centralBaseListService.loadSchema();
            cbList.setListeSchemaCb(centralBaseListService.getListSchemaCb());
            cbList.setListeSchemaKb(centralBaseListService.getListSchemaKb());
            return "homePageTechAnalysisAdminView";

        }
        if (TYPEACCESS_ACTIONPLANBUILDER.equals(pTypeAccess)) {

        }
        if (TYPEACCESS_STATBENCHMARK.equals(pTypeAccess)) {

        }

        return "detailCB";
    }

}
