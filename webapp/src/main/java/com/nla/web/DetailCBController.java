package com.nla.web;

import com.nla.service.CentralBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("log")
public class DetailCBController {

    @Autowired
    private CentralBaseService centralBaseService;

    /**
     * link to
     * detailCB.jsp
     *
     * @param model
     * @param pSchemaName
     * @return
     */
    @RequestMapping("/detailCBController.htm")
    public String getDetailCentral(ModelMap model, @RequestParam("schemaName") String pSchemaName) {
        List<String> sx = centralBaseService.getDetails((String) pSchemaName);
        model.addAttribute("snapshotAppli", sx);
        return "detailCBView";
    }

}
