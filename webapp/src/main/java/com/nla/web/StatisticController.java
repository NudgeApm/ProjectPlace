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
    
    /**
     * @param model
     * @return
     */
    @RequestMapping("/getTableSize.htm")
    public String insertData(ModelMap model) {
        int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        
        model.addAttribute("nbAccount", nbAccount);
        model.addAttribute("nbContrat", nbContrat);
        return "statisticView";
    }
    
    /**
     * @param model
     * @return
     */
    @RequestMapping("/insertDataBatch.htm")
    public String insertDataBatch(ModelMap model) {
        centralBaseListService.insertDataBatch();
        int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        
        model.addAttribute("nbAccount", nbAccount);
        model.addAttribute("nbContrat", nbContrat);
        return "statisticView";
    }
    
    /**
     * @param model
     * @return
     */
    @RequestMapping("/lowTransactionAccoringlyToVolumeOfData.htm")
    public String lowTransactionAccoringlyToVolumeOfData(ModelMap model) {
    	centralBaseListService.selectOneColumnSlowLoop();
        return "statisticView";
    } 
}
