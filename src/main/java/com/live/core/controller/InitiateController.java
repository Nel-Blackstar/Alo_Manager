package com.live.core.controller;

import com.live.core.entities.Live;
import com.live.core.service.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InitiateController {
    @Autowired
    LiveService liveService;
    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    protected static final SimpleDateFormat dateHeureFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");


    // Rechercher l'autoecole et charger dans le modèle s'il existe, sinon, charger un autoecole par défaut
    public Live chargerLive(Model model) {
        List<Live> lives = liveService.findAll();
        Live current_live = new Live();

        if (!lives.isEmpty()) {
            model.addAttribute("lives", lives.get(0));
            current_live = lives.get(0);
        } else {
            model.addAttribute("live", current_live);
        }
        return current_live;
    }

    /**
     *
     * @param date1 date de debut
     * @param date2 date de fin
     * @return
     */
    public float diff(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        float res = (diff / (1000*60*60*24));
        return res;
    }

    /**Formater une date :
     *
     */

    public static String formatterDate(Date date) {
        return dateFormat.format(date);
    }

    public static String formatterDateHeure(Date date) {
        return dateHeureFormat.format(date);
    }


    /** Ajouter/retrancher des jours à une date :
     *
     * @param date date cpurante
     * @param nbJour nombre de jour a ajouter si positif ou a soustraire
     * @return
     */
    public static Date ajouterRetirerJour(Date date, int nbJour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, nbJour);
        return cal.getTime();
    }

    /**
     * Pour retrancher des heures, il faut fournir un paramètre négatif au nombre d'heures.
     *
     *     Ajouter/retrancher des minutes à une date :
     *
     * @param date
     * @param nbHeure
     * @return
     */
    public static Date ajouterHeure(Date date, int nbHeure) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, nbHeure);
        return cal.getTime();
    }

    /**
     *
     * @param date
     * @param nbMinute
     * @return
     */
    public static Date ajouterMinute(Date date, int nbMinute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, nbMinute);
        return cal.getTime();
    }

    /**
     * Pour retrancher des minutes, il faut fournir un paramètre négatif au nombre de minutes.
     *
     *             Ajouter/retrancher des secondes à une date :
     * @param date
     * @param nbSeconde
     * @return
     */

    public static Date ajouterSeconde(Date date, int nbSeconde) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, nbSeconde);
        return cal.getTime();
    }

    public static Date ajouterJourHeureMinute(Date date,int nbJour,int nbHeure, int nbMinute) {
        System.out.println();
        System.out.println("> original: "+ formatterDateHeure(date));
        Date dMofJr = ajouterRetirerJour(date,nbJour);
        System.out.println();
        System.out.println("> valeur ajouter de : "+nbJour +"jours => "+ formatterDateHeure(dMofJr));
        //ajout des heure au jour modifier
        Date dMofHeure = ajouterHeure(dMofJr,nbHeure);
        System.out.println();
        System.out.println("> valeur ajouter de [jours - heures : "+nbJour+ "- "+nbHeure +"] => "+ formatterDateHeure(dMofHeure));
        //ajout des minutes au jour modifier
        Date dMofMinite = ajouterMinute(dMofHeure,nbMinute);

        System.out.println();
        System.out.println("> valeur ajouter de [jours - heures - Minutes: "+nbJour+ "- "+nbHeure+ " - "+ nbMinute +"] => "+ formatterDateHeure(dMofMinite));

        return dMofMinite;
    }


}
