package com.nla.web;

import com.nla.domain.ViewCentralBaseList;
import com.nla.service.CentralBaseListService;
import com.nla.service.TransactionCityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class ActionByCityController extends AbstractController {


    private CentralBaseListService centralBaseListService;
    private TransactionCityService transactionCityService;
    
    @Autowired
    public void setLogxService(CentralBaseListService pCentralBaseService) {
        this.centralBaseListService = pCentralBaseService;
    }

    

    @Autowired
    public void setLogService(TransactionCityService pTransactionCityService) {
        this.transactionCityService = pTransactionCityService;
    }

    
    
    /**
     * link to:
     * actionPlanBuilderPage.jsp
     *
     * @param model
     * @return
     */
    @RequestMapping("/actionPlanBuilderxx.htm")
    public String redirect(ModelMap model) {
        ViewCentralBaseList cbList = new ViewCentralBaseList();
        model.addAttribute("cbList", cbList);
        model.addAttribute("breadcrumbs", getBreadcrumbs());
        if (centralBaseListService.getListSchemaCb() == null || centralBaseListService.getListSchemaCb().size() == 0) {
            centralBaseListService.loadSchema();
        }
        cbList.setListeSchemaCb(centralBaseListService.getListSchemaCb());

        return "actionPlanBuilderView";
    }

    private String getBreadcrumbs() {
        ArrayList<String> chemin = new ArrayList<String>();
        ArrayList<String> valeur = new ArrayList<String>();
        chemin.add("homePage.htm");
        chemin.add("NULL");
        valeur.add("Home");
        valeur.add("Action Plan builder");
        return getBreadcrumbsTemplate(chemin, valeur);
    }
    
    
    @RequestMapping("/runParis.htm")
    public String runParis(ModelMap model) {
        monThread mt = new monThread();
        mt.start();
        centralBaseListService.callToJMS();
        model.addAttribute("message", "ville active: paris");
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Paris"));
        return "salesByCityView";
        //return "homeView";

    }
    @RequestMapping("/runNewYork.htm")
    public String runNewYork(ModelMap model) {
    	transactionCityService.selectNewYork();
        model.addAttribute("message", "ville active: New York");
        model.addAttribute("sales", centralBaseListService.getSalesSummary("New York"));
        return "salesByCityView";
        //return "homeView";

    }
    
    @RequestMapping("/runTokio.htm")
    public String runTokio(ModelMap model) {
    	transactionCityService.getHighVolumeData();
    	//model.addAttribute("message", "ville active: Tokio");
    	model.addAttribute("sales", centralBaseListService.getSalesSummary("Tokio"));
        return "salesByCityView";
        //return "homeView";

    }
    
    @RequestMapping("/runChicago.htm")
    public String runChicago(ModelMap model) {
    	transactionCityService.getListAccount(1000);
        model.addAttribute("message", "ville active: Chicago");
        //return "homeView";
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Chicago"));
        return "salesByCityView";

    }
    
    @RequestMapping("/runHongKong.htm")
    public String runHongKong(ModelMap model) {
    	transactionCityService.getListAccount(100);
        model.addAttribute("message", "ville active: Hong-Kong");
        return "homeView";

    }

    @RequestMapping("/runMoscou.htm")
    public String runMoscou(ModelMap model) {
    	transactionCityService.getListAccount(10000);
        model.addAttribute("message", "ville active: Moscou");
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Moscou"));
        return "salesByCityView";
        //return "homeView";

    }

    @RequestMapping("/runRio.htm")
    public String runRio(ModelMap model) {
    	centralBaseListService.selectOneColumnRecursifEntryPoint();
        model.addAttribute("message", "ville active: Rio de Janeiro");
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Rio de Janeiro"));
        return "salesByCityView";
        //return "homeView";
    }
    
    
    @RequestMapping("/runJohannesburg.htm")
    public String runJohannesburg(ModelMap model) {
    	transactionCityService.selectJohannesburgData();
        model.addAttribute("message", "ville active: johannesburg");
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Johannesburg"));
        return "salesByCityView";
        //return "homeView";
    }
    
    @RequestMapping("/runBuenoAires.htm")
    public String runBuenoAires(ModelMap model) {
    	transactionCityService.selectBuenoAiresData();
        model.addAttribute("message", "ville active: Bueno Aires");
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Bueno Aires"));
        return "salesByCityView";
        //return "homeView";
    }
    
    @RequestMapping("/runMumbai.htm")
    public String runMumbai(ModelMap model) {
    	transactionCityService.getOneSpecialTransaction();
    	model.addAttribute("message", "ville active: Mumbai");
    	model.addAttribute("sales", centralBaseListService.getSalesSummary("Mumbai"));
        return "salesByCityView";
        
    	//return "homeViewx";
    }
    
    /**
     * @param model
     * @return
     */
    @RequestMapping("/runSanFrancisco.htm")
    public String runSanFrancisco(ModelMap model) {
        model.addAttribute("message", "ville active: San Francisco");
        model.addAttribute("sales", centralBaseListService.getSalesSummary("San Francisco"));
        return "salesByCityView";
        
        //return "technicalAnalysisAdminInexistante";
    }
    /**
     * @param model
     * @return
     */
    @RequestMapping("/runMadrid.htm")
    public String runMadrid(ModelMap model) {
        model.addAttribute("message", "ville active: Madrid");
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Madrid"));
        //return "salesByCityView";
        
        return "technicalAnalysisAdminInexistante";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/runSydney.htm")
    public String runSydney(ModelMap model) {
    	transactionCityService.executeSydneyTransactions();
    	model.addAttribute("message", "ville active: Sydney");
    	model.addAttribute("sales", centralBaseListService.getSalesSummary("Sydney"));
        return "salesByCityView";
        
        //return "homeView";
    } 
 
    /**
     * @param model
     * @return
     */
    @RequestMapping("/runActivity.htm")
    public String runActivity(ModelMap model) {
    	centralBaseListService.insertData();
    	model.addAttribute("message", "augmentation de l'activité simulée");
        return "homeView";
    } 
    
}
