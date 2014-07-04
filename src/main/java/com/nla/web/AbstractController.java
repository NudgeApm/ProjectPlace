package com.nla.web;

import com.nla.domain.Schema;
import com.nla.domain.SchemaCb;
import com.nla.domain.Snapshot;
import com.nla.service.CentralBaseListService;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class AbstractController {

    // from HOMEPAGE to TECHNICAL ANALYSIS ADMINISTRATION PAGES
    final String TYPEACCESS_TECHANALYSISADMIN = "TechAnalysisAdmin";

    // from HOMEPAGE to ACTION PLAN BUILDER PAGES
    final String TYPEACCESS_ACTIONPLANBUILDER = "APBuilder";

    // from HOMEPAGE to STATISTIC / BENCHMARK / QUALITY MODEL PAGES
    final String TYPEACCESS_STATBENCHMARK = "StatBenchmark";

    public String getBreadcrumbsTemplate(ArrayList<String> pChemin, ArrayList<String> pLabel) {
        String s = "<ul class='breadcrumb'>";

        for (int i = 0; i < pChemin.size(); i++) {
            String chemin = pChemin.get(i);
            String label = pLabel.get(i);

            if (!"NULL".equals(chemin)) {
                s = s + "<li><a href='" + chemin + "'>" + label + "</a> <span class='divider'>/</span></li>";
            } else {
                s = s + "<li>" + label + "</li>";
            }
        }
        s = s + "</ul>";
        return s;
    }

    public void setInformationHeader(String pSchemaName, String snapshotIDslashappliID, CentralBaseListService centralBaseListService, ModelMap model) {
        StringTokenizer st = new StringTokenizer(snapshotIDslashappliID, ",");
        String snapshotID = st.nextToken();
        String applicationID = st.nextToken();


        String appName = "";
        String snapName = "";
        if (centralBaseListService.getListSchemaCb() != null) {
            for (Schema s : centralBaseListService.getListSchemaCb()) {
                for (Snapshot snap : ((SchemaCb) s).getSnapshotList()) {
                    if (snap.getId() == Integer.valueOf(snapshotID) && s.getName().equals(pSchemaName)) {

                        appName = snap.getAppli().getName();
                        snapName = snap.getName();


                    }
                }
            }

        }
        model.addAttribute("schemaName", pSchemaName);
        model.addAttribute("snapshotName", snapName);
        model.addAttribute("appName", appName);
    }


}
