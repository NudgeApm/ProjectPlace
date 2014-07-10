package com.nla.web;

import com.nla.domain.ViewCentralBaseList;
import com.nla.domain.ViewMngtBaseList;
import com.nla.service.CentralBaseListService;
import com.nla.service.ManagementBaseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class TechnicalAnalysisAdminController extends AbstractController {

    @Autowired
    private CentralBaseListService centralBaseListService;
    @Autowired
    private ManagementBaseListService managementBaseListService;

    /**
     * link to
     * technicalAnalysisAdminPage.jsp
     *
     * @param model
     * @return
     */
    @RequestMapping("/technicalAnalysisAdmin.htm")
    public String redirect(ModelMap model) {
        centralBaseListService.loadApplications();
        return "technicalAnalysisAdminView";
    }


    /**
     * @param model
     * @return
     */
    @RequestMapping("/insertData.htm")
    public String insertData(ModelMap model) {
        centralBaseListService.insertData();
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
    @RequestMapping("/deleteTables.htm")
   	public String deleteTables(ModelMap model)	{
   		centralBaseListService.deleteTables();
   		return "technicalAnalysisAdminView";
   	}
    
    /**
     * @param model
     * @return
     */
    @RequestMapping("/selectOneColumnRecursif.htm")
    public String selectRecursif(ModelMap model) {
        centralBaseListService.selectOneColumnRecursifEntryPoint();
        return "technicalAnalysisAdminView";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/viewDoesntExist.htm")
    public String viewDoesntExist(ModelMap model) {
        //centralBaseListService.selectOneColumnRecursifEntryPoint();
        return "technicalAnalysisAdminInexistante";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/viewNullPointerException.htm")
    public String viewNullPointerException(ModelMap model) {
        centralBaseListService.generateNullPointerException();
        return "technicalAnalysisAdminInexistante";
    }


    /**
     * @param model
     * @return
     */
    @RequestMapping("/selectProduitCartesien.htm")
    public String selectProduitCartesien(ModelMap model) {
        centralBaseListService.selectCartesianProduct();
        return "technicalAnalysisAdminView";
    }


    /**
     * @param model
     * @return
     */
    @RequestMapping("/selectStar.htm")
    public String selectStar(ModelMap model) {
        centralBaseListService.selectStar();
        return "technicalAnalysisAdminView";
    }


    /**
     * @param model
     * @return
     */
    @RequestMapping("/selectOneColumnLoop.htm")
    public String selectOneColumnLoop(ModelMap model) {
        centralBaseListService.selectOneColumnLoop();
        return "technicalAnalysisAdminView";
    }


    /**
     * @param model
     * @return
     */
    @RequestMapping("/runThread.htm")
    public String runThread(ModelMap model) {

        monThread mt = new monThread();
        mt.start();
        return "technicalAnalysisAdminView";
    }


    /**
     * @param model
     * @return
     */
    @RequestMapping("/selectOneColumn.htm")
    public String selectOneColumn(ModelMap model) {
        centralBaseListService.selectOneColumn();
        return "technicalAnalysisAdminView";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/selectStar100Items.htm")
    public String selectStar100Items(ModelMap model) {
        //centralBaseListService.selectStar100(100);
        model.addAttribute("account", centralBaseListService.getListAccount(100));
        return "technicalAnalysisAdminAppView";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/selectStar1000Items.htm")
    public String selectStar1000Items(ModelMap model) {
        model.addAttribute("account", centralBaseListService.getListAccount(1000));
        return "technicalAnalysisAdminAppView";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/selectStar10000Items.htm")
    public String selectStar10000Items(ModelMap model) {
        model.addAttribute("account", centralBaseListService.getListAccount(10000));
        return "technicalAnalysisAdminAppView";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/pageQuiPlante.htm")
    public String pageQuiPlante(ModelMap model) {
        model.addAttribute("account", centralBaseListService.getListAccount(10000));
        return "pasDeMappingPourPageQuiPlante";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/erreur500Forward.htm")
    public String erreur500Forward(ModelMap model) {
        model.addAttribute("account", centralBaseListService.getListAccount(10000));
        return "pasDeMappingPourPageQuiPlante";
    }


    @RequestMapping("/technicalAnalysisAdminApp.htm")
    public String redirectApp(ModelMap model) {
        ViewCentralBaseList cbList = new ViewCentralBaseList();
        model.addAttribute("cbList", cbList);
        if (centralBaseListService.getListSchemaCb() == null || centralBaseListService.getListSchemaCb().size() == 0) {
            centralBaseListService.loadSchema();
            centralBaseListService.setMaxLocForAllApp();
        }
        cbList.setListeSchemaCb(centralBaseListService.getListSchemaCb());
        cbList.setListeSchemaKb(centralBaseListService.getListSchemaKb());

        ViewMngtBaseList mngtList = new ViewMngtBaseList();
        model.addAttribute("mngtList", mngtList);
        if (managementBaseListService.getListSchemaMNGT() == null || managementBaseListService.getListSchemaMNGT().size() == 0) {
            managementBaseListService.loadSchema();
        }
        mngtList.setListeSchemaMngt(managementBaseListService.getListSchemaMNGT());

        model.addAttribute("breadcrumbs", getBreadcrumbsApp());

        model.addAttribute("applications", centralBaseListService.getListApplication());
        model.addAttribute("NBapplications", centralBaseListService.getListApplication().size());
        model.addAttribute("NBCentral", centralBaseListService.getListSchemaCb().size());
        model.addAttribute("NBManagementBase", managementBaseListService.getListSchemaMNGT().size());

        return "technicalAnalysisAdminAppView";
    }

    @RequestMapping("/technicalAnalysisAdminMngtBase.htm")
    public String redirectMngtBase(ModelMap model) {
        ViewCentralBaseList cbList = new ViewCentralBaseList();
        model.addAttribute("cbList", cbList);
        if (centralBaseListService.getListSchemaCb() == null || centralBaseListService.getListSchemaCb().size() == 0) {
            centralBaseListService.loadSchema();
        }
        cbList.setListeSchemaCb(centralBaseListService.getListSchemaCb());
        cbList.setListeSchemaKb(centralBaseListService.getListSchemaKb());

        ViewMngtBaseList mngtList = new ViewMngtBaseList();
        model.addAttribute("mngtList", mngtList);
        if (managementBaseListService.getListSchemaMNGT() == null || managementBaseListService.getListSchemaMNGT().size() == 0) {
            managementBaseListService.loadSchema();
        }
        mngtList.setListeSchemaMngt(managementBaseListService.getListSchemaMNGT());

        model.addAttribute("breadcrumbs", getBreadcrumbsApp());

        model.addAttribute("applications", centralBaseListService.getListApplication());
        model.addAttribute("NBapplications", centralBaseListService.getListApplication().size());
        model.addAttribute("NBCentral", centralBaseListService.getListSchemaCb().size());
        model.addAttribute("NBManagementBase", managementBaseListService.getListSchemaMNGT().size());

        return "technicalAnalysisAdminMngtBaseView";
    }

    @RequestMapping("/technicalAnalysisAdminCentralBase.htm")
    public String redirectCentralBase(ModelMap model) {
        ViewCentralBaseList cbList = new ViewCentralBaseList();
        model.addAttribute("cbList", cbList);
        if (centralBaseListService.getListSchemaCb() == null || centralBaseListService.getListSchemaCb().size() == 0) {
            centralBaseListService.loadSchema();
        }
        cbList.setListeSchemaCb(centralBaseListService.getListSchemaCb());
        cbList.setListeSchemaKb(centralBaseListService.getListSchemaKb());

        ViewMngtBaseList mngtList = new ViewMngtBaseList();
        model.addAttribute("mngtList", mngtList);
        if (managementBaseListService.getListSchemaMNGT() == null || managementBaseListService.getListSchemaMNGT().size() == 0) {
            managementBaseListService.loadSchema();
        }
        mngtList.setListeSchemaMngt(managementBaseListService.getListSchemaMNGT());

        model.addAttribute("breadcrumbs", getBreadcrumbsApp());

        model.addAttribute("applications", centralBaseListService.getListApplication());
        model.addAttribute("NBapplications", centralBaseListService.getListApplication().size());
        model.addAttribute("NBCentral", centralBaseListService.getListSchemaCb().size());
        model.addAttribute("NBManagementBase", managementBaseListService.getListSchemaMNGT().size());

        return "technicalAnalysisAdminCentralBaseView";
    }

    private String getBreadcrumbs() {
        ArrayList<String> chemin = new ArrayList<String>();
        ArrayList<String> valeur = new ArrayList<String>();
        chemin.add("homePage.htm");
        chemin.add("NULL");
        valeur.add("Home");
        valeur.add("Technical Analysis Administration");
        return getBreadcrumbsTemplate(chemin, valeur);
    }

    private String getBreadcrumbsApp() {
        ArrayList<String> chemin = new ArrayList<String>();
        ArrayList<String> valeur = new ArrayList<String>();
        chemin.add("homePage.htm");
        chemin.add("technicalAnalysisAdmin.htm");
        chemin.add("NULL");
        valeur.add("Home");
        valeur.add("Technical Analysis Administration");
        valeur.add("Applications");
        return getBreadcrumbsTemplate(chemin, valeur);
    }

}
