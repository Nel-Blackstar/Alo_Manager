package com.live.core.controller;

import com.live.core.entities.Live;
import com.live.core.entities.Personnel;
import com.live.core.entities.Roles;
import com.live.core.entities.Users;
import com.live.core.repository.PersonnelRepository;
import com.live.core.repository.UsersRepository;
import com.live.rh.entities.Apprenant;
import com.live.rh.service.ApprenantService;
import com.live.core.service.*;
import com.live.paie.entities.Banque;
import com.live.paie.service.BanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    // Objet de cryptage et decryptage des mots de passe
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    //management du personnel

    /** Ajout d'un personnel
     *
     * @param model
     * @return
     */
    @GetMapping("/personnels/ajouter-personnel")
    public String formPersonnel(Model model) {
        //model.addAttribute("user", iHotelManager.userConnecte());
        chargerLive(model);

        model.addAttribute("state", "get");
        model.addAttribute("personnel", new Personnel());
        model.addAttribute("banques", banqueService.findAll());
        return "administration/personnels/create";
    }

    //gestion des personnelss

    /**
     * <b> methode d'ajout d'un utilisateur </b>
     * @param model
     * @param personnel personnel a ajouter
     * @return
     */
    @PostMapping(value = "/personnels/ajouter-personnel")
    public String ajouterPersonnel(Model model, Personnel personnel) {
        //model.addAttribute("personnel", iHotelManager.userConnecte());
        chargerLive(model);
        Personnel personnel1 = personnelService.save(personnel);
        model.addAttribute("state", "post");
        model.addAttribute("info",personnel1.getNom()+" - "+personnel1.getEmail());
        return "redirect:/admin/personnels";
    }

    /**
     *     <b> Consultation des information d'un personnel du system  </b>
     * @param model
     * @param id identified of personnel
     * @return
     */
    @RequestMapping("/personnels/consulter-personnel/{id}")
    public String consulterPersonnel(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", iLiveManager.userConnecte());
        chargerLive(model);

        // Retrouver l'personnels à consulter et le placer dans le modèle
        Personnel personnel = personnelService.findOne(id);
        model.addAttribute("personnel", personnel);
        return "administration/consulter-personnel";
    }

    // Modification des informations sur le personnels

    /**
     *     <b>  Modification des informations sur l'utilisateur</b>
     * @param model
     * @param id identified of personnel
     * @return
     */
    @RequestMapping("/personnels/editer-personnel/{id}")
    public String editerPersonnel(Model model, @PathVariable("id") long id) {
        Personnel personnel = personnelService.findOne(id);
        model.addAttribute("state", "get");
        model.addAttribute("personnel", personnel);

        model.addAttribute("user", iLiveManager.userConnecte());
        chargerLive(model);
        return "administration/ajouter-personnel";
    }

    /** Suppression d'un personnel
     *
     * @param model
     * @param id identified of personnel person
     * @return
     */
    @RequestMapping("/personnels/supprimer-personnel/{id}")
    public String supprimerPersonnel(Model model, @PathVariable("id") long id) {
        personnelService.delete(personnelService.findOne(id));
        List<Personnel> listePersonnels
                = personnelService.findAll();
        model.addAttribute("listePersonnels", listePersonnels);

        model.addAttribute("user", iLiveManager.userConnecte());
        chargerLive(model);
        return "redirect:/admin/personnels";
    }

    /**
     * Rechercher de l'ensemble des utilisateurs du systeme
     * @param model
     * @return
     */
    @RequestMapping("/personnels")
    public String listPersonnel(Model model) {
        model.addAttribute("listePersonnels", personnelService.findAll());
        //model.addAttribute("user", iHotelManager.userConnecte());
        chargerLive(model);

        return "administration/personnels/index";
    }

    //management des utilisateurs

    /** Ajout d'un user
     *
     * @param model
     * @return
     */
    @GetMapping(value="/users/ajouter-user")
    public String formUser(Model model) {
        chargerLive(model);
        // Charger la liste des rôles disponibles et déposer dans le model
        model.addAttribute("listeRoles", rolesService.findAll());
        //chargement de la liste du personnel
        List<Personnel> listePersonnels=personnelRepository.findUsersAccount();
        model.addAttribute("listePersonnels",listePersonnels);
        model.addAttribute("state", "get");
        model.addAttribute("users", new Users());
        return "administration/utilisateurs/create";
    }

    //gestion des userss

    /**
     * <b> methode d'ajout d'un utilisateur </b>
     * @param model
     * @param users utilisateur a ajouter
     * @return
     */
    @PostMapping(value = "/users/ajouter-user")
    public String ajouterUser(Model model, Users users, @RequestParam("role") String role,@RequestParam("vmdp") String vmdp) {
        //model.addAttribute("user", iHotelManager.userConnecte());
        chargerLive(model);

        Users user = new Users();
        user.setLogin(users.getLogin());
        user.setActive(true);
        user.setUsername(users.getUsername());

        // Crypter le mot de passe utilisateur
        user.setPassword(encoder.encode(users.getPassword()));

        // Attribuer les rôles à l'utilisateur
      Roles roles = rolesService.findOne(role);
        if(roles != null){
            // Enregistrer l'utilisateur
            Users savedUser = usersService.save(user);
            // Le rôle selectionné existe dans le système
            savedUser.addFirstRole(roles);
            usersService.save(savedUser);
        }
        model.addAttribute("state", "post");
        model.addAttribute("info",user.getLogin() +" - "+user.getUsername());
        return "redirect:/admin/users";
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
        Users users = usersRepository.findUsersByLogin(login);;
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
    public String editerUser(Model model, @PathVariable("id") String login) {
        Users users = usersRepository.findUsersByLogin(login);
        model.addAttribute("state", "get");
        model.addAttribute("users", users);
        model.addAttribute("listePersonnels", personnelService.findAll());
        model.addAttribute("listeRoles", rolesService.findAll());
        //model.addAttribute("user", iLiveManager.userConnecte());
        chargerLive(model);
        return "administration/utilisateurs/update";
    }

    @PostMapping(value = "/users/editer-user")
    public String editUser(Model model, Users users, @RequestParam("role") String role,@RequestParam("vmdp") String vmdp) {
        //model.addAttribute("user", iHotelManager.userConnecte());
        chargerLive(model);

        Users user = usersRepository.findUsersByLogin(users.getLogin());
        user.setLogin(users.getLogin());
        user.setActive(true);
        user.setUsername(users.getUsername());

        // Crypter le mot de passe utilisateur
        user.setPassword(encoder.encode(users.getPassword()));

        // Attribuer les rôles à l'utilisateur
        Roles roles = rolesService.findOne(role);
        if(roles != null){
            // Enregistrer l'utilisateur
            Users savedUser = usersService.save(user);
            // Le rôle selectionné existe dans le systèmes
            savedUser.addFirstRole(roles);
            usersService.save(savedUser);
        }
        model.addAttribute("state", "post");
        model.addAttribute("info",user.getLogin() +" - "+user.getUsername());
        return "redirect:/admin/users";
    }

    // Suppression d'une users
    @RequestMapping("/users/supprimer-user/{id}")
    public String supprimerUser(Model model, @PathVariable("id") String login) {
        usersService.delete(usersRepository.findUsersByLogin(login));
        chargerLive(model);
        return "redirect:/admin/users";
    }

    /**
     * Rechercher de l'ensemble des utilisateurs du systeme
     * @param model
     * @return
     */
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("listeUsers", usersService.findAll());
        //model.addAttribute("user", iHotelManager.userConnecte());
        chargerLive(model);

        return "administration/utilisateurs/index";
    }
    /**
     * Rechercher de l'ensemble des Apprenants du systeme
     * @param model
     * @return
     */
    @GetMapping("/apprenants")
    public String listApprenant(Model model) {
    	model.addAttribute("listeApprenant", apprenantService.findAll());
        return "administration/apprenants/index";
    }
    /** 
    * M�thode d'ajout d'un Apprenant get
    * @param model
    * @return
    */
    @RequestMapping("/ajouter-apprenant")
    public String formApprenant(Model model) {

       model.addAttribute("state", "get");
       model.addAttribute("apprenant", new Apprenant());
       return "administration/apprenants/create";
    }
    /**
     * M�thode d'ajout d'un Apprenant post
     * @param model
     * @return
     */
    @PostMapping(value = "/ajouter-apprenant")
    public String ajouterApprenant(Model model, Apprenant apprenant) {
        Apprenant apprenantToSave = apprenantService.save(apprenant);
        model.addAttribute("state", "post");
        model.addAttribute("info",apprenantToSave.getNom()+" - "+apprenantToSave.getTelephone_1());
        return "redirect:/admin/apprenants";
    }
    /**
     * Modification des informations sur un Apprenant
     * @param model
     * @param id identifiant de l'apprenant
     * @return
     */
    @RequestMapping("/update-apprenant/{id}")
    public String editeApprenant(Model model,@PathVariable long id) {
    	Apprenant apprenant = apprenantService.findOne(id);
    	model.addAttribute("apprenant", apprenant);
    	return "administration/apprenants/update";

    }
    /**
     * Modification des informations sur un Apprenant
     * @return
     */
    @PostMapping(value = "/update-apprenant")
    public String saveUpdateApprenant(Apprenant apprenant) {
    	apprenantService.save(apprenant);
    	return "redirect:/admin/apprenants";

    }
    /**
     * Suppression d'un apprenant
     * @param id identifiant de l'apprenant
     * @return
     */
    @RequestMapping("/delete-apprenant/{id}")
    public String deleteApprenant(@PathVariable long id) {
    	Apprenant apprenant = apprenantService.findOne(id);
    	apprenantService.delete(apprenant);
    	return "redirect:/admin/apprenants";

    }
}
