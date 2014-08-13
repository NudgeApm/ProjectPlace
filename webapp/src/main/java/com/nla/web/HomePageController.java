package com.nla.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nla.service.CentralBaseListService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * HomePage controller entry point for the web site !
 *
 * @author NLA
 */
@Controller
public class HomePageController {

	@Autowired
    private CentralBaseListService centralBaseListService;
    
	
    /**
     * HOME PAGE entry point for the web site
     * link to
     * homePage.jsp
     *
     * @param model
     * @return String
     */
    @RequestMapping(method = RequestMethod.GET)
    public String HomePageFirstPage(ModelMap model) {
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
    	return "homeView";
    }

    @RequestMapping("/homePage.htm")
    public String redirect(ModelMap model) {
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        
        
        return "homeView";
    }

}

