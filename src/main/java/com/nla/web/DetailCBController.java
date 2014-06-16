package com.nla.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nla.service.CentralBaseService;

@Controller
@SessionAttributes("castLog")
public class DetailCBController {
	
	private CentralBaseService centralBaseService;
	
	@Autowired
	public void setCentralBaseService(CentralBaseService centralBaseService) {
		this.centralBaseService = centralBaseService;
	}
	
	/**
	 * link to
	 * detailCB.jsp
	 * @param model
	 * @param pSchemaName
	 * @return
	 */
	@RequestMapping("/detailCBController.htm")
	public String getDetailCentral(ModelMap model, @RequestParam("schemaName") String pSchemaName) {
		List<String> sx = centralBaseService.getDetails((String)pSchemaName);
		model.addAttribute("snapshotAppli",sx);
		return "detailCBView";
    }
	
//	@RequestMapping("/detailCBController.htm")
//	public String redirect(ModelMap model)
//	{	
//		model.addAttribute("test");
//		return "detailCB";
//	}
	
}
