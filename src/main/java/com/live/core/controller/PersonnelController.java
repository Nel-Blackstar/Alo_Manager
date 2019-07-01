package com.live.core.controller;
import com.live.common.entities.CodeValue;
import com.live.common.service.CodeValueService;
import com.live.core.entities.Personnel;
import com.live.core.entities.Roles;
import com.live.core.entities.Users;
import com.live.core.repository.PersonnelRepository;
import com.live.core.repository.UsersRepository;
import com.live.core.service.*;
import com.live.moniteur.entities.Inscription;
import com.live.moniteur.entities.SessionFormation;
import com.live.moniteur.service.InscriptionService;
import com.live.moniteur.service.SessionFormationService;
import com.live.paie.service.BanqueService;
import com.live.rh.entities.Apprenant;
import com.live.rh.service.ApprenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class PersonnelController extends InitiateController {
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
    public String ajouterPersonnel(HttpSession session,Model model, Personnel personnel) {
        //model.addAttribute("personnel", iHotelManager.userConnecte());
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
    public String modifierPersonnel(HttpSession session,Model model, Personnel personnel,String date) {
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
    public String ajouterUser(HttpSession session, Model model, Users users, @RequestParam("role") List<String> role, @RequestParam("vmdp") String vmdp) {
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
    public String editUser(HttpSession session,Model model, Users users, @RequestParam("role") List<String> role,@RequestParam("vmdp") String vmdp) {
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
    public String ajouterApprenant(HttpSession session,Model model, Apprenant apprenant) {
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
    public String saveUpdateApprenant(HttpSession session,Apprenant apprenant,String date) {
    	@SuppressWarnings("deprecation")
		Date date_naissance=new Date();
		try {
			date_naissance = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		session.setAttribute("infos","Modification terminer avec succes!!");
		apprenant.setDate_naissance(date_naissance);
    	apprenantService.save(apprenant);
    	return "redirect:/admin/apprenants";

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
    	session.setAttribute("infos","suppression terminer avec succes!!");
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
     public String ajouterFormation(HttpSession session,Model model, SessionFormation formation) {
         sessionFormationService.save(formation);
         model.addAttribute("state", "post");
         session.setAttribute("infos","Nouvelle session de formation configuerer avec succes!!");
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
     public String saveUpdateFormation(HttpSession session,Apprenant apprenant,String date) {
     	@SuppressWarnings("deprecation")
 		Date date_naissance=new Date();
 		try {
 			date_naissance = new SimpleDateFormat("dd/MM/yyyy").parse(date);
 		} catch (ParseException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}  
 		session.setAttribute("infos","Modification terminer avec succes !!!");
 		apprenant.setDate_naissance(date_naissance);
     	apprenantService.save(apprenant);
     	return "redirect:/admin/apprenants";

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
     public String ajouterApprenantFormation(HttpServletRequest request,HttpSession session,Model model, Inscription inscription) {
         chargerLive(model);
         SessionFormation formation = (SessionFormation) session.getAttribute("formationCourante");
         inscription.setFormation(formation);
         Inscription inscription1 = inscriptionService.save(inscription);
         model.addAttribute("state", "post");
         session.setAttribute("infos","Proc�ssus terminer avec succ�s!");
         String referer = request.getHeader("Referer");
         //return "redirect:"+ referer;
         return "redirect:/admin/formation/apprenant";
     }
     //suppression de l'inscription
     @GetMapping("/delete-Inscription/{id}")
     public String deleteEntrer(HttpServletRequest request,Model model,HttpSession session,@PathVariable long id) {
     	Inscription inscription = inscriptionService.findOne(id);
     	inscriptionService.delete(inscription);
     	session.setAttribute("infos","suppression terminer avec succes!!");
     	String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
     }
     //modification de l'inscription
     @RequestMapping("/modifier-Inscription/{id}")
     public String editeCharge(HttpSession session,Model model,@PathVariable("id")  long id) {
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
     
}
