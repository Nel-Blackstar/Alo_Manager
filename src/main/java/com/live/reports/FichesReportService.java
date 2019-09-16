package com.live.reports;
import com.live.core.entities.Live;
import com.live.core.entities.Personnel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.stereotype.Service;

import com.live.paie.entities.BulletinPaie;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FichesReportService {
    public void generateBulletinsPdfReport(String entreprise, String adresse, String boitePostale, String telephone, String matricule, String nomEmployer, String prenomEmployer, String sexe, String description, String poste, String heurs, String entree, String base, String pvaleur, String pretenue, String ppayer, String dvaleurs, String dretenue, String dpayer, String avaleurs, String aretenue, String apayer, String pfvaleurs, String pfretenue, String pfpayer, String pvvaleurs, String pvretenue, String pvpayer, String reglement, String net, String nomConges, String congesNormale, String congesPlus, String congesPris, String congesRestant, Live live, Personnel personnel,BulletinPaie bulletinPaie, List<BulletinPaie> bulletinList) {
        //test
        try {
            InputStream bsReportStream = getClass().getResourceAsStream("/static/reports/fiche.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(bsReportStream);
            JRSaver.saveObject(jasperReport, "fiche.jasper");
            JasperPrint jasperPrint = pointCommun(entreprise, adresse,boitePostale,telephone,matricule,nomEmployer,prenomEmployer, sexe, description, poste, heurs, entree, base,pvaleur,pretenue,ppayer, dvaleurs, dretenue, dpayer,avaleurs,aretenue,apayer,pfvaleurs,pfretenue,pfpayer,pvvaleurs,pvretenue,pvpayer, reglement, net, nomConges, congesNormale, congesPlus, congesPris, congesRestant,live,personnel, jasperReport, bulletinList);
            JasperExportManager.exportReportToPdfFile(jasperPrint,  System.getProperty("user.home")+"/alo/rapports/fiche"+bulletinPaie.getId()+".pdf");
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    private JasperPrint pointCommun(String entreprise, String adresse,String boitePostale,String telephone,String matricule,String nomEmployer,String prenomEmployer, String sexe,String description,String poste,String heurs,String entree,String base,String pvaleurs,String pretenue,String ppayer,String dvaleurs,String dretenue,String dpayer,String avaleurs,String aretenue,String apayer,String pfvaleurs,String pfretenue,String pfpayer,String pvvaleurs,String pvretenue,String pvpayer,String reglement,String net,String nomConges,String congesNormale,String congesPlus,String congesPris,String congesRestant, Live live, Personnel personnel,JasperReport jasperReport, List<BulletinPaie> bulletins) throws JRException {
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(bulletins);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("entreprise", entreprise);
        parameters.put("adresse", adresse);
        parameters.put("boitePostale", boitePostale);
        parameters.put("telephone", telephone);
        parameters.put("matricule", matricule);
        parameters.put("nomEmployer", nomEmployer);
        parameters.put("prenomEmployer", prenomEmployer);
        parameters.put("description", description);
        parameters.put("sexe", sexe);
        parameters.put("poste", poste);
        parameters.put("heures", heurs);
        parameters.put("embauche", entree);
        parameters.put("base", base);
        parameters.put("pretsV", pvaleurs);
        parameters.put("pretsR", pretenue);
        parameters.put("pretsP", ppayer);
        parameters.put("dettesV", dvaleurs);
        parameters.put("dettesR", dretenue);
        parameters.put("dettesP", dpayer);
        parameters.put("avancesV", avaleurs);
        parameters.put("avancesR", aretenue);
        parameters.put("avancesP", apayer);
        parameters.put("primesFV", pfvaleurs);
        parameters.put("primesFR", pfretenue);
        parameters.put("primesFP", pfpayer);
        parameters.put("primesVV", pvvaleurs);
        parameters.put("primesVR", pvretenue);
        parameters.put("primesVP", pvpayer);
        parameters.put("reglement", reglement);
        parameters.put("net", net);
        parameters.put("nomConges", nomConges);
        parameters.put("congesNormale", congesNormale);
        parameters.put("congesPlus", congesPlus);
        parameters.put("congesPris", congesPris);
        parameters.put("congesRestant", congesRestant);
        parameters.put("logo", System.getProperty("user.home")+ "/alo/live/" + live.getPhoto());
        parameters.put("photo", System.getProperty("user.home")+ "/alo/personnels/" + personnel.getPhoto());
        return JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
    }
}
