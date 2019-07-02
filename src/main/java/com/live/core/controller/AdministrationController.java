package com.live.core.controller;

import com.live.core.entities.Live;
import com.live.core.repository.PersonnelRepository;
import com.live.core.repository.UsersRepository;
import com.live.core.service.*;
import com.live.moniteur.entities.Chapitre;
import com.live.moniteur.entities.Cours;
import com.live.moniteur.repository.ChapitreRepository;
import com.live.moniteur.repository.CoursRepository;
import com.live.moniteur.service.SessionFormationService;
import com.live.paie.entities.Banque;
import com.live.paie.service.BanqueService;
import com.live.rh.service.ApprenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdministrationController {
    @Autowired
    ChapitreRepository chapitreRepository;
    @Autowired
    CoursRepository coursRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PersonnelRepository personnelRepository;
    @Autowired
    PersonnelService personnelService;
    @Autowired
    UsersService usersService;
    @Autowired
    LiveService liveService;
    @Autowired
    ILiveManager iLiveManager;
    @Autowired
    RolesService rolesService;
    @Autowired
    BanqueService banqueService;
    @Autowired
    ApprenantService apprenantService;
    @Autowired
    SessionFormationService sessionFormationService;
    // Objet de cryptage et decryptage des mots de passe
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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
    public String listBanques(HttpSession session,Model model) {
        model.addAttribute("banques", banqueService.findAll());
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("banque", new Banque());
        return "administration/banques/index";
    }

    /** Ajout d'une banque
     *
     * @param model
     * @return
     */
    /**
     * <b> methode d'ajout d'une banque </b>
     * @param model
     * @param banque banque a ajouter
     * @return
     */
    @PostMapping(value = "/ajouter-banque")
    public String ajouterBanque(HttpSession session,Model model, Banque banque) {
        Banque banqueToSave = banqueService.save(banque);
        model.addAttribute("state", "post");
        model.addAttribute("info",banqueToSave.getNom()+" - "+banqueToSave.getTelephone());
        session.setAttribute("infos","La nouvelle banque vien d'�tre cr�e!!");
        return "redirect:/admin/banques";
    }
    /**
    * Modification de la banque
    * @param model
    * @param id identifiant de la banque
    * @return
    */
    @RequestMapping("/update-banque/{id}")
    public String editeBanque(HttpSession session,Model model,@PathVariable long id) {
   	Banque banque = banqueService.findOne(id);
   	if (session.getAttribute("infos") != null){
        model.addAttribute("info",session.getAttribute("infos"));
        session.removeAttribute("infos");
    }
   	model.addAttribute("banque", banque);
   	return "administration/banques/update";

   }

   /**
    * Modification des informations sur une banque
    * @param banque methode post
    * @return
    */
    @PostMapping(value = "/update-banque")
    public String saveUpdateBanque(HttpSession session,Banque banque) {
   	banqueService.save(banque);
   	session.setAttribute("infos","Modification terminer avec succes!!");
   	return "redirect:/admin/banques";

   }
    /**
     * Suppression d'une banque
     * @param id identifiant de la banque
     * @return
     */
    @RequestMapping("/delete-banque/{id}")
    public String deleteBanque(HttpSession session,@PathVariable long id) {
    	Banque banque = banqueService.findOne(id);
    	banqueService.delete(banque);
    	session.setAttribute("infos","suppression terminer avec succes!!");
    	return "redirect:/admin/banques";

    }

    /**
     * Toutes les methodes de gestion des cours
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/cours")
    public String Cours(HttpSession session,Model model) {
        model.addAttribute("listeCours", coursRepository.findAll());
        chargerLive(model);
        //chargement de la liste du personnel
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("state", "get");
        model.addAttribute("cours", new Cours());
        return "administration/formations/cours/index";
    }

    @PostMapping(value = "/cours/ajouter-cours")
    public String saveCours(HttpSession session,Cours cours) {
        coursRepository.save(cours);
        session.setAttribute("infos","Enregistrement du cours effectuer avec success");
        return "redirect:/admin/cours";
    }
    @RequestMapping("/update-cours/{id}")
    public String editCours(HttpSession session,Model model,@PathVariable long id) {
        Cours cours = coursRepository.getOne(id);
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("cours", cours);
        return "administration/formations/cours/update";

    }
    @PostMapping(value = "/cours/update-cours")
    public String UpdateCours(HttpSession session,Cours cours) {
        coursRepository.save(cours);
        session.setAttribute("infos","Modification terminer avec succes!!");
        return "redirect:/admin/cours";
    }
    @RequestMapping("/cours/delete-cours/{id}")
    public String deleteCours(HttpSession session,@PathVariable long id) {
        Cours cours = coursRepository.getOne(id);
        coursRepository.delete(cours);
        session.setAttribute("infos","suppression terminer avec succes!!");
        return "redirect:/admin/cours";
    }
    /**
     * affichage des information sur le cour
     * @param session
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/consulter-cours/{id}")
    public String showCour(HttpSession session,Model model,@PathVariable long id) {
        Cours cours = coursRepository.getOne(id);
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("chapitre",new Chapitre());
        model.addAttribute("cours", cours);
        return "administration/formations/cours/view";
    }

    @PostMapping(value = "/cours/save-chapitre")
    public String saveChapter(HttpSession session,Chapitre chapitre,@RequestParam("id_cours") long cours) {
            chapitreRepository.save(chapitre);
            Cours cour=coursRepository.getOne(cours);
            List<Chapitre> chapitres = cour.getChapitres();
            chapitres.add(chapitre);
            cour.setChapitres(chapitres);
            coursRepository.save(cour);
            session.setAttribute("infos","Nouveau chapitre enregistré");
        return "redirect:/admin/consulter-cours/"+cours;
    }
}
