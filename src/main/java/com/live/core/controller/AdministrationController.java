package com.live.core.controller;

import com.live.core.entities.Live;
import com.live.core.service.ILiveManager;
import com.live.core.service.LiveService;
import com.live.core.service.UsersService;
import com.live.paie.entities.Banque;
import com.live.paie.service.BanqueService;
import com.live.rh.entities.Apprenant;
import com.live.rh.service.ApprenantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdministrationController {
    @Autowired
    LiveService liveService;

    @Autowired
    ILiveManager iLiveManager;
    @Autowired
    UsersService usersService;
    @Autowired
    BanqueService banqueService;
    @Autowired
    ApprenantService apprenantService;

    // Rechercher l'autoecole et charger dans le modèle s'il existe, sinon, charger un autoecole par défaut
    public void chargerLive(Model model) {
        List<Live> lives = liveService.findAll();

        if (!lives.isEmpty()) {
            model.addAttribute("lives", lives.get(0));
        } else {
            Live default_live = new Live();
            model.addAttribute("live", default_live);
        }
    }

    // Ajout d'une autoecole
    @GetMapping("/ajouter-auto-ecole")
    public String formHotel(Model model) {
        model.addAttribute("user", iLiveManager.userConnecte());
        chargerLive(model);

        model.addAttribute("state", "get");
        model.addAttribute("live", new Live());
        return "administration/auto_ecole/ajouter-auto_ecole";
    }

    //gestion des hotels

    /**
     * <b> methode d'ajout d'un auto-ecole</b>
     * @param model
     * @param live
     * @return
     */
    @PostMapping(value = "/ajouter-autoecole")
    public String ajouterHotel(Model model, Live live) {
        model.addAttribute("user", iLiveManager.userConnecte());
        chargerLive(model);
        Live hotel1 = liveService.save(live);
        model.addAttribute("state", "post");
        model.addAttribute("info",live.getNom());
        return "redirect:/admin/auto_ecoles";
    }

    /**
     *     <b> Consultation d'un auto-ecole associes a ces batiments et ces etages</b>
     * @param model
     * @param id identifiant de l'auto-ecole
     * @return
     */
    @GetMapping("/consulter-auto-ecole/{id}")
    public String consulterLive(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", iLiveManager.userConnecte());
        chargerLive(model);

        // Retrouver l'hotel à consulter et la placer dans le modèle
        Live live = liveService.findOne(id);
        model.addAttribute("live", live);

        return "auto-ecoles/consulter-auto-ecole";
    }

    // Modification des informations sur l'auto-ecole

    /**
     *     <b>  Modification des informations sur l'auto-ecole </b>
     * @param model
     * @param id identifiant de l'auto-ecole
     * @return
     */
    @GetMapping("/editer-auto-ecole/{id_auto_ecole}")
    public String editerHotel(Model model, @PathVariable("id") long id) {
        Live live = liveService.findOne(id);
        model.addAttribute("state", "get");
        model.addAttribute("live", live);

        model.addAttribute("user", iLiveManager.userConnecte());
        chargerLive(model);
        return "auto-ecoles/ajouter-auto-ecole";
    }

    // Suppression d'une auto-ecole
    @GetMapping("/supprimer-auto-ecole/{id}")
    public String supprimerHotel(Model model, @PathVariable("id") long id) {
        liveService.delete(liveService.findOne(id));
        List<Live> lives = liveService.findAll();
        model.addAttribute("lives", lives);

        model.addAttribute("user", iLiveManager.userConnecte());
        chargerLive(model);
        return "redirect:/admin/auto-ecoles";
    }

    /**
     * Rechercher de l'ensemble des hotels
     * @param model
     * @return
     */
    @GetMapping("/auto-ecoles")
    public String detailHotel(Model model) {

        List<Live> lives = liveService.findAll();
        if(!lives.isEmpty()){

            // Récupérer le premier hotel existant
            model.addAttribute("lives", lives.get(0));
        }else{
            // Aucun hotel n'est enregistré
            Live live_fictif = new Live();
            live_fictif.setNom("Auto ecole par defaut");
            model.addAttribute("live", live_fictif);
        }

        return "administration/auto-ecoles/index";
    }

    /**
     * Rechercher de l'ensemble des banques du systeme
     * @param model
     * @return
     */
    @GetMapping("/banques")
    public String listBanques(Model model) {
        model.addAttribute("banques", banqueService.findAll());
        return "administration/banques/index";
    }

    /** Ajout d'une banque
     *
     * @param model
     * @return
     */
    @RequestMapping("/ajouter-banque")
    public String formBanque(Model model) {

        model.addAttribute("state", "get");
        model.addAttribute("banque", new Banque());
        return "administration/banques/create";
    }

    /**
     * <b> methode d'ajout d'une banque </b>
     * @param model
     * @param banque banque a ajouter
     * @return
     */
    @PostMapping(value = "/ajouter-banque")
    public String ajouterBanque(Model model, Banque banque) {
        Banque banqueToSave = banqueService.save(banque);
        model.addAttribute("state", "post");
        model.addAttribute("info",banqueToSave.getNom()+" - "+banqueToSave.getTelephone());
        return "redirect:/admin/banques";
    }
    /**
    * Modification de la banque
    * @param model
    * @param id identifiant de la banque
    * @return
    */
    @RequestMapping("/update-banque/{id}")
    public String editeBanque(Model model,@PathVariable long id) {
   	Banque banque = banqueService.findOne(id);
   	model.addAttribute("banque", banque);
   	return "administration/banques/update";

   }
   /**
    * Modification des informations sur une banque
    * @param model methode post
    * @param id identifiant de la banquet
    * @return
    */
    @PostMapping(value = "/update-banque")
    public String saveUpdateBanque(Banque banque) {
   	banqueService.save(banque);
   	return "redirect:/admin/banques";

   }
    /**
     * Suppression d'une banque
     * @param model
     * @param id identifiant de la banque
     * @return
     */
    @RequestMapping("/delete-banque/{id}")
    public String deleteBanque(@PathVariable long id) {
    	Banque banque = banqueService.findOne(id);
    	banqueService.delete(banque);
    	return "redirect:/admin/banques";

    }
}
