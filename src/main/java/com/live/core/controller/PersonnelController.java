package com.live.core.controller;
import com.live.common.entities.CodeValue;
import com.live.common.service.CodeValueService;
import com.live.core.entities.Partenaire;
import com.live.core.entities.Personnel;
import com.live.core.entities.Roles;
import com.live.core.entities.Users;
import com.live.core.repository.PersonnelRepository;
import com.live.core.repository.UsersRepository;
import com.live.core.service.*;
import com.live.moniteur.entities.Diplome;
import com.live.moniteur.entities.Inscription;
import com.live.moniteur.entities.SessionFormation;
import com.live.moniteur.service.DiplomeService;
import com.live.moniteur.service.InscriptionService;
import com.live.moniteur.service.SessionFormationService;
import com.live.paie.service.BanqueService;
import com.live.rh.entities.*;
import com.live.rh.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class PersonnelController extends InitiateController {
    @Autowired
    SortieService sortieService;
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
    CodeValueService codeValueService;
    @Autowired
    SessionFormationService sessionFormationService;
    @Autowired
    InscriptionService inscriptionService;
    @Autowired
    DiplomeService diplomeService;
    @Autowired
    PartenaireService partenaireService;
    @Autowired
    FournituresService fournitureService;
    @Autowired
    OffreService offreService;
    @Autowired
    PrevisionService previsionService;
    @Autowired
    RendezVousService rendezVousService;


    // Objet de cryptage et decryptage des mots de passe
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    //management du personnel

    /** Ajout d'un personnel
     *
     * @param model
     * @return
     */
    //gestion des personnelss

    /**
     * <b> methode d'ajout d'un utilisateur </b>
     * @param model
     * @param personnel personnel a ajouter
     * @return
     */
    @PostMapping(value = "/personnels/ajouter-personnel")
    public String ajouterPersonnel(HttpSession session,Model model,@Valid Personnel personnel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
       	 return "administration/personnels/index";
	 	}
        chargerLive(model);
        Personnel personnel1 = personnelService.save(personnel);
        model.addAttribute("state", "post");
        session.setAttribute("infos","Le personnel"+personnel1.getNom()+" - "+personnel1.getEmail()+" vient d'etre cree !!");
        model.addAttribute("info",personnel1.getNom()+" - "+personnel1.getEmail());
        return "redirect:/admin/personnels";
    }

    /**
     *     <b> Modifiction des information d'un personnel du system  </b>
     * @param model
     * @param id identified of personnel
     * @return
     */
    @RequestMapping("/personnels/modifier-personnel/{id}")
    public String consulterPersonnel(HttpSession session,Model model, @PathVariable("id") Long id) {
        chargerLive(model);
        Personnel personnel = personnelService.findOne(id);
        model.addAttribute("banques", banqueService.findAll());
        model.addAttribute("personnel", personnel);
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        return "administration/personnels/update";
    }

    /**
     * Rechercher de l'ensemble des utilisateurs du systeme
     * @param model
     * @return
     */
    @RequestMapping("/personnels")
    public String listPersonnel(HttpSession session,Model model) {
        model.addAttribute("listePersonnels", personnelService.findAll());
        //model.addAttribute("user", iHotelManager.userConnecte());
        chargerLive(model);
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("state", "get");
        model.addAttribute("personnel", new Personnel());
        model.addAttribute("banques", banqueService.findAll());
        return "administration/personnels/index";
    }

    //management des utilisateurs
    
    /**
     * <b> methode de modification du personnel </b>
     * @param model
     * @param personnel personnel a ajouter
     * @return
     */
    @PostMapping(value = "/personnels/modifier-personnel")
    public String modifierPersonnel(HttpSession session,Model model,@Valid Personnel personnel,String date, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
       	 	return "administration/personnels/update";
	 		}
    	@SuppressWarnings("deprecation")
		Date date_naissance=new Date();
		try {
			date_naissance = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	personnel.setDate_naissance(date_naissance);
        chargerLive(model);
        personnelService.save(personnel);
        session.setAttribute("infos","Modification terminer avec succes!!");
        return "redirect:/admin/personnels";
	    }
	    
	    /** Suppression d'un personnel
	    *
	    * @param model
	    * @param id identified of personnel person
	    * @return
	    */
	   @RequestMapping("/personnels/supprimer-personnel/{id}")
	   public String supprimerPersonnel(HttpSession session,Model model, @PathVariable("id") long id) {
	       personnelService.delete(personnelService.findOne(id));
	       session.setAttribute("infos","suppression terminer avec succes!!");
	       return "redirect:/admin/personnels";
	   }

    /** Ajout d'un user
     *
     * @param model
     * @return
     */
    //gestion des userss

    /**
     * <b> methode d'ajout d'un utilisateur </b>
     * @param model
     * @param users utilisateur a ajouter
     * @return
     */
    @PostMapping(value = "/users/ajouter-user")
    public String ajouterUser(HttpSession session, Model model,@Valid Users users, @RequestParam("role") List<String> role, @RequestParam("vmdp") String vmdp, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
       	 return "administration/utilisateurs/index";
	 	}
        //model.addAttribute("user", iHotelManager.userConnecte());
        chargerLive(model);
        List<String> erreur= new ArrayList<>();
        Users user = new Users();
        user.setLogin(users.getLogin());
        user.setActive(true);
        user.setUsername(users.getUsername());

        // Crypter le mot de passe utilisateur
        user.setPassword(encoder.encode(users.getPassword()));

        // Attribuer les rôles à l'utilisateur
      if (!vmdp.equals(users.getPassword())){
            erreur.add("les mots de passe saisie ne sont pas identique \n");
      }
        if (usersService.findByLogin(users.getLogin()) != null){
            erreur.add("Un utilisateur possedant se login existe deja dans le systeme \n");
        }
        if(!erreur.isEmpty()){
            session.setAttribute("infos",erreur);
            return  "redirect:/admin/users/ajouter-user";
        }else{
            List<Roles> roles = new ArrayList<>();
            for (String roleb:role) {
                Roles r=rolesService.findOne(roleb);
                roles.add(r);
            }
            // Enregistrer l'utilisateur
            Users savedUser = usersService.save(user);
            if(!roles.isEmpty()){
                // Le rôle selectionné existe dans le système
                savedUser.setRoles(roles);
                usersService.save(savedUser);
            }
            model.addAttribute("state", "post");
            session.setAttribute("infos","Utilisateur cree, Login: "+user.getLogin() +" -- Nom :  "+user.getUsername());
            return "redirect:/admin/users";
        }
    }

    /**
     *     <b> Consultation d'un utilisateur </b>
     * @param model
     * @param login identifiant de l'users
     * @return
     */
    @RequestMapping("/users/consulter-user/{id}")
    public String consulterUser(Model model, @PathVariable("id") String login) {
        //model.addAttribute("user", iLiveManager.userConnecte());
        chargerLive(model);
        // Retrouver l'users à consulter et le placer dans le modèle
        Users users = usersRepository.findUsersByLogin(login);
        model.addAttribute("users", users);
        return "administration/utilisateurs/view";
    }

    // Modification des informations sur l'users

    /**
     *     <b>  Modification des informations sur l'utilisateur</b>
     * @param model
     * @param login identifiant de l'utilisateur
     * @return
     */
    @GetMapping("/users/editer-user/{id}")
    public String editerUser(HttpSession session,Model model, @PathVariable("id") String login) {
        Users users = usersRepository.findUsersByLogin(login);
        model.addAttribute("state", "get");
        model.addAttribute("users", users);
        model.addAttribute("listePersonnels", personnelService.findAll());
        model.addAttribute("listeRoles", rolesService.findAll());
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        //model.addAttribute("user", iLiveManager.userConnecte());
        chargerLive(model);
        return "administration/utilisateurs/update";
    }

    @PostMapping(value = "/users/editer-user")
    public String editUser(HttpSession session,Model model,@Valid Users users, @RequestParam("role") List<String> role,@RequestParam("vmdp") String vmdp, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
       	 return "administration/utilisateurs/update";
	 	}
        List<String> erreur= new ArrayList<>();
        Users user = new Users();
        user.setLogin(users.getLogin());
        user.setActive(true);
        user.setUsername(users.getUsername());

        // Crypter le mot de passe utilisateur
        user.setPassword(encoder.encode(users.getPassword()));

        // Attribuer les rôles à l'utilisateur
        if (!vmdp.equals(users.getPassword())){
            erreur.add("les mots de passe saisie ne sont pas identique \n");
        }
        if(!erreur.isEmpty()){
            session.setAttribute("infos",erreur);
            return  "redirect:/admin/users/ajouter-user";
        }else {
            List<Roles> roles = new ArrayList<>();
            for (String roleb : role) {
                Roles r = rolesService.findOne(roleb);
                roles.add(r);
            }
            // Enregistrer l'utilisateur
            Users savedUser = usersService.save(user);
            if (!roles.isEmpty()) {
                // Le rôle selectionné existe dans le système
                savedUser.setRoles(roles);
                usersService.save(savedUser);
            }
        }
        model.addAttribute("state", "post");
        session.setAttribute("infos",user.getLogin() +" - "+user.getUsername()+" mis a jour");
        return "redirect:/admin/users";
    }

    // Suppression d'une users
    @RequestMapping("/users/supprimer-user/{id}")
    public String supprimerUser(HttpSession session,Model model, @PathVariable("id") String login) {
        usersService.delete(usersRepository.findUsersByLogin(login));
        session.setAttribute("infos","l'utilisateur "+login+" a ete supprimer avec success");
        chargerLive(model);
        return "redirect:/admin/users";
    }

    /**
     * Rechercher de l'ensemble des utilisateurs du systeme
     * @param model
     * @return
     */
    @GetMapping("/users")
    public String listUsers(HttpSession session,Model model) {
        model.addAttribute("listeUsers", usersService.findAll());
        chargerLive(model);
        // Charger la liste des rôles disponibles et déposer dans le model
        model.addAttribute("listeRoles", rolesService.findAll());
        //chargement de la liste du personnel
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        List<Personnel> listePersonnels=personnelRepository.findUsersAccount();
        model.addAttribute("listePersonnels",listePersonnels);
        model.addAttribute("state", "get");
        model.addAttribute("users", new Users());
        return "administration/utilisateurs/index";
    }
    /**
     * Rechercher de l'ensemble des Apprenants du systeme
     * @param model
     * @return
     */
    @GetMapping("/apprenants")
    public String listApprenant(HttpSession session,Model model) {
    	if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("state", "get");
    	model.addAttribute("listeApprenant", apprenantService.findAll());
        model.addAttribute("apprenant", new Apprenant());
        return "administration/apprenants/index";
    }
    /**
     * M�thode d'ajout d'un Apprenant post
     * @param model
     * @return
     */
    @PostMapping(value = "/ajouter-apprenant")
    public String ajouterApprenant(HttpSession session,Model model,@Valid Apprenant apprenant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
       	 return "administration/apprenants/index";
	 	}
        Apprenant apprenantToSave = apprenantService.save(apprenant);
        model.addAttribute("state", "post");
        model.addAttribute("info",apprenantToSave.getNom()+" - "+apprenantToSave.getTelephone_1());
        session.setAttribute("infos","Ajout "+apprenantToSave.getNom()+" - "+apprenantToSave.getTelephone_1()+" terminer avec succes!!");
        return "redirect:/admin/apprenants";
    }
    /**
     * Modification des informations sur un Apprenant
     * @param model
     * @param id identifiant de l'apprenant
     * @return
     */
    @RequestMapping("/update-apprenant/{id}")
    public String editeApprenant(HttpSession session,Model model,@PathVariable long id) {
    	Apprenant apprenant = apprenantService.findOne(id);
    	model.addAttribute("apprenant", apprenant);
    	if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
    	return "administration/apprenants/update";

    }
    /**
     * Modification des informations sur un Apprenant
     * @return
     */
    @PostMapping(value = "/update-apprenant")
    public String saveUpdateApprenant(HttpSession session,@Valid Apprenant apprenant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
       	 return "administration/apprenants/update";
	 	}
		session.setAttribute("infos","Modification terminer avec succ�s!!");
    	apprenantService.save(apprenant);
    	return "redirect:/admin/apprenants";

    }

    @GetMapping("/apprenants/view/{id}")
    public String viewApprenant(HttpSession session,Model model,@PathVariable("id") long id) {
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("state", "get");
        model.addAttribute("apprenant", apprenantService.findOne(id));
        model.addAttribute("inscriptions",inscriptionService.findInscriptionsByApprenant(apprenantService.findOne(id)));
        return "administration/apprenants/show";
    }

    /**
     * Suppression d'un apprenant
     * @param id identifiant de l'apprenant
     * @return
     */
    @RequestMapping("/delete-apprenant/{id}")
    public String deleteApprenant(HttpSession session,@PathVariable long id) {
    	Apprenant apprenant = apprenantService.findOne(id);
    	apprenantService.delete(apprenant);
    	session.setAttribute("infos","suppr�ssion terminer avec succ�s!!");
    	return "redirect:/admin/apprenants";

    }
    //La formation
    /**
     * Rechercher de l'ensemble des utilisateurs du systeme
     * @param model
     * @return
     */
    @RequestMapping("/formations")
    public String listFormation(HttpSession session,Model model) {
        model.addAttribute("listeFormations", sessionFormationService.findAll());
        chargerLive(model);
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        return "administration/formations/index";
    }
    /** 
     * M�thode d'ajout d'un Apprenant get
     * @param model
     * @return
     */
     @GetMapping("/ajouter-formation")
     public String formFormation(HttpSession session,Model model) {
     	if (session.getAttribute("infos") != null){
             model.addAttribute("info",session.getAttribute("infos"));
             session.removeAttribute("infos");
         }
     	List<CodeValue> typePermis = codeValueService.findByIdentifier("type_permis");
        model.addAttribute("state", "get");
        model.addAttribute("sessionFormation", new SessionFormation());
        model.addAttribute("permis", typePermis);
        return "administration/formations/create";
     }
     /**
      * M�thode d'ajout d'un Apprenant post
      * @param model
      * @return
      */
     @PostMapping(value = "/ajouter-formation")
     public String ajouterFormation(HttpSession session,Model model,@Valid SessionFormation formation, BindingResult bindingResult) {
         if (bindingResult.hasErrors()) {
        	 return "administration/formations/create";
	 		}
         sessionFormationService.save(formation);
         model.addAttribute("state", "post");
         session.setAttribute("infos","Nouvelle session de formation configuer�e avec succ�s!!");
         return "redirect:/admin/formations";
     }
     /**
      * Modification des informations sur un Apprenant
      * @param model
      * @param id identifiant de l'apprenant
      * @return
      */
     @RequestMapping("/update-formation/{id}")
     public String editeFormation(HttpSession session,Model model,@PathVariable long id) {
     	SessionFormation formation = sessionFormationService.findOne(id);
     	model.addAttribute("formation", formation);
     	if (session.getAttribute("infos") != null){
             model.addAttribute("info",session.getAttribute("infos"));
             session.removeAttribute("infos");
         }
     	return "administration/formations/update";

     }
     /**
      * Modification des informations sur la formation
      * @return
      */
     @PostMapping(value = "/update-formation")
     public String saveUpdateFormation(HttpSession session,@Valid SessionFormation formation, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
           	 return "administration/formations/update";
    	}
    	session.setAttribute("infos","Nouvelle session de formation modifier avec succ�s!!");
     	sessionFormationService.save(formation);
     	return"redirect:/admin/formations";

     }
     //Affichage de la session de formation 
     @RequestMapping("/consulter-formation/{id}")
     public String ConsulterPeriode(Model model,@PathVariable long id,HttpSession session) {
    	SessionFormation formation = sessionFormationService.findOne(id);
    	session.setAttribute("formationCourante",formation);
      	model.addAttribute("formation", formation);
     	return "administration/formations/view";
     }
     //Gestion des apprenants dans la session de formation
     @GetMapping("/formation/apprenant")
     public String formationApprenant(HttpSession session,Model model) {
    	 if(session.getAttribute("formationCourante")==null) {
     		return "redirect:/admin/formations";
     	}
     	if (session.getAttribute("infos") != null){
             model.addAttribute("info",session.getAttribute("infos"));
             session.removeAttribute("infos");
         }
     	SessionFormation formation = (SessionFormation) session.getAttribute("formationCourante");
     	model.addAttribute("listeApprenant", apprenantService.findAll());
     	model.addAttribute("listeInscriptions", inscriptionService.findInscriptionsByFormation(formation));
     	model.addAttribute("permis", codeValueService.findByIdentifier("type_permis"));
     	model.addAttribute("formation", formation);
        model.addAttribute("inscription", new Inscription());
         return "administration/formations/apprenants/index";
     }
     @PostMapping(value = "/formation/apprenant")
     public String ajouterApprenantFormation(HttpServletRequest request,HttpSession session,Model model,@Valid Inscription inscription,@RequestParam("categoriePermis") Long categoriePermis, BindingResult bindingResult) {
         chargerLive(model);
         if (bindingResult.hasErrors()) {
        	 return "administration/formations/apprenants/index";
 		 }
         if(session.getAttribute("formationCourante")==null) {
     		return "redirect:/admin/formations";
     	}
         SessionFormation formation = (SessionFormation) session.getAttribute("formationCourante");
         CodeValue categorie=codeValueService.findById(categoriePermis);
         List<Inscription> listeInscrit=inscriptionService.findInscriptionsByFormation(formation);
         boolean n=false;
         for(Inscription inscrit : listeInscrit) {
        	 if(inscrit.getApprenant()==inscription.getApprenant()) {
        		 n=true;
        	 }
         }
         if(n==true) {
        	 session.setAttribute("infos","Echec du proc�ssus de cr�ation. Cet appr�nant a d�j� �t� inscrit a cette session de formation!");
         }else {
        	 Diplome diplome=new Diplome();
             diplome.setStatut(false);
             diplome.setCategoriePermis(categorie);
             diplomeService.save(diplome);
             inscription.setDiplome(diplome);
             inscription.setFormation(formation);
             inscriptionService.save(inscription);
             diplome.setInscrit(inscription);
             diplomeService.save(diplome);
             model.addAttribute("state", "post");
             session.setAttribute("infos","Proc�ssus de cr�ation terminer avec succ�s!"); 
         }
         return "redirect:/admin/formation/apprenant";
     }
     //suppression de l'inscription
     @GetMapping("/formation/delete-Inscription/{id}")
     public String deleteInscriFormation(HttpServletRequest request,Model model,HttpSession session,@PathVariable long id) {
    	 if(session.getAttribute("formationCourante")==null) {
     		return "redirect:/admin/formations";
     	}
    	Inscription inscription = inscriptionService.findOne(id);
     	Diplome diplome=diplomeService.findOne(inscription.getDiplome().getId());
     	diplome.setInscrit(null);
     	inscription.setDiplome(null);
     	inscriptionService.save(inscription);
     	diplomeService.save(diplome);
     	try {
     		inscriptionService.delete(inscription);
     	}catch(Exception $e) {
     		diplome.setInscrit(inscription);
         	inscription.setDiplome(diplome);
         	inscriptionService.save(inscription);
         	diplomeService.save(diplome);
     		session.setAttribute("infos","suppr�ssion echouer car plusieurs opp�rations ont d�j� �t� �ffectuers!!");
     		return "redirect:/admin/formation/apprenant";
     	}
     	diplomeService.delete(diplome);
     	session.setAttribute("infos","suppr�ssion terminer avec succ�s!!");
        return "redirect:/admin/formation/apprenant";
     }
     //modification de l'inscription
     @RequestMapping("/formation/modifier-Inscription/{id}")
     public String editeCharge(HttpSession session,Model model,@PathVariable("id")  long id) {
    	if(session.getAttribute("formationCourante")==null) {
     		return "redirect:/admin/formations";
     	}
    	 SessionFormation formation = (SessionFormation) session.getAttribute("formationCourante");
    	 model.addAttribute("listeApprenant", apprenantService.findAll());
      	 model.addAttribute("listeInscriptions", inscriptionService.findInscriptionsByFormation(formation));
      	 model.addAttribute("permis", codeValueService.findByIdentifier("type_permis"));
      	 model.addAttribute("formation", formation);
         Inscription inscription = inscriptionService.findOne(id);
         model.addAttribute("inscription", inscription);
         if (session.getAttribute("infos") != null){
             model.addAttribute("info",session.getAttribute("infos"));
             session.removeAttribute("infos");
         }
         return "administration/formations/apprenants/update";
     }
	 //Gestion des diplomes dans la session de formation
	 @GetMapping("/formation/diplomes")
     public String listeDiplomlesFormation(HttpSession session,Model model) {
		if(session.getAttribute("formationCourante")==null) {
	    		return "redirect:/admin/formations";
	    	}
     	if (session.getAttribute("infos") != null){
             model.addAttribute("info",session.getAttribute("infos"));
             session.removeAttribute("infos");
         }
     	SessionFormation formation = (SessionFormation) session.getAttribute("formationCourante");
     	model.addAttribute("listeInscriptions", inscriptionService.findInscriptionsByFormation(formation));
        return "administration/formations/diplomes/index";
     }
	 @RequestMapping("/setDiplomeStatut/{id}")
     public String setDiplomeStatut(HttpSession session,Model model,@PathVariable("id")  long id) {
		 if(session.getAttribute("formationCourante")==null) {
	    		return "redirect:/admin/formations";
	    	}
		 Diplome diplome=null;
		 try {
    		diplome=diplomeService.findOne(id);
    	}catch(Exception $e) {
    		$e.printStackTrace();
    	}
    	if(diplome!=null) {
    		if(diplome.isStatut()==true) {
        		diplome.setStatut(false);
        	}else {
        		diplome.setStatut(true);
        	}
        	diplomeService.save(diplome);
            session.setAttribute("infos","Op�ration terminer avec succ�s!!");
            return "redirect:/admin/formation/diplomes";
    	}else {
    		session.setAttribute("infos"," Echec de l'op�ration!!");
            return "redirect:/admin/formation/diplomes";
    	}
     }
	 @RequestMapping("/partenaire")
     public String partenaire(HttpSession session,Model model) {
    	return "administration/partenaires/index";
     }
	 @RequestMapping("/partenaires/view")
     public String addPartenaire(HttpSession session,Model model) {
		 if (session.getAttribute("infos") != null){
             model.addAttribute("info",session.getAttribute("infos"));
             session.removeAttribute("infos");
         }
		 Partenaire partenaire=new Partenaire();
		 model.addAttribute(partenaire);
		 model.addAttribute("partenaires",partenaireService.findAll());
    	return "administration/partenaires/partenaires/index";
     }
	 @PostMapping(value = "/partenaires/save")
     public String savePartenaire(HttpSession session,@Valid Partenaire partenaire, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
           	 return "administration/partenaires/partenaires/index";
    	}
    	session.setAttribute("infos","Op�ration terminer avec succ�s!!");
     	partenaireService.save(partenaire);
     	return"redirect:/admin/partenaires/view";

     }
	 @RequestMapping("/partenaires/update/{id}")
     public String updatePartenaire(HttpSession session,Model model,@PathVariable("id")  long id) {
		 model.addAttribute("partenaire",partenaireService.findOne(id));
		 return "administration/partenaires/partenaires/update";
     }
	 @RequestMapping("/partenaires/delete/{id}")
     public String deletePartenaire(HttpSession session,Model model,@PathVariable("id")  long id) {
		 Partenaire partenaire=partenaireService.findOne(id);
		 try {
			 partenaireService.delete(partenaire);
			 session.setAttribute("infos","Op�ration terminer avec succ�s!!");
		 }catch(Exception $e) {
			 session.setAttribute("infos","Op�ration non �ffectuer ce partenaire existe et est lier a plusieurs op�rations!!");
		 }
		 return"redirect:/admin/partenaires/view";
     }
	 @RequestMapping("/partenaires/fournitures")
     public String addFornitures(HttpSession session,Model model) {
		 if (session.getAttribute("infos") != null){
             model.addAttribute("info",session.getAttribute("infos"));
             session.removeAttribute("infos");
         }
		 Fournitures fourniture=new Fournitures();
		 model.addAttribute(fourniture);
		 model.addAttribute("fournitures",fournitureService.findAll());
    	return "administration/partenaires/fournitures/index";
     }
	 @PostMapping(value = "/fourniture/save")
     public String saveFourniture(HttpSession session,@Valid Fournitures fourniture, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
           	 return "administration/partenaires/fournitures/index";
    	}
    	session.setAttribute("infos","Op�ration terminer avec succ�s!!");
     	fournitureService.save(fourniture);
     	return"redirect:/admin/partenaires/fournitures";

     }
	 @RequestMapping("/fourniture/update/{id}")
     public String updateFourniture(HttpSession session,Model model,@PathVariable("id")  long id) {
		 model.addAttribute("fourniture",fournitureService.findOne(id));
		 return "administration/partenaires/fournitures/update";
     }
	 @RequestMapping("/fourniture/delete/{id}")
     public String deleteFourniture(HttpSession session,Model model,@PathVariable("id")  long id) {
		 Fournitures fourniture=fournitureService.findOne(id);
		 try {
			 fournitureService.delete(fourniture);
			 session.setAttribute("infos","Op�ration terminer avec succ�s!!");
		 }catch(Exception $e) {
			 session.setAttribute("infos","Op�ration non �ffectuer cette fourniture existe et est lier a plusieurs op�rations!!");
		 }
		 return"redirect:/admin/partenaires/fournitures";
     }
	 @RequestMapping("/partenaires/offres")
     public String addoffres(HttpSession session,Model model) {
		 if (session.getAttribute("infos") != null){
             model.addAttribute("info",session.getAttribute("infos"));
             session.removeAttribute("infos");
         }
		 Offre offre=new Offre();
		 model.addAttribute("partenaires",partenaireService.findAll());
		 model.addAttribute("fournitures",fournitureService.findAll());
		 model.addAttribute(offre);
		 model.addAttribute("offres",offreService.findAll());
    	return "administration/partenaires/offres/index";
     }
	 @PostMapping(value = "/offres/save")
     public String saveOffres(HttpSession session,@Valid Offre offre, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
           	 return "administration/partenaires/offres/index";
    	}
    	session.setAttribute("infos","Op�ration terminer avec succ�s!!");
     	offreService.save(offre);
     	List<Offre> offres= new ArrayList<>();
     	offres.add(offre);
     	Partenaire partenaire=offre.getPartenaire();
     	if(!partenaire.getOffres().isEmpty()) {
     		for(Offre o : partenaire.getOffres()) {
         		offres.add(o);
         	}
     	}
     	partenaire.setOffres(offres);
     	try {
     		partenaireService.save(partenaire);
     	}catch(Exception $e) {
     		session.setAttribute("infos","Op�ration terminer avec succ�s!!");
     	}
     	return"redirect:/admin/partenaires/offres";

     }
	 @RequestMapping("/offres/update/{id}")
     public String updateOffre(HttpSession session,Model model,@PathVariable("id")  long id) {
		 model.addAttribute("offre",offreService.findOne(id));
		 model.addAttribute("partenaires",partenaireService.findAll());
		 model.addAttribute("fournitures",fournitureService.findAll());
		 return "administration/partenaires/offres/update";
     }
	 @RequestMapping("/offres/delete/{id}")
     public String deleteOffre(HttpSession session,Model model,@PathVariable("id")  long id) {
		 Offre offre=offreService.findOne(id);
		 List<Partenaire> partenaires=partenaireService.findAll();
		 if(!partenaires.isEmpty()) {
		 for(Partenaire p : partenaires) {
			  if(!p.getOffres().isEmpty()) {
				  List<Offre> rs=new ArrayList<>();
				 for(Offre r : p.getOffres()) {
					 if(!r.equals(offre)) {
						 rs.add(r);	
					 }
				 }
				 p.setOffres(rs);
				 try {
					 partenaireService.save(p);
					 session.setAttribute("infos","Op�ration terminer avec succ�s!!");
				 }catch(Exception $e) {
					 session.setAttribute("infos","Op�ration non �ffectuer cette pr�vision existe et est lier a plusieurs op�rations!!");
				 }
			  }
		  }
		 }
		 try {
			 offreService.delete(offre);
			 session.setAttribute("infos","Op�ration terminer avec succ�s!!");
		 }catch(Exception $e) {
			 session.setAttribute("infos","Op�ration non �ffectuer cette offre existe et est lier a plusieurs op�rations!!");
		 }
		 return"redirect:/admin/partenaires/offres";
     }
	 @RequestMapping("/partenaires/previsions")
     public String addprevision(HttpSession session,Model model) {
		 if (session.getAttribute("infos") != null){
             model.addAttribute("info",session.getAttribute("infos"));
             session.removeAttribute("infos");
         }
		 Prevision prevision=new Prevision();
		 model.addAttribute("partenaires",partenaireService.findAll());
		 model.addAttribute("previsions",previsionService.findAll());
		 model.addAttribute(prevision);
    	return "administration/partenaires/previsions/index";
     }
	 @PostMapping(value = "/previsions/save")
     public String savePrevision(HttpSession session,@Valid Prevision prevision, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
           	 return "administration/partenaires/previsions/index";
    	}
    	session.setAttribute("infos","Op�ration terminer avec succ�s!!");
     	previsionService.save(prevision);
     	List<Prevision> previsions= new ArrayList<>();
     	previsions.add(prevision);
     	Partenaire partenaire=prevision.getPartenaire();
     	if(!partenaire.getPrevisions().isEmpty()) {
     		for(Prevision p : partenaire.getPrevisions()) {
         		previsions.add(p);
         	}
     	}
     	partenaire.setPrevisions(previsions);
     	try {
     		partenaireService.save(partenaire);
     	}catch(Exception $e) {
     		session.setAttribute("infos","Op�ration terminer avec succ�s!!");
     	}
     	return"redirect:/admin/partenaires/previsions";

     }
	 @RequestMapping("/previsions/update/{id}")
     public String updateprevisions(HttpSession session,Model model,@PathVariable("id")  long id) {
		 model.addAttribute("prevision",previsionService.findOne(id));
		 model.addAttribute("partenaires",partenaireService.findAll());
		 return "administration/partenaires/previsions/update";
     }
	 @RequestMapping("/previsions/delete/{id}")
     public String deleteprevision(HttpSession session,Model model,@PathVariable("id")  long id) {
		 Prevision prevision=previsionService.findOne(id);
		 List<Partenaire> partenaires=partenaireService.findAll();
		 if(!partenaires.isEmpty()) {
		 for(Partenaire p : partenaires) {
			  if(!p.getPrevisions().isEmpty()) {
				  List<Prevision> rs=new ArrayList<>();
				 for(Prevision r : p.getPrevisions()) {
					 if(!r.equals(prevision)) {
						 rs.add(r);	
					 }
				 }
				 p.setPrevisions(rs);
				 try {
					 partenaireService.save(p);
					 session.setAttribute("infos","Op�ration terminer avec succ�s!!");
				 }catch(Exception $e) {
					 session.setAttribute("infos","Op�ration non �ffectuer cette pr�vision existe et est lier a plusieurs op�rations!!");
				 }
			  }
		  }
		 }
		 try {
			 previsionService.delete(prevision);
			 session.setAttribute("infos","Op�ration terminer avec succ�s!!");
		 }catch(Exception $e) {
			 session.setAttribute("infos","Op�ration non �ffectuer cette pr�visions existe et est lier a plusieurs op�rations!!");
		 }
		 return"redirect:/admin/partenaires/previsions";
     }
	 @RequestMapping("/partenaires/rendez-vous")
     public String addRendezVous(HttpSession session,Model model) {
		 if (session.getAttribute("infos") != null){
             model.addAttribute("info",session.getAttribute("infos"));
             session.removeAttribute("infos");
         }
		 RendezVous rendezVous=new RendezVous();
		 model.addAttribute("partenaires",partenaireService.findAll());
		 model.addAttribute("rendezVouss",rendezVousService.findAll());
		 model.addAttribute(rendezVous);
    	return "administration/partenaires/rendez_vous/index";
     }
	 @PostMapping(value = "/rendez-vous/save")
     public String saveRendezVous(HttpSession session,@Valid RendezVous rendezVous,@RequestParam("partenaire") List<Long> partenaireId, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
           	 return "administration/partenaires/rendez_vous/index";
    	}
    	session.setAttribute("infos","Op�ration terminer avec succ�s!!");
     	rendezVousService.save(rendezVous);
		for(Long pId : partenaireId) {
			List<RendezVous> rendez= new ArrayList<>();
	     	rendez.add(rendezVous);
    		Partenaire partenaire=partenaireService.findOne(pId);
    		if(!partenaire.getRendezVous().isEmpty()) {
    			for(RendezVous r : partenaire.getRendezVous()) {
        			rendez.add(r);
        		}
    		}
    		partenaire.setRendezVous(rendez);
    	try {
    		partenaireService.save(partenaire);
		}catch(Exception $e) {
     		session.setAttribute("infos","Op�ration terminer avec succ�s!!");
     	}
    	}
     	return"redirect:/admin/partenaires/rendez-vous";

     }
	 @RequestMapping("/rendez-vous/update/{id}")
     public String updateRendezVous(HttpSession session,Model model,@PathVariable("id")  long id) {
		 model.addAttribute("rendezVous",rendezVousService.findOne(id));
		 model.addAttribute("partenaires",partenaireService.findAll());
		 model.addAttribute("rendezVouss",rendezVousService.findAll());
		 return "administration/partenaires/rendez_vous/update";
     }
	 @PostMapping(value = "/rendez-vous/update")
     public String updateRendezVous(HttpSession session,@Valid RendezVous rendezVous, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
           	 return "administration/partenaires/rendez_vous/index";
    	}
		session.setAttribute("infos","Op�ration terminer avec succ�s!!");
     	rendezVousService.save(rendezVous);
     	return"redirect:/admin/partenaires/rendez-vous";

     }
	 @RequestMapping("/rendez-vous/delete/{id}")
	 public String deleteRendezVou(HttpSession session,Model model,@PathVariable("id")  long id) {
		 RendezVous rendezVous=rendezVousService.findOne(id);
		 List<Partenaire> partenaires=partenaireService.findAll();
		 if(!partenaires.isEmpty()) {
		 for(Partenaire p : partenaires) {
			  if(!p.getRendezVous().isEmpty()) {
				  List<RendezVous> rs=new ArrayList<>();
				 for(RendezVous r : p.getRendezVous()) {
					 if(!r.equals(rendezVous)) {
						 rs.add(r);	
					 }
				 }
				 p.setRendezVous(rs);
				 try {
					 partenaireService.save(p);
					 session.setAttribute("infos","Op�ration terminer avec succ�s!!");
				 }catch(Exception $e) {
					 session.setAttribute("infos","Op�ration non �ffectuer ce rendez-vous existe et est lier a plusieurs op�rations!!");
				 }
			  }
		  }
		 }
		 try {
			 rendezVousService.delete(rendezVous);
			 session.setAttribute("infos","Op�ration terminer avec succ�s!!");
		 }catch(Exception $e) {
			 session.setAttribute("infos","Op�ration non éffectuer ce rendez-vous existe et est lier a plusieurs op�rations!!");
		 }
		 return"redirect:/admin/partenaires/rendez-vous";
     }

     // gestion des sortie de stock
    @RequestMapping("/partenaires/sorties")
    public String saveSortie(HttpSession session,Model model) {
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("sortie",new Sortie());
        model.addAttribute("offres",offreService.findAll());
        model.addAttribute("sorties",sortieService.findAll());
        return "administration/partenaires/sorties/index";
    }

    @PostMapping(value = "/sortie/save")
    public String saveSortie(HttpSession session,Sortie sortie) {
        try {
            sortie.setType("sortie");
            sortieService.save(sortie);
        }catch(Exception $e) {
            session.setAttribute("infos","Opération terminer avec succ�s!!");
        }
        return"redirect:/admin/partenaires/sorties";

    }
    @RequestMapping("/sorties/delete/{id}")
    public String deleteSortie(HttpSession session,Model model,@PathVariable("id")  long id) {
        Sortie sortie=sortieService.findOne(id);
        try {
            sortieService.delete(sortie);
            session.setAttribute("infos","Opération terminer avec succ�s!!");
        }catch(Exception $e) {
            session.setAttribute("infos","Opération non éffectuer cette fourniture existe et est lier a plusieurs opérations!!");
        }
        return"redirect:/admin/partenaires/sorties";
    }
    @GetMapping("/sorties/update/{id}")
    public String updateSortie(Model model,@PathVariable("id")  long id) {
        model.addAttribute("sortie",sortieService.findOne(id));
        model.addAttribute("offres",offreService.findAll());
        return "administration/partenaires/sorties/update";
    }
    @PostMapping("/sorties/update")
    public String saveUpdateSortie(HttpSession session,Model model,Sortie sortie,@RequestParam("date") Date date) {
         sortie.setDate(date);
         sortie.setType("sortie");
        try {
            sortieService.save(sortie);
            session.setAttribute("infos","Modification effectuer avec success "+sortie.getId());
        }catch (Exception $e){
            session.setAttribute("infos","Erreur survenue lors de la modification");
        }
        return "redirect:/admin/partenaires/sorties";
    }

    @RequestMapping("partenaires/Emprunts")
    public String saveEmprunt(HttpSession session,Model model) {
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("sortie",new Sortie());
        model.addAttribute("offres",offreService.findAll());
        model.addAttribute("sorties",sortieService.findAll());
        return "administration/partenaires/emprunts/index";
    }
    @PostMapping(value = "/emprunt/save")
    public String saveEmprunt(HttpSession session,Sortie sortie) {
        try {
            sortie.setType("Emprunt");
            sortieService.save(sortie);
        }catch(Exception $e) {
            session.setAttribute("infos","Opération terminer avec succ�s!!");
        }
        return"redirect:/admin/partenaires/Emprunts";

    }
    @RequestMapping("/emprunt/delete/{id}")
    public String deleteEmprunt(HttpSession session,Model model,@PathVariable("id")  long id) {
        Sortie sortie=sortieService.findOne(id);
        try {
            sortieService.delete(sortie);
            session.setAttribute("infos","Opération terminer avec succ�s!!");
        }catch(Exception $e) {
            session.setAttribute("infos","Opération non éffectuer cette fourniture existe et est lier a plusieurs opérations!!");
        }
        return"redirect:/admin/partenaires/Emprunts";
    }
    @GetMapping("/emprunt/update/{id}")
    public String updateEmprunt(Model model,@PathVariable("id")  long id) {
        model.addAttribute("sortie",sortieService.findOne(id));
        model.addAttribute("offres",offreService.findAll());
        return "administration/partenaires/Emprunt/update";
    }
    @PostMapping("/emprunt/update")
    public String saveUpdateEmprunt(HttpSession session,Model model,Sortie sortie,@RequestParam("date") Date date) {
        sortie.setDate(date);
        sortie.setType("Emprunt");
        try {
            sortieService.save(sortie);
            session.setAttribute("infos","Modification effectuer avec success "+sortie.getId());
        }catch (Exception $e){
            session.setAttribute("infos","Erreur survenue lors de la modification");
        }
        return "redirect:/admin/partenaires/Emprunts";
    }
    @GetMapping("/partenaires/stock")
    public String stock(Model model){
         List<Offre> offres1=offreService.findAll();
         List<Offre> offres2=new ArrayList<>();
        for (Offre offre : offres1) {
                List<Sortie> sorties = sortieService.findAllByOffre(offre);
                long qte = offre.getQuantite();
            for (Sortie sortie: sorties) {
                    qte-=sortie.getQuantite();
            }
            offre.setQuantite(qte);
            offres2.add(offre);
        }
         model.addAttribute("offres",offres2);
         return  "administration/partenaires/stock";
    }
}
