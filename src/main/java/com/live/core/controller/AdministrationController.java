package com.live.core.controller;

import com.live.common.service.DetailServiceimpl;
import com.live.core.entities.Live;
import com.live.core.repository.PersonnelRepository;
import com.live.core.service.*;
import com.live.moniteur.entities.*;
import com.live.moniteur.repository.ChapitreRepository;
import com.live.moniteur.repository.CoursRepository;
import com.live.moniteur.service.*;
import com.live.paie.entities.Banque;
import com.live.paie.service.BanqueService;
import com.live.rh.service.ApprenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdministrationController {
    @Autowired
    EvaluationService evaluationService;
    @Autowired
    SuivreService suivreService;
    @Autowired
    InscriptionService inscriptionService;
    @Autowired
    ChapitreService chapitreRepository;
    @Autowired
    CoursService coursRepository;
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

    // Rechercher l'auto ecole et charger dans le modele s'il existe, sinon, charger un auto ecole par défaut
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

        // Retrouver l'hotel à consulter et la placer dans le modele
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
            live_fictif.setNom("Auto-école par défaut");
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
    public String ajouterBanque(HttpSession session,Model model,@Valid Banque banque, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		return "administration/banques/index";
		}
        Banque banqueToSave = banqueService.save(banque);
        model.addAttribute("state", "post");
        model.addAttribute("info",banqueToSave.getNom()+" - "+banqueToSave.getTelephone());
        session.setAttribute("infos","La nouvelle banque vient d'être crée !!");
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
    public String saveUpdateBanque(HttpSession session,@Valid Banque banque, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		return "administration/banques/update";
		}
	   	banqueService.save(banque);
	   	session.setAttribute("infos","Modification terminer avec succès !!");
	   	return "redirect:/admin/banques";

   }
    /**
     * Suppression d'une banque
     * @param id identifiant de la banque
     * @return
     */
    @RequestMapping("/delete-banque/{id}")
    public String deleteBanque(HttpSession session,@PathVariable long id) {
        try {
            Banque banque = banqueService.findOne(id);
            banqueService.delete(banque);
            session.setAttribute("infos","Suppression terminer avec succès !!");
        }catch (Exception $e) {
            session.setAttribute("infos","Suppression non terminer plusieurs éléments ont été lier à cette banque !!");
        }
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
    	if(session.getAttribute("formationCourante")==null) {
    		return "redirect:/admin/formations";
    	}
    	SessionFormation formation = (SessionFormation) session.getAttribute("formationCourante");
        model.addAttribute("listeCours", coursRepository.findByFormation(formation));
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
    public String saveCours(HttpSession session,@Valid Cours cours, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		return "administration/formations/cours/index";
		}
    	if(session.getAttribute("formationCourante")==null) {
    		return "redirect:/admin/formations";
    	}
    	SessionFormation formation = (SessionFormation) session.getAttribute("formationCourante");
    	cours.setFormation(formation);
        coursRepository.save(cours);
        session.setAttribute("infos","Enregistrement du cours effectuer avec succès.");
        return "redirect:/admin/cours";
    }
    @RequestMapping("/update-cours/{id}")
    public String editCours(HttpSession session,Model model,@PathVariable long id) {
    	if(session.getAttribute("formationCourante")==null) {
    		return "redirect:/admin/formations";
    	}
        Cours cours = coursRepository.findOne(id);
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("cours", cours);
        return "administration/formations/cours/update";

    }
    @PostMapping(value = "/cours/update-cours")
    public String UpdateCours(HttpSession session,@Valid Cours cours, BindingResult bindingResult,@RequestParam("idform") Long  idform) {
    	if (bindingResult.hasErrors()) {
    		return "administration/formations/cours/update";
		}
    	if(session.getAttribute("formationCourante")==null) {
    		return "redirect:/admin/formations";
    	}
    	cours.setId((Long) cours.getId());
        cours.setFormation(sessionFormationService.findOne(idform));
        coursRepository.save(cours);
        session.setAttribute("infos","Modification terminer avec succès !!");
        return "redirect:/admin/cours";
    }
    @RequestMapping("/cours/delete-cours/{id}")
    public String deleteCours(HttpSession session,@PathVariable long id) {
    	if(session.getAttribute("formationCourante")==null) {
    		return "redirect:/admin/formations";
    	}
        Cours cours = coursRepository.findOne(id);
    	try {
            coursRepository.delete(cours);
            session.setAttribute("infos","Suppression terminer avec succès !!");
        }catch (Exception $e){
    	    System.out.println($e.getMessage());
            session.setAttribute("infos","Suppression non terminer car des actions y ont été effectuées !!");
        }

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
    	if(session.getAttribute("formationCourante")==null) {
    		return "redirect:/admin/formations";
    	}
        Cours cours = coursRepository.findOne(id);
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("chapitre",new Chapitre());
        model.addAttribute("cours", cours);
        return "administration/formations/cours/view";
    }

    @PostMapping(value = "/cours/save-chapitre")
    public String saveChapter(HttpSession session,@Valid Chapitre chapitre,@RequestParam("id_cours") long cours, BindingResult bindingResult) {
	    	if (bindingResult.hasErrors()) {
	    		return "administration/formations/cours/view";
			}
            String resumeTraiter=chapitre.getResume();
            resumeTraiter.replace("\n", "<br>");
            chapitre.setResume(resumeTraiter);
            chapitreRepository.save(chapitre);
            Cours cour=coursRepository.findOne(cours);
            List<Chapitre> chapitres = cour.getChapitres();
            chapitres.add(chapitre);
            cour.setChapitres(chapitres);
            coursRepository.save(cour);
            session.setAttribute("infos","Nouveau chapitre enregistré avec succès !!");
        return "redirect:/admin/consulter-cours/"+cours;
    }
    @RequestMapping("/cours/delete-chap/{id}/{courId}")
    public String deleteChapitre(HttpSession session,@PathVariable("id") long id,@PathVariable("courId") long id_cour) {
        Chapitre chapitre = chapitreRepository.findOne(id);
        Cours cours =coursRepository.findOne(id_cour);
        List<Chapitre> chaps=cours.getChapitres();
        chaps.remove(chapitre);
        cours.setChapitres(chaps);
        try{
            coursRepository.save(cours);
            chapitreRepository.delete(chapitre);
            session.setAttribute("infos","Suppression terminer avec succès !!");
        }catch (Exception $e) {
            System.out.println($e.getMessage());
            session.setAttribute("infos","Suppression non terminer car des opérations ont eu lieu sur ce chapitre !!");
        }
        return "redirect:/admin/consulter-cours/"+id_cour;
    }

    @GetMapping("/cours/suivie")
    public String suivieCours(HttpSession session,Model model) {
    	if(session.getAttribute("formationCourante")==null) {
    		return "redirect:/admin/formations";
    	}
        SessionFormation formation = (SessionFormation) session.getAttribute("formationCourante");
        model.addAttribute("listeCours", coursRepository.findByFormation(formation));
        chargerLive(model);
        //chargement de la liste du personnel
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("state", "get");
        model.addAttribute("listeInscri",inscriptionService.findInscriptionsByFormation(formation));
        model.addAttribute("listeApp",suivreService.findAll());
        model.addAttribute("suivre",new Suivre());
        return "administration/formations/cours/suivieCours";
    }
    @GetMapping("/cours/evaluation")
    public String evaluationCours(HttpSession session,Model model) {
    	if(session.getAttribute("formationCourante")==null) {
    		return "redirect:/admin/formations";
    	}
        SessionFormation formation = (SessionFormation) session.getAttribute("formationCourante");
        model.addAttribute("listeCours", coursRepository.findByFormation(formation));
        chargerLive(model);
        //chargement de la liste du personnel
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("state", "get");
        model.addAttribute("listeInscri",inscriptionService.findInscriptionsByFormation(formation));
        model.addAttribute("listeEvaluation",evaluationService.findAll());
        model.addAttribute("evaluation",new Evaluation());
        return "administration/formations/cours/suivieEvaluation";
    }
    @PostMapping("/cours/ajouter-suivieCours")
    public String addSuivie(HttpSession session,Suivre suivre){
        Boolean verif = false;
        List<Suivre> suivies = suivreService.findAllByInscription(suivre.getInscription());
        for (Suivre sui : suivies){
                if (sui.getChapitre()==suivre.getChapitre()){
                    verif=true;
                }
        }
        if(verif){
            session.setAttribute("infos","L'apprenant a déjà suivie ce cours, Merci de vérifier vous informations.");
        }else{
            Inscription inscrit = suivre.getInscription();
            List<Suivre> suivs=null;
            try {
                suivs=inscrit.getSuivres();
            }catch (Exception e){
                suivs=new ArrayList<Suivre>();
            }
            suivs.add(suivreService.save(suivre));
            inscrit.setSuivres(suivs);
            inscriptionService.save(inscrit);
            session.setAttribute("infos","Enregistrement du suivie de cours effectuer.");
        }
        return "redirect:/admin/cours/suivie";
    }
    @PostMapping("/cours/ajouter-evaluation")
    public String addEvaluation(HttpSession session, Evaluation evaluation){
            Inscription inscrit=evaluation.getInscription();
            List<Evaluation> evals=null;
            try {
                evals=inscrit.getEvaluations();
            }catch (Exception e){
                evals=new ArrayList<Evaluation>();
            }
            evals.add(evaluationService.save(evaluation));
            inscrit.setEvaluations(evals);
            inscriptionService.save(inscrit);
            session.setAttribute("infos","Enregistrement de l'évaluation de cours effectuer.");
        return "redirect:/admin/cours/evaluation";
    }
}
