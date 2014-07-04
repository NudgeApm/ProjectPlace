package com.nla.service;

import com.nla.dao.CentralBaseDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CentralBaseServiceImpl implements CentralBaseService {

    @Override
    public List<String> getDetails(final String pCentralBaseName) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        CentralBaseDAO cbDAO = (CentralBaseDAO) context.getBean("centralBaseDAO");
        return cbDAO.getTechno(pCentralBaseName);

    }

}
