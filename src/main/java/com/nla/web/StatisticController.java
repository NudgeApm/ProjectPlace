package com.nla.web;

import com.nla.domain.ViewCentralBaseList;
import com.nla.domain.ViewMngtBaseList;
import com.nla.service.CentralBaseListService;
import com.nla.service.ManagementBaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StatisticController {

    @Autowired
    private CentralBaseListService centralBaseListService;

    @Autowired
    private ManagementBaseListService managementBaseListService;

    @RequestMapping("/statistic.htm")
    public String redirect(ModelMap model) {
        ViewCentralBaseList cbList = new ViewCentralBaseList();
        model.addAttribute("cbList", cbList);
        centralBaseListService.loadSchema();
        cbList.setListeSchemaCb(centralBaseListService.getListSchemaCb());
        cbList.setListeSchemaKb(centralBaseListService.getListSchemaKb());

        ViewMngtBaseList mngtList = new ViewMngtBaseList();
        model.addAttribute("mngtList", mngtList);
        managementBaseListService.loadSchema();
        mngtList.setListeSchemaMngt(managementBaseListService.getListSchemaMNGT());

        return "statisticView";
    }
}
