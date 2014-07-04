package com.nla.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nla.dao.CentralBaseDAO;
import com.nla.domain.SchemaCb;
import org.springframework.stereotype.Component;

@Component
public class CentralBaseServiceImpl implements CentralBaseService{

	@Override
	public List<String> getDetails(final String pCentralBaseName) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		CentralBaseDAO cbDAO = (CentralBaseDAO) context.getBean("centralBaseDAO");
		return cbDAO.getTechno(pCentralBaseName); 
		 
	}

}
