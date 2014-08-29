package com.nla.web;

import com.nla.domain.ViewCentralBaseList;
import com.nla.service.CentralBaseListService;
import com.nla.service.TransactionCityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Paris"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Paris"));
        return "salesByCityView";
        //return "homeView";

    }
    @RequestMapping("/runNewYork.htm")
    public String runNewYork(ModelMap model) {
    	
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        transactionCityService.selectNewYork();
        model.addAttribute("message", "ville active: New York");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("New York"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("New York"));
        return "salesByCityView";
        //return "homeView";

    }
    
    
   	/**
   	 * @param model
  	 * @return
   	 */
    @RequestMapping("/calculAggregation.htm")
   	public String calculAggregation(ModelMap model)	{
    	transactionCityService.calculAggregation();
   		return "technicalAnalysisAdminView";
   	}
    
    @RequestMapping("/consultationMadrid.htm")
    public String consultationMadrid(ModelMap model) {
    	
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        transactionCityService.consultationMadrid();
        model.addAttribute("message", "ville active: Madrid");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Madrid"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Madrid"));
        return "salesByCityView";
    }
    
    @RequestMapping("/consultationMoscou.htm")
    public String consultationMoscou(ModelMap model) {
    	
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        transactionCityService.consultationMoscou();
        model.addAttribute("message", "ville active: Moscou");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Moscou"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Moscou"));
        return "salesByCityView";
    }
   
    @RequestMapping("/consultationLisbonne.htm")
    public String consultationLisbonne(ModelMap model) {
    	
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        model.addAttribute("message", "ville active: Lisbonne");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Lisbonne"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Lisbonne"));
        return "salesByCityView";
    }
    
    @RequestMapping("/consultationRome.htm")
    public String consultationRome(ModelMap model) {
    	
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        model.addAttribute("message", "ville active: Rome");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Rome"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Rome"));
        return "salesByCityView";
    }
    
    @RequestMapping("/consultationMarseille.htm")
    public String consultationMarseille(ModelMap model) {
    	
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        model.addAttribute("message", "ville active: Marseille");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Marseille"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Marseille"));
        return "salesByCityView";
    }
    
    
    @RequestMapping("/consultationMunich.htm")
    public String consultationMunich(ModelMap model) {
    	
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        model.addAttribute("message", "ville active: Munich");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Munich"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Munich"));
        return "salesByCityView";
    }
    
    
    @RequestMapping("/consultationBordeaux.htm")
    public String consultationBordeaux(ModelMap model) {
    	
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        model.addAttribute("message", "ville active: Bordeaux");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Bordeaux"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Bordeaux"));
        return "salesByCityView";
    }
    
    @RequestMapping("/consultationNantes.htm")
    public String consultationNantes(ModelMap model) {
    	
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        model.addAttribute("message", "ville active: Nantes");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Nantes"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Nantes"));
        return "salesByCityView";
    }
    
    @RequestMapping("/consultationBarcelone.htm")
    public String consultationBarcelone(ModelMap model) {
    	
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        model.addAttribute("message", "ville active: Barcelone");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Barcelone"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Barcelone"));
        return "salesByCityView";
    }
    
    
    @RequestMapping("/consultationLondres.htm")
    public String consultationLondres(ModelMap model) {
    	
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        model.addAttribute("message", "ville active: Londres");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Londres"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("londres"));
        return "salesByCityView";
    }
    
    
    @RequestMapping("/consultationNewYork.htm")
    public String consultationNewYork(ModelMap model) {
    	
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
        transactionCityService.consultationNewYork();
        model.addAttribute("message", "ville active: New York");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("New York"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("New York"));
        return "salesByCityView";
    }
    
    
    @RequestMapping("/runTokio.htm")
    public String runTokio(ModelMap model) {
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
    	transactionCityService.getHighVolumeData();
    	model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Tokio"));
    	model.addAttribute("sales", centralBaseListService.getSalesSummary("Tokio"));
        return "salesByCityView";
        
    }
    
    @RequestMapping("/runChicago.htm")
    public String runChicago(ModelMap model) {
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
    	transactionCityService.getListAccount(1000);
        model.addAttribute("message", "ville active: Chicago");
        //return "homeView";
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Chicago"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Chicago"));
        return "salesByCityView";

    }
    
    @RequestMapping("/runHongKong.htm")
    public String runHongKong(ModelMap model) {
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
    	transactionCityService.getListAccount(100);
        model.addAttribute("message", "ville active: Hong-Kong");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Hong Kong"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Hong Kong"));
        return "salesByCityView";

    }

    @RequestMapping("/runMoscou.htm")
    public String runMoscou(ModelMap model) {
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
    	transactionCityService.getListAccount(10000);
        model.addAttribute("message", "ville active: Moscou");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Moscou"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Moscou"));
        return "salesByCityView";
        //return "homeView";

    }

    @RequestMapping("/runRio.htm")
    public String runRio(ModelMap model) {
    	int nbContrat = centralBaseListService.getNumberContrat();
        int nbAccount = centralBaseListService.getNumberAccount();
        System.out.println("nombre de contrat ="+nbContrat);
        if( nbContrat == 0 && nbAccount == 0){
        	centralBaseListService.initDB();
        	centralBaseListService.insertData();
        	centralBaseListService.insertDataBatch();
        }
    	centralBaseListService.selectOneColumnRecursifEntryPoint();
        model.addAttribute("message", "ville active: Rio de Janeiro");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Rio de Janeiro"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Rio de Janeiro"));
        return "salesByCityView";
        //return "homeView";
    }
    

    @RequestMapping("/afficheDetail.htm")
    public String afficheDetail(ModelMap model, @RequestParam("idProduct") String pIdProduct, @RequestParam("idCity") String pIdCity) {
        model.addAttribute("message", "ville active: details");
        model.addAttribute("products",centralBaseListService.getNumberOfSaleByProductByCity(Integer.valueOf(pIdProduct), Integer.valueOf(pIdCity)));
        
        return "detailsSalesByCityView";
    }
    
    @RequestMapping("/afficheDetailVente.htm")
    public String afficheDetailVente(ModelMap model, @RequestParam("idProduct") String pIdProduct, @RequestParam("idCity") String pIdCity) {
        model.addAttribute("message", "ville active: details");
        model.addAttribute("products",centralBaseListService.getSalesByProductByCity(Integer.valueOf(pIdProduct), Integer.valueOf(pIdCity)));
        return "detailsSalesByCityView";

    }    
    
 
    
    @RequestMapping("/runMiami.htm")
    public String runMiami(ModelMap model) {
        model.addAttribute("message", "ville active: Miami");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Miami"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Miami"));
        return "salesByCityView";
    }
    
    
    @RequestMapping("/runRome.htm")
    public String runRome(ModelMap model) {
        model.addAttribute("message", "ville active: Rome");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Rome"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Rome"));
        return "salesByCityView";
        //return "homeView";
    }

    @RequestMapping("/runShanghai.htm")
    public String runShanghai(ModelMap model) {
        model.addAttribute("message", "ville active: Shanghai");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Shanghai"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Shanghai"));
        return "salesByCityView";
    }
    
    @RequestMapping("/runMunich.htm")
    public String runMunich(ModelMap model) {
        model.addAttribute("message", "ville active: Munich");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Munich"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Munich"));
        return "salesByCityView";
    }
    
    
    @RequestMapping("/runJohannesburg.htm")
    public String runJohannesburg(ModelMap model) {
    	transactionCityService.selectJohannesburgData();
        model.addAttribute("message", "ville active: johannesburg");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Johannesburg"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Johannesburg"));
        return "salesByCityView";
        //return "homeView";
    }
    
    @RequestMapping("/runBuenoAires.htm")
    public String runBuenoAires(ModelMap model) {
    	transactionCityService.selectBuenoAiresData();
    	
        model.addAttribute("message", "ville active: Bueno Aires");
        
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Bueno Aires"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Bueno Aires"));
        return "salesByCityView";
        //return "homeView";
    }
    
    @RequestMapping("/runMumbai.htm")
    public String runMumbai(ModelMap model) {
    	transactionCityService.getOneSpecialTransaction();
    	model.addAttribute("message", "ville active: Mumbai");
    	model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Mumbai"));
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
    	centralBaseListService.detailOnSanFrancisco();
        model.addAttribute("message", "ville active: San Francisco");
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("San Francisco"));
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
        model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Madrid"));
        model.addAttribute("sales", centralBaseListService.getSalesSummary("Madrid"));
        
        
        //return "salesByCityView"; correction mettre cette vue à la place pour faire pour les autres vues.
        
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
    	model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Sydney"));
    	model.addAttribute("sales", centralBaseListService.getSalesSummary("Sydney"));
        return "salesByCityView";
    } 

    /**
     * @param model
     * @return
     */
    @RequestMapping("/runSingapour.htm")
    public String runSingapour(ModelMap model) {
    	transactionCityService.executeSingapourTransactions();
    	model.addAttribute("message", "ville active: Singapour");
    	model.addAttribute("nbSales",centralBaseListService.getNumberOfSale("Singapour"));
    	model.addAttribute("sales", centralBaseListService.getSalesSummary("Singapour"));
        return "salesByCityView";
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
    
    /**
     * @param model
     * @return
     */
    @RequestMapping("/runConsolidationData.htm")
    public String runConsolidationData(ModelMap model) {
    	transactionCityService.consolidateData();
    	model.addAttribute("message", "augmentation de l'activité simulée");
        return "homeView";
    }
    
    
    
    /**
     * @param model
     * @return
     */
    @RequestMapping("/updateSalesValues.htm")
    public String updateSalesValues(ModelMap model) {
    	transactionCityService.updateDataSalesValue();
    	model.addAttribute("message", "augmentation de l'activité simulée");
        return "homeView";
    } 
    
    
    
}
