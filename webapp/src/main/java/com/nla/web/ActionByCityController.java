package com.nla.web;

import com.nla.domain.ViewCentralBaseList;
import com.nla.service.CentralBaseListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class ActionByCityController extends AbstractController {


    private CentralBaseListService centralBaseListService;

    @Autowired
    public void setLogService(CentralBaseListService pCentralBaseService) {
        this.centralBaseListService = pCentralBaseService;
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
        model.addAttribute("message", "ville active: paris");
        return "homeView";

    }
    @RequestMapping("/runNewYork.htm")
    public String runNewYork(ModelMap model) {
    	centralBaseListService.selectStar();
        model.addAttribute("message", "ville active: New York");
        return "homeView";

    }
    
    @RequestMapping("/runTokio.htm")
    public String runTokio(ModelMap model) {
    	centralBaseListService.selectCartesianProduct();
        model.addAttribute("message", "ville active: Tokio");
        return "homeView";

    }
    
    @RequestMapping("/runChicago.htm")
    public String runChicago(ModelMap model) {
    	centralBaseListService.getListAccount(1000);
        model.addAttribute("message", "ville active: Chicago");
        return "homeView";

    }
    
    @RequestMapping("/runHongKong.htm")
    public String runHongKong(ModelMap model) {
    	centralBaseListService.getListAccount(100);
        model.addAttribute("message", "ville active: Hong-Kong");
        return "homeView";

    }

    @RequestMapping("/runMoscou.htm")
    public String runMoscou(ModelMap model) {
    	centralBaseListService.getListAccount(10000);
        model.addAttribute("message", "ville active: Moscou");
        return "homeView";

    }

    @RequestMapping("/runRio.htm")
    public String runRio(ModelMap model) {
    	centralBaseListService.selectOneColumnRecursifEntryPoint();
        model.addAttribute("message", "ville active: Rio de Janeiro");
        return "homeView";
    }
    
    
    @RequestMapping("/runJohannesburg.htm")
    public String runJohannesburg(ModelMap model) {
    	centralBaseListService.selectOneColumn();
        model.addAttribute("message", "ville active: johannesburg");
        return "homeView";
    }
    
    @RequestMapping("/runBuenoAires.htm")
    public String runBuenoAires(ModelMap model) {
    	centralBaseListService.selectOneColumnLoop();
        model.addAttribute("message", "ville active: Bueno Aires");
        return "homeView";
    }
    
    @RequestMapping("/runMumbai.htm")
    public String runMumbai(ModelMap model) {
    	centralBaseListService.selectOneColumnLoop();
    	centralBaseListService.getListAccount(10000);
    	model.addAttribute("message", "ville active: Mumbai");
        return "homeViewx";
    }
    
    /**
     * @param model
     * @return
     */
    @RequestMapping("/runSanFrancisco.htm")
    public String runSanFrancisco(ModelMap model) {
        centralBaseListService.selectOneColumnRecursifEntryPoint();
        model.addAttribute("message", "ville active: San Francisco");
        return "technicalAnalysisAdminInexistante";
    }
    /**
     * @param model
     * @return
     */
    @RequestMapping("/runMadrid.htm")
    public String runMadrid(ModelMap model) {
        centralBaseListService.generateNullPointerException();
        model.addAttribute("message", "ville active: Madrid");
        return "technicalAnalysisAdminInexistante";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/runSydney.htm")
    public String runSydney(ModelMap model) {
    	centralBaseListService.selectOneColumnSlowLoop();
    	model.addAttribute("message", "ville active: Sydney");
        return "homeView";
    } 
    
}
