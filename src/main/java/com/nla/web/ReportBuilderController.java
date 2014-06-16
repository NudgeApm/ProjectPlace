package com.nla.web;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nla.domain.Schema;
import com.nla.domain.ViewCentralBaseList;
import com.nla.domain.ViewMngtBaseList;
import com.nla.service.CentralBaseListService;
import com.nla.service.ManagementBaseListService;

@Controller
public class ReportBuilderController extends AbstractController{

	private CentralBaseListService centralBaseListService;

	@Autowired
	public void setCastLogService(CentralBaseListService pCentralBaseService) {
		this.centralBaseListService = pCentralBaseService;
	}
	


	/**
	 * link to
	 * reportBuilderPage.jsp
	 * @param model
	 * @return
	 */
	@RequestMapping("/reportBuilder.htm")
	public String redirect(ModelMap model)
	{
		ViewCentralBaseList cbList = new ViewCentralBaseList();		
		cbList.setListeSchemaCb(centralBaseListService.getListSchemaCb());
		
		if(centralBaseListService.getListSchemaCb()==null){
			centralBaseListService.loadSchema();			
		}
		ArrayList<Schema> listSchema = deserialize();
		//*** load from project place MYSQL database
	//	centralBaseListService.loadApplications();
		model.addAttribute("cbList",cbList);
		model.addAttribute("breadcrumbs",getBreadcrumbs());		
		serialization(centralBaseListService.getListSchemaCb());
		return "reportBuilderView";
	}
	
	private void serialization(ArrayList<Schema> cbList){
		try {
			FileOutputStream fichier = new FileOutputStream("C:\\temp\\cbList.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(cbList);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<Schema> deserialize(){
		ArrayList<Schema> cbList = null;
		try {
			FileInputStream fichier = new FileInputStream("C:\\temp\\cbList.ser");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			cbList = (ArrayList<Schema>) ois.readObject();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cbList;
	}
	private String getBreadcrumbs(){		
		ArrayList<String> chemin=new ArrayList<String>();
		ArrayList<String> valeur=new ArrayList<String>();		
		chemin.add("homePage.htm");
		chemin.add("NULL");		
		valeur.add("Home");
		valeur.add("Custom report builder");
		return getBreadcrumbsTemplate(chemin,valeur);		
	}
}
