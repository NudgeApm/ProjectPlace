package com.nla.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * HomePage controller entry point for the web site !
 * @author NLA
 *
 */
@Controller
public class HomePageController {

	/**
	 * HOME PAGE entry point for the web site
	 * link to
	 * homePage.jsp
	 * @param model
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.GET)	
	public String HomePageFirstPage(ModelMap model)
	{
		return "homeView";
	}
	
	@RequestMapping("/homePage.htm")
	public String redirect()
	{
		return "homeView";
	}

}

