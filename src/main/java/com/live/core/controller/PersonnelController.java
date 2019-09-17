package com.live.core.controller;
import com.live.common.entities.CodeValue;
import com.live.common.entities.Detail;
import com.live.common.service.CodeValueService;
import com.live.core.entities.Live;
import com.live.core.entities.Partenaire;
import com.live.core.entities.Personnel;
import com.live.core.entities.Roles;
import com.live.core.entities.Users;
import com.live.core.repository.PersonnelRepository;
import com.live.core.repository.UsersRepository;
import com.live.core.service.*;
import com.live.moniteur.entities.Diplome;
import com.live.moniteur.entities.Dossier;
import com.live.moniteur.entities.Inscription;
import com.live.moniteur.entities.SessionFormation;
import com.live.moniteur.repository.DossierRepository;
import com.live.moniteur.service.DiplomeService;
import com.live.moniteur.service.InscriptionService;
import com.live.moniteur.service.SessionFormationService;
import com.live.paie.entities.Contrat;
import com.live.paie.service.BanqueService;
import com.live.paie.service.ContratService;
import com.live.paie.service.ProfessionService;
import com.live.paie.service.TypeContratService;
import com.live.rh.entities.*;
import com.live.rh.repository.DetailOffreRepository;
import com.live.rh.service.*;

import net.bytebuddy.utility.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Null;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class PersonnelController extends InitiateController {
    @Autowired
    public DossierRepository dossierRepository;
    @Autowired
    public ProfessionService professionService;
    @Autowired
    public ContratService contratService;
    @Autowired
    SortieService sortieService;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    DetailOffreRepository detailOffreRepository;
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
    @Autowired
    FactureService factureService;


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
     * @throws IOException 
     */
    @PostMapping(value = "/personnels/ajouter-personnel")
    public String ajouterPersonnel( @RequestParam("pt") MultipartFile photo,HttpSession session,Model model,Contrat contrat,@Valid Personnel personnel, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
       	 return "administration/personnels/index";
	 	}
        personnel.setPhoto(RandomString.make(10)+photo.getOriginalFilename());
    	Files.write(Paths.get(System.getProperty("user.home")+"/alo/personnels/"+personnel.getPhoto()), photo.getBytes());
        chargerLive(model);
        Personnel personnel1 = personnelService.save(personnel);
        contrat.setPersonnel(personnel1);
        Contrat c=contratService.save(contrat);
        personnel1.setContrat(c);
        personnel1 = personnelService.save(personnel1);
        model.addAttribute("state", "post");
        session.setAttribute("infos"," Opération éffectuer sur "+personnel1.getNom()+" - "+personnel1.getEmail()+" avec succès !!");
        model.addAttribute("info",personnel1.getNom()+" - "+personnel1.getEmail());
        return "redirect:/admin/personnels";
    }
    @ResponseBody
	 @GetMapping(value = "/personnels/images", produces = MediaType.IMAGE_PNG_VALUE)
	 public byte[] getPersonnelPhoto(@RequestParam("pid") Long id) throws IOException {
	      Personnel p=personnelService.findOne(id);
	      return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/alo/personnels/" + p.getPhoto()));
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
        List<Personnel> ps=personnelService.findAll();
        int i= ps.size()+1;
        Personnel Dernier=personnelService.findOne((long) (i));
        model.addAttribute("state", "get");
        NumberFormat nf = new DecimalFormat("00000");
        Personnel p=new Personnel();
        p.setMatricule("ALO-"+nf.format(Dernier.getId()));
        model.addAttribute("personnel", p);
        model.addAttribute("contrat", new Contrat());
        model.addAttribute("contrats",contratService.findAll());
        model.addAttribute("professions",professionService.findAll());
        model.addAttribute("banques", banqueService.findAll());
        return "administration/personnels/index";
    }
    
    //gestion de l'entreprise
    @RequestMapping("/live")
    public String live(HttpSession session,Model model) {
    	if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
    	try {
    		Live live=liveService.findOne((long) 1);
    		if(live.getNom().isEmpty()) {
    			Live lives=new Live();
        		model.addAttribute("live",lives);
        		return "administration/live/create";
    		}else {
    			model.addAttribute("live",live);
        		return "administration/live/update";
    		}
    	}catch(Exception $e) {
    		Live live=new Live();
    		model.addAttribute("live",live);
    		return "administration/live/create";
    	}
    }
    @PostMapping(value = "/ajouter")
    public String ajouterLive(@RequestParam("pt") MultipartFile photo,HttpSession session,Model model,@Valid Live live) throws IOException {
    	live.setPhoto(RandomString.make(10)+photo.getOriginalFilename());
    	Files.write(Paths.get(System.getProperty("user.home")+"/alo/live/"+live.getPhoto()), photo.getBytes());
    	try {
        	liveService.save(live);
        	session.setAttribute("infos","Actions terminer avec succès !!");
        	return "redirect:/admin/live";
        }catch(Exception $e) {
        	session.setAttribute("infos","Action non terminer !!");
        	return "redirect:/admin/live";
        	
        }
    }
    
     @ResponseBody
	 @GetMapping(value = "/live/images", produces = MediaType.IMAGE_PNG_VALUE)
	 public byte[] getLivePhoto(@RequestParam("lid") Long id) throws IOException {
	      Live p=liveService.findOne(id);
	      return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/alo/live/" + p.getPhoto()));
	 }
    //management des utilisateurs
    
    /**
     * <b> methode de modification du personnel </b>
     * @param model
     * @param personnel personnel a ajouter
     * @return
     */
    @PostMapping(value = "/personnels/modifier-personnel")
    public String modifierPersonnel(HttpSession session,Model model,@Valid Personnel personnel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
       	 	return "administration/personnels/update";
	 		}
    	//Personnel p=personnelService.findOne(personnel.getId());
        chargerLive(model);
        personnelService.save(personnel);
        session.setAttribute("infos","Modification terminer avec succès !!");
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
	       try {
               personnelService.delete(personnelService.findOne(id));
               session.setAttribute("infos","Suppression terminer avec succès !!");
           }catch (Exception $e){
               session.setAttribute("infos","Suppression non terminer car plusieurs opérations ont été effectuées. Supprimer les peut réessayer !!");
           }

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
            erreur.add("Les mots de passe saisie ne sont pas identiques. \n");
      }
        if (usersService.findByLogin(users.getLogin()) != null){
            erreur.add("Un utilisateur possédant se login existe déjà dans le système. \n");
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
            session.setAttribute("infos","Utilisateur crée, Login : "+user.getLogin() +" -- Nom :  "+user.getUsername());
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
            erreur.add("Les mots de passe saisie ne sont pas identiques. \n");
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
                // Le rôle selectionn� existe dans le système
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
        session.setAttribute("infos","l'utilisateur "+login+" à été supprimer avec succès.");
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
        List<Apprenant> ps=apprenantService.findAll();
        int i= ps.size()+1;
        Apprenant Dernier=apprenantService.findOne((long) (i));
        NumberFormat nf = new DecimalFormat("00000");
        Apprenant app=new Apprenant();
        app.setMatricule("ALOLDD"+nf.format(Dernier.getId()));
        model.addAttribute("state", "get");
    	model.addAttribute("listeApprenant", apprenantService.findAll());
        model.addAttribute("apprenant", app);
        return "administration/apprenants/index";
    }
    /**
     * Méthode d'ajout d'un Apprenant post
     * @param model
     * @return
     */
    @PostMapping(value = "/ajouter-apprenant")
    public String ajouterApprenant(@RequestParam("ph")  MultipartFile photo,HttpSession session,Model model,@Valid Apprenant apprenant, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
       	 return "administration/apprenants/index";
	 	}
        apprenant.setPhoto(RandomString.make(10)+photo.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home")+"/alo/apprenants/"+apprenant.getPhoto()), photo.getBytes());

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
    public String saveUpdateApprenant(@RequestParam("ph")  MultipartFile photo,HttpSession session,@Valid Apprenant apprenant, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
       	 return "administration/apprenants/update";
	 	}
        apprenant.setPhoto(RandomString.make(10)+photo.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home")+"/alo/apprenants/"+apprenant.getPhoto()), photo.getBytes());

        session.setAttribute("infos","Modification terminer avec succès !!");
    	apprenantService.save(apprenant);
    	return "redirect:/admin/apprenants";

    }
    @ResponseBody
    @GetMapping(value = "/apprenants/images", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getApprenantsPhoto(@RequestParam("aid") Long id) throws IOException {
        Apprenant a=apprenantService.findOne(id);
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/alo/apprenants/" + a.getPhoto()));
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
    	session.setAttribute("infos","Suppression terminer avec succès !!");
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
     * Méthode d'ajout d'un Apprenant get
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
      * Méthode d'ajout d'un Apprenant post
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
         session.setAttribute("infos","Nouvelle session de formation configuré avec succès !!");
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
    	session.setAttribute("infos","Nouvelle session de formation modifier avec succès !!");
     	sessionFormationService.save(formation);
     	return"redirect:/admin/formations";

     }
    //Affichage de la session de formation
    @RequestMapping("/supprimer-formation/{id}")
    public String supprimerFormation(Model model,@PathVariable long id,HttpSession session) {
        SessionFormation formation = sessionFormationService.findOne(id);
        try {
            sessionFormationService.delete(formation);
            session.setAttribute("infos","Session de formation supprimer avec succès !!");
        }catch (Exception $e){
            System.out.println($e.getMessage());
            session.setAttribute("infos","Session de formation non supprimer car des actions y ont été effectuées. Supprimer les pui réessayer !!!");
        }
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
        	 session.setAttribute("infos","Echec du processus de création. Cet apprenant a déjà été inscrit à cette session de formation !");
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
             session.setAttribute("infos","Processus de création terminer avec succès ! ");
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
     		session.setAttribute("infos","Suppression échouer car plusieurs opérations ont déjà été effectuées !!");
     		return "redirect:/admin/formation/apprenant";
     	}
     	diplomeService.delete(diplome);
     	session.setAttribute("infos","Suppression terminer avec succès !!");
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
		 model.addAttribute("permis", diplome.getCategoriePermis().getId());
		 model.addAttribute("diplome",diplome);
        return "administration/formations/diplomes/update";
     }
	 @PostMapping(value ="/formations/diplome/update")
	 public String SaveNumeroDiplome(HttpSession session,@Valid Diplome diplome, BindingResult bindingResult,@RequestParam("permis") Long permis,@RequestParam("propietaire") Long propietaire) {
    	if (bindingResult.hasErrors()) {
           	 return "administration/formations/diplomes/update";
    	}
    	diplome.setCategoriePermis(codeValueService.findById(permis));
    	diplome.setStatut(true);
    	diplome.setInscrit(inscriptionService.findOne(propietaire));
    	diplomeService.save(diplome);
    	session.setAttribute("infos","Opération terminer avec succès !!");
    	return "redirect:/admin/formation/diplomes";
	 }
	 @RequestMapping("/partenaire")
     public String partenaire(HttpSession session,Model model) {
		if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
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
     public String savePartenaire(@RequestParam("photos") MultipartFile photo, HttpSession session,@Valid Partenaire partenaire, BindingResult bindingResult) throws IOException {
    	if (bindingResult.hasErrors()) {
           	 return "administration/partenaires/partenaires/index";
    	}
    	partenaire.setPhoto(RandomString.make(10)+photo.getOriginalFilename());
    	Files.write(Paths.get(System.getProperty("user.home")+"/alo/partenaires/"+partenaire.getPhoto()), photo.getBytes());
    	session.setAttribute("infos","Opération terminer avec succès !!");
     	partenaireService.save(partenaire);
     	return"redirect:/admin/partenaires/view";

     }
	 @ResponseBody
	 @GetMapping(value = "/partenaire/images", produces = MediaType.IMAGE_PNG_VALUE)
	 public byte[] getPhoto(@RequestParam("pid") Long id) throws IOException {
	      Partenaire p=partenaireService.findOne(id);
	      return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/alo/partenaires/" + p.getPhoto()));
	 }
	 @RequestMapping("/partenaires/update/{id}")
     public String updatePartenaire(HttpSession session,Model model,@PathVariable("id")  long id) {
		 model.addAttribute("partenaire",partenaireService.findOne(id));
		 return "administration/partenaires/partenaires/update";
     }
	 @RequestMapping("/partenaires/view/{id}")
     public String viewPartenaire(HttpSession session,Model model,@PathVariable("id")  long id) {
		 model.addAttribute("partenaire",partenaireService.findOne(id));
		 return "administration/partenaires/partenaires/show";
     }
	 @RequestMapping("/partenaires/delete/{id}")
     public String deletePartenaire(HttpSession session,Model model,@PathVariable("id")  long id) {
		 Partenaire partenaire=partenaireService.findOne(id);
		 try {
			 partenaireService.delete(partenaire);
			 session.setAttribute("infos","Opération terminer avec succès !!");
		 }catch(Exception $e) {
			 session.setAttribute("infos","Opération non effectué ce partenaire existe et est lier à plusieurs opérations !!");
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
    	session.setAttribute("infos","Opération terminer avec succès !!");
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
			 session.setAttribute("infos","Opération terminer avec succès !!");
		 }catch(Exception $e) {
			 session.setAttribute("infos","Opération non effectuer cette fourniture existe et est lier a plusieurs opérations !!");
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
     public String saveOffres(HttpSession session,@Valid Offre offre, BindingResult bindingResult,Model model) {
	    offre.setStocksInitial(offre.getQuantite());    	
		 if (bindingResult.hasErrors()) {
    		 model.addAttribute("partenaires",partenaireService.findAll());
    		 model.addAttribute("fournitures",fournitureService.findAll());
    		 model.addAttribute(offre);
    		 model.addAttribute("info","Echec de l’opération !!");
           	 return "administration/partenaires/offres/index";
    	}
    	session.setAttribute("infos","Opération terminer avec succès !!");
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
     		session.setAttribute("infos","Opération terminer avec succès !!");
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
					 session.setAttribute("infos","Opération terminer avec succès !!");
				 }catch(Exception $e) {
					 session.setAttribute("infos","Opération non effectuer cette prévision existe et est lier à plusieurs opérations !!");
				 }
			  }
		  }
		 }
		 try {
			 offreService.delete(offre);
			 session.setAttribute("infos","Opération terminer avec succès !!");
		 }catch(Exception $e) {
			 session.setAttribute("infos","Opération non effectuer cette offre existe et est lier à plusieurs opérations !!");
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
    	session.setAttribute("infos","Opération terminer avec succès!!");
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
     		session.setAttribute("infos","Opération terminer avec succès !!");
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
					 session.setAttribute("infos","Opération terminer avec succès !!");
				 }catch(Exception $e) {
					 session.setAttribute("infos","Opération non effectuer cette prévision existe et est lier a plusieurs Opérations !!");
				 }
			  }
		  }
		 }
		 try {
			 previsionService.delete(prevision);
			 session.setAttribute("infos","Opération terminer avec succès !!");
		 }catch(Exception $e) {
			 session.setAttribute("infos","Opération non effectuer cette prévision existe et est lier à plusieurs Opérations !!");
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
    	session.setAttribute("infos","Opération terminer avec succès !!");
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
     		session.setAttribute("infos","Opération terminer avec succès !!");
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
		session.setAttribute("infos","Opération terminer avec succès !!");
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
					 session.setAttribute("infos","Opération terminer avec succès !!");
				 }catch(Exception $e) {
					 session.setAttribute("infos","Opération non effectuer ce rendez-vous existe et est lier a plusieurs opérations !!");
				 }
			  }
		  }
		 }
		 try {
			 rendezVousService.delete(rendezVous);

			 session.setAttribute("infos","Opération terminer avec succès !!");
		 }catch(Exception $e) {
			 session.setAttribute("infos","Opération non effectuer ce rendez-vous existe et est lier a plusieurs opérations !!");
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
        if (session.getAttribute("impression") != null){
            model.addAttribute("impression",session.getAttribute("impression"));
            session.removeAttribute("impression");
        }
        if (session.getAttribute("compteur") != null){
            model.addAttribute("compteur",session.getAttribute("compteur"));
            session.removeAttribute("compteur");
        }
        if (session.getAttribute("facture") != null){
            model.addAttribute("facture",session.getAttribute("facture"));
            session.removeAttribute("facture");
        }
        if (session.getAttribute("lettre") != null){
            model.addAttribute("lettre",session.getAttribute("lettre"));
            model.addAttribute("im", liveService.findOne((long) 1));
            session.removeAttribute("lettre");
            model.addAttribute("live",liveService.findOne((long) 1));
        }
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
        model.addAttribute("sorties",sortieService.findAll());
        return "administration/partenaires/sorties/index";
    }

    @PostMapping(value = "/sortie/save")
    public String saveSortie(HttpSession session,Sortie sortie,@RequestParam("qte[]") List<Long> qtes,@RequestParam("o[]") List<Long> offresId) {
    	int compteur=0;
    	List<Sortie> sts=new ArrayList<>();
    	Facture facture=new Facture();
        facture.setActeur(sortie.getAuteur());
        facture.setDescription(sortie.getDescription());
        factureService.save(facture);
    	for(Long of :  offresId) {
    		Sortie s=new Sortie();
    		s.setAuteur(sortie.getAuteur());
    		s.setDate(sortie.getDate());
    		s.setDescription(sortie.getDescription());
    		s.setOffre(offreService.findOne(of));
    		s.setQuantite(qtes.get(compteur));
    		List<Sortie> sorties = sortieService.findAllByOffre(s.getOffre());
            long qte = s.getOffre().getQuantite();
             for (Sortie sorti: sorties) {
                     qte-=sorti.getQuantite();
             }
             if(qte<s.getQuantite()) {
              	session.setAttribute("infos","Quantité inférieure au stock !! Quantité entrée : "+sortie.getQuantite()+" Reste stocks : "+qte);
              	return"redirect:/admin/partenaires/sorties";
              }
             try {
                 s.setType("sortie");
                 s.setFacture(facture);
                 sortieService.save(s);
             }catch(Exception $e) {
                 session.setAttribute("infos","Opération terminer avec succès !!");
             }
             sts.add(s);
             compteur++;
    	}
    	double compter=(double) 0;
    	for(Sortie st: sts) {
    		compter+=(double) st.getQuantite()*st.getOffre().getPvp();
    	}
    	String lettre = null;
		try {
			lettre = Nombre.CALCULATE.getValue(compter,"Francs CFA");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NumberFormat nf = new DecimalFormat("0000000");
		facture.setDescription(nf.format(facture.getId()));
		try {
			factureService.save(facture);
        }catch(Exception $e) {
        	session.setAttribute("infos","Echec de la création de la facture "+$e.getLocalizedMessage());
        }
		session.setAttribute("impression",sts);
        session.setAttribute("compteur",compter);
		session.setAttribute("lettre",lettre);
		session.setAttribute("facture",facture);
        return"redirect:/admin/partenaires/sorties";

    }
    @RequestMapping("/sorties/delete/{id}")
    public String deleteSortie(HttpSession session,Model model,@PathVariable("id")  long id) {
        Sortie sortie=sortieService.findOne(id);
        try {
            sortieService.delete(sortie);
            session.setAttribute("infos","Opération terminer avec succès !!");
        }catch(Exception $e) {
            session.setAttribute("infos","Opération non effectuer cette fourniture existe et est lier à plusieurs Opérations !!");
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
            session.setAttribute("infos","Modification effectuer avec succès "+sortie.getId());
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
         if (session.getAttribute("impression") != null){
             model.addAttribute("impression",session.getAttribute("impression"));
             session.removeAttribute("impression");
         }
         if (session.getAttribute("compteur") != null){
             model.addAttribute("compteur",session.getAttribute("compteur"));
             session.removeAttribute("compteur");
         }
         if (session.getAttribute("facture") != null){
             model.addAttribute("facture",session.getAttribute("facture"));
             session.removeAttribute("facture");
         }
         if (session.getAttribute("lettre") != null){
             model.addAttribute("lettre",session.getAttribute("lettre"));
             model.addAttribute("im", liveService.findOne((long) 1));
             session.removeAttribute("lettre");
             model.addAttribute("live",liveService.findOne((long) 1));
         }
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
         model.addAttribute("sorties",sortieService.findAll());
        return "administration/partenaires/emprunts/index";
    }
    @PostMapping(value = "/emprunt/save")
    public String saveEmprunt(HttpSession session,Sortie sortie,@RequestParam("qte[]") List<Long> qtes,@RequestParam("o[]") List<Long> offresId) {
    	int compteur=0;
    	List<Sortie> sts=new ArrayList<>();
    	Facture facture=new Facture();
        facture.setActeur(sortie.getAuteur());
        facture.setDescription(sortie.getDescription());
        factureService.save(facture);
    	for(Long of :  offresId) {
    		Sortie s=new Sortie();
    		s.setAuteur(sortie.getAuteur());
    		s.setDate(sortie.getDate());
    		s.setDescription(sortie.getDescription());
    		s.setOffre(offreService.findOne(of));
    		s.setQuantite(qtes.get(compteur));
    		List<Sortie> sorties = sortieService.findAllByOffre(s.getOffre());
            long qte = s.getOffre().getQuantite();
             for (Sortie sorti: sorties) {
                     qte-=sorti.getQuantite();
             }
             if(qte<s.getQuantite()) {
              	session.setAttribute("infos","Quantité inférieure au stock !! Quantité entrée : "+sortie.getQuantite()+" reste stocks: "+qte);
              	return"redirect:/admin/partenaires/sorties";
              }
             try {
            	 s.setType("emprunt");
                 s.setFacture(facture);
                 sortieService.save(s);
             }catch(Exception $e) {
                 session.setAttribute("infos","Opération terminer avec succès !!");
             }
             sts.add(s);
             compteur++;
    	}
    	double compter=(double) 0;
    	for(Sortie st: sts) {
    		compter+=(double) st.getQuantite()*st.getOffre().getPvp();
    	}
    	String lettre = null;
		try {
			lettre = Nombre.CALCULATE.getValue(compter,"Francs CFA");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NumberFormat nf = new DecimalFormat("0000000");
		facture.setDescription(nf.format(facture.getId()));
		try {
			factureService.save(facture);
        }catch(Exception $e) {
        	session.setAttribute("infos","Echec de la création de la facture "+$e.getLocalizedMessage());
        }
		session.setAttribute("impression",sts);
        session.setAttribute("compteur",compter);
		session.setAttribute("lettre",lettre);
		session.setAttribute("facture",facture);
        return"redirect:/admin/partenaires/Emprunts";

    }
    @RequestMapping("/emprunt/delete/{id}")
    public String deleteEmprunt(HttpSession session,Model model,@PathVariable("id")  long id) {
        Sortie sortie=sortieService.findOne(id);
        try {
            sortieService.delete(sortie);
            session.setAttribute("infos","Opération terminer avec succès !!");
        }catch(Exception $e) {
            session.setAttribute("infos","Opération non effectuer cette fourniture existe et est lier à plusieurs Opérations !!");
        }
        return"redirect:/admin/partenaires/Emprunts";
    }
    @GetMapping("/emprunt/update/{id}")
    public String updateEmprunt(Model model,@PathVariable("id")  long id) {
        model.addAttribute("sortie",sortieService.findOne(id));
        model.addAttribute("offres",offreService.findAll());
        return "administration/partenaires/Emprunts/update";
    }
    @PostMapping("/emprunt/update")
    public String saveUpdateEmprunt(HttpSession session,Model model,Sortie sortie,@RequestParam("date") Date date) {
        sortie.setDate(date);
        sortie.setType("Emprunt");
        try {
            sortieService.save(sortie);
            session.setAttribute("infos","Modification effectuer avec succès "+sortie.getId());
        }catch (Exception $e){
            session.setAttribute("infos","Erreur survenue lors de la modification ");
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

    @GetMapping("/partenaires/payement")
    public String payement(HttpSession session,Model model){
    	if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("detail",new Details());
        model.addAttribute("offres",offreService.findAll());
         return  "administration/partenaires/payement/index";
    }

    @PostMapping("/payement/save")
    public String savePayement(Model model, Details details,HttpSession session){
    	double total =0.0;
        for (Details detail: details.getOffre().getDetails()) {
                total+=detail.getValeur();
        }
    	if(total+details.getValeur()>(details.getOffre().getQuantite()*details.getOffre().getPp())) {
    		model.addAttribute("info","montant "+details.getValeur()+" FCFA Entré supérieur à la transaction reste à verser : "+((details.getOffre().getQuantite()*details.getOffre().getPp())-total)+" FCFA");
    		model.addAttribute("detail",details);
            model.addAttribute("offres",offreService.findAll());
    		return  "administration/partenaires/payement/index";
    	}
         detailOffreRepository.save(details);
         Offre offre=offreService.findOne(details.getOffre().getId());
         List<Details> details2=offre.getDetails();
         details2.add(details);
         offre.setDetails(details2);
         offreService.save(offre);
         session.setAttribute("infos","Enregistrement effectuer");
         return "redirect:/admin/partenaires/payement";
    }

    @GetMapping("/payements/show/{id}")
        public String offrePayements(HttpSession session,Model model,@PathVariable("id")  long id){
    	if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
         Offre offre=offreService.findOne(id);
        double total =0.0;
        for (Details detail: offre.getDetails()) {
                total+=detail.getValeur();
        }
        model.addAttribute("total",total);
         model.addAttribute("offre",offre);
            return  "administration/partenaires/payement/show";
        }
    @GetMapping("/partenaire/rendez-vous/{id}")
    public String showRendezVous(Model model,@PathVariable("id")  long id){
     RendezVous rendezVous=rendezVousService.findOne(id);
     List<Partenaire> partenaireRendezVous=new ArrayList<>();
     List<Partenaire> partenaires=partenaireService.findAll();
     for(Partenaire partenaire : partenaires) {
    	 for(RendezVous r : partenaire.getRendezVous()) {
    		 if(r.equals(rendezVous)) {
    			 partenaireRendezVous.add(partenaire);
    		 }
    	 }
     }
     model.addAttribute("partenaires",partenaireRendezVous);
     model.addAttribute("rendezVous",rendezVous);
     return  "administration/partenaires/rendez_vous/show";
    }
    public enum Nombre {
        //nombre simple
        ZERO(0, "zero"),UN(1, "un"),DEUX(2, "deux"),TROIX(3, "trois"),
        QUATRE(4,"quatre"),CINQ(5, "cinq"),SIX(6, "six"),SEPT(7, "sept"),
        HUIT(8,"huit"),NEUF(9, "neuf"),DIX(10, "dix"),ONZE(11, "onze"), 
        DOUZE(12, "douze"),TREIZE(13, "treize"),QUATORZE(14, "quatorze"),
        QUINZE(15, "quinze"),SEIZE(16, "seize"),DIXSEPT(17, "dix-sept"),
        DIXHUIT(18, "dix-huit"),DIXNEUF(19, "dix-neuf"),
        
        //de 20 � 99
        VINGT(20, 29, "vingt"),
        TRENTE(30, 39, "trente"),
        QUARANTE(40, 49, "quarante"), 
        CINQUANTE(50, 59, "cinquante"),
        SOIXANTE(60, 69, "soixante"),
        SOIXANTEDIX(70, 79, "soixante-dix", SOIXANTE),
        QUATREVINGT(80, 89,"quatre-vingt","s"),
        QUATREVINGTDIX(90, 99, "quatre-vingt-dix",QUATREVINGT),
        
        //de 10 � X milliard
        DIXAINE(10, 99),
        CENT(100, 999, "cent",DIXAINE),
        MILLE(1000, 999999, "mille", CENT),
        MILLION(1000000,99999999, "million", MILLE),
        MILLIARD(1000000000, Long.MAX_VALUE,"milliard", MILLION),
        
        //enum de calcul
        CALCULATE(){
            protected String getValue(long value)throws Exception {
                if (value == 0) return ZERO.label;
                else return ((value < 0) ? "moins " : "")+ MILLIARD.getStringValue((Math.abs(value)));
            }

            protected String getValue(double value,String separator)throws Exception {
                if (value == 0) return ZERO.label+" "+separator;
                else{
                    StringBuilder sb = new StringBuilder();
                    sb.append((value < 0) ? "moins " : "");
                    String vstr = Double.toString(value);
                    
                    int indexOf = vstr.indexOf('.');
                    
                    if(indexOf == -1){
                        sb.append(MILLIARD.getStringValue((long)(Math.abs(value))));
                        sb.append(" ");
                        sb.append(separator);
                    }else{
                        sb.append(MILLIARD.getStringValue(Long.parseLong(vstr.substring(0, indexOf))));
                        sb.append(" ");
                        sb.append(separator);
                        String floatting =vstr.substring(indexOf+1,(indexOf+3>=vstr.length())?vstr.length():indexOf+3)+(indexOf+3>=vstr.length()?"0":"");
                        long v = Long.parseLong(floatting);
                        if(v!=0){
                            sb.append(" ");
                            sb.append(MILLIARD.getStringValue(v));
                        }
                    }
                    return sb.toString();
                }
            }

        };

        protected long min, max;
        protected String label;
        protected Nombre before;
        // valeur � ajout � la fin d'un nombre entier
        private String addMin;
        /* constructeurs*/
        Nombre() {
        }

        Nombre(long v, String s) {
            this(v, v, s);
        }

        Nombre(long min, long max) {
            this.min = min;
            this.max = max;
        }

        Nombre(long min, long max, String label, Nombre before) {
            this(min, max, label);
            this.before = before;
        }

        Nombre(long min, long max, String label,String addMin) {
            this(min, max, label);
            this.addMin = addMin;
        }
        
        Nombre(long min, long max, String label) {
            this(min, max);
            this.label = label;
        }
        
        protected String getValue(long value)throws Exception{
            throw new Exception("Vous devez appeler la méthode par l'énumération Chiffre.CALCULATE");
        }

        protected String getValue(double value,String separator)throws Exception{
            throw new Exception("Vous devez appeller La méthode par l'énumération Chiffre.CALCULATE");
        }

        // fonction de transformation
        private String getStringValue(long value) {
            long v1 = value / this.min;
            if (v1 == 0 && before != null)return before.getStringValue(value);

            StringBuilder add = new StringBuilder();
            Nombre[]values = Nombre.values();
            if(value<20) return values[(int)value].label;
            for (int i = 0; i < values.length; i++) {
                Nombre nombre = values[i];
                //si la valeur est inf�rieur � 100
                if (value < 100 && nombre.min <= value && nombre.max >= value) {
                    //cas des valeurs 20, 30, 40, etc...
                    if (value == nombre.min) return nombre.label+((nombre.addMin!=null)?nombre.addMin:"");
                    //cas de 71->79 et 91->99
                    else if (nombre.before != null){
                        StringBuilder sb = new StringBuilder(nombre.before.label);
                        //71
                        sb.append(((value - nombre.min + 10 == 11 && nombre.equals(SOIXANTEDIX)) ? " et " : "-"));
                        sb.append(DIXAINE.getStringValue(value - nombre.min + 10));
                        return sb.toString();
                    }else{
                        StringBuilder sb = new StringBuilder(nombre.label);
                        //81
                        sb.append(((value - nombre.min == 1 && !nombre.equals(QUATREVINGT)) ? " et " : "-"));
                        //second chiffre
                        sb.append(((value - nombre.min > 0) ? DIXAINE.getStringValue(value - nombre.min) : ""));
                        return sb.toString();
                    }
                } else if (nombre.min <= v1 && nombre.max >= v1 && value >= 100) {
                    //premi�re partie du nombre
                    
                    //100 et 1000
                    if ((this.equals(MILLE) || this.equals(CENT))&& Nombre.UN.equals(nombre))
                        add.append(label);
                    else{
                        add.append(nombre.getStringValue(v1));
                        //cas : Million de millard et Milliard de milliard 
                        add.append(((MILLIARD.equals(this)&& (MILLION.equals(nombre) || MILLIARD.equals(nombre)) ? " de" : "")));
                        //ajout du label si pr�sent
                        add.append(((label != null) ? " " + label : ""));
                    }
                    //deuxi�me partie du nombre
                    add.append(((value - (v1 * this.min) > 0) ? (" " + before.getStringValue(value - (v1 * this.min))): (v1 > 1) ? "s" : ""));
                    return add.toString();
                }
            }
            return add.toString();
        }
    }
    @GetMapping("/partenaire/vente")
    public String vente(HttpSession session,Model model) {
    	 model.addAttribute("sortie",new Sortie());
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
         model.addAttribute("sorties",sortieService.findAll());
    	return"administration/partenaires/sorties/create";
    }
    @GetMapping("/partenaire/credit")
    public String credit(HttpSession session,Model model) {
    	 model.addAttribute("sortie",new Sortie());
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
         model.addAttribute("sorties",sortieService.findAll());
    	return"administration/partenaires/emprunts/create";
    }
    @GetMapping("/partenaire/facture/{id}")
    public String showFacture(HttpSession session,Model model,@PathVariable("id")  long id){
	if (session.getAttribute("infos") != null){
        model.addAttribute("info",session.getAttribute("infos"));
        session.removeAttribute("infos");
    }
	Facture facture=factureService.findOne(id);
	List<Sortie> sorties=sortieService.findAllByFacture(facture);
	double compter=(double) 0;
	for(Sortie st: sorties) {
		compter+=(double) st.getQuantite()*st.getOffre().getPvp();
	}
	String lettre = null;
	try {
		lettre = Nombre.CALCULATE.getValue(compter,"Francs CFA");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    model.addAttribute("impression",sorties);
    model.addAttribute("compteur",compter);
    model.addAttribute("facture",facture);
    model.addAttribute("lettre",lettre);
    model.addAttribute("live",liveService.findOne((long) 1));
    return  "administration/partenaires/factures/show";
    }

    /*
    *Les payement relatif a la formation
     * @param session
     * @param model
     * @return
     */

    @GetMapping("/formation/payements")
    public String payements(HttpSession session,Model model) {
        if(session.getAttribute("formationCourante")==null) {
            return "redirect:/admin/formations";
        }
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        SessionFormation formation = (SessionFormation) session.getAttribute("formationCourante");
        model.addAttribute("listeInscriptions", inscriptionService.findInscriptionsByFormation(formation));
        model.addAttribute("formation", formation);
        model.addAttribute("detail", new Detail());
        return "administration/formations/payement/index";
    }

    @PostMapping("formation/payement/save")
    public String saveInsPayement(Model model, Details details,HttpSession session){
        double total =0.0;
        for (Details detail: details.getInscription().getDetails()) {
            total+=detail.getValeur();
        }
        detailOffreRepository.save(details);
        Inscription inscription=inscriptionService.findOne(details.getInscription().getId());
        List<Details> details2=inscription.getDetails();
        details2.add(details);
        inscription.setDetails(details2);
        inscriptionService.save(inscription);
        session.setAttribute("infos","Enregistrement effectuer");
        return "redirect:/admin/formation/payements";
    }

    @GetMapping("formation/payements/show/{id}")
    public String inscriptionPayements(HttpSession session,Model model,@PathVariable("id")  long id){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        Inscription inscription=inscriptionService.findOne(id);
        double total =0.0;
        for (Details detail: inscription.getDetails()) {
            total+=detail.getValeur();
        }
        model.addAttribute("total",total);
        model.addAttribute("inscription",inscription);
        return  "administration/formations/payement/show";
    }

    @GetMapping("exament/voir")
    public String examentFinal(HttpSession session,Model model){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("inscriptions", inscriptionService.findInscriptionsByFormation((SessionFormation) session.getAttribute("formationCourante")));
        return  "administration/formations/exament/index";
    }

    @PostMapping("exament/save")
    public String examentSavel(HttpSession session,Model model,@RequestParam("theoriques") List<String> theoriques,@RequestParam("pratiques") List<String> pratiques){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        int i=0;
        for (Inscription inscription : inscriptionService.findInscriptionsByFormation((SessionFormation) session.getAttribute("formationCourante"))){
            switch (pratiques.get(i)){
                case "true":
                    inscription.setEpreuvePratique(true);
                    break;
                case "false":
                    inscription.setEpreuvePratique(false);
                    break;
                default:
                    inscription.setEpreuvePratique(null);
                    break;
            }
            switch (theoriques.get(i)){
                case "true":
                    inscription.setEpreuveTheorique(true);
                    break;
                case "false":
                    inscription.setEpreuveTheorique(false);
                    inscription.setEpreuvePratique(false);
                    break;
                default:
                    inscription.setEpreuveTheorique(null);
                    inscription.setEpreuvePratique(null);
                    break;
            }
                i++;
                inscriptionService.save(inscription);
        }
        return  "redirect:/admin/exament/voir";
    }

    @GetMapping("/formation/dossiers")
    public String dossiers(HttpSession session,Model model){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("listeInscriptions", inscriptionService.findInscriptionsByFormationEpreuvePratique((SessionFormation) session.getAttribute("formationCourante"),true));
        return  "administration/formations/dossiers/index";
    }

    @GetMapping("/formation/dossiers/create/{id}")
    public  String createDossier(HttpSession session,Model model,@PathVariable("id") Long id){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("inscription",inscriptionService.findOne(id));
        model.addAttribute("dossier",new Dossier());
        return  "administration/formations/dossiers/show";
    }

    @PostMapping("/formation/dossier/save")
    public  String saveDossier(Model model,Dossier dossier,@RequestParam("inscription") Long id){
        if (dossier.getId() != null){
            dossier.setId((Long) dossier.getId());
        }
        Inscription inscription=inscriptionService.findOne(id);
        dossier.setInscrit(inscription);
        inscription.setDossier(dossierRepository.save(dossier));
        inscriptionService.save(inscription);
        return "redirect:/admin/formation/dossiers";
    }
    @PostMapping("/formation/dossier/update")
    public String updateDossier(Model model,Dossier dossier,@RequestParam("inscription") Long id){
        Inscription inscription=inscriptionService.findOne(id);
        dossier.setId((Long) dossier.getId());
        dossier.setInscrit(inscription);
        dossierRepository.save(dossier);
       return "redirect:/admin/formation/dossiers";
    }

    @GetMapping("/formation/dossiers/edit/{id}")
    public  String editDossier(HttpSession session,Model model,@PathVariable("id") Long id){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("inscription",inscriptionService.findOne(id));
        model.addAttribute("edit",new Dossier());
        return  "administration/formations/dossiers/show";
    }

    @GetMapping("/formation/recipices")
    public String recipicicer(HttpSession session,Model model){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("listeInscriptions", inscriptionService.findInscriptionsByFormation((SessionFormation) session.getAttribute("formationCourante")));
        return  "administration/formations/recipice/index";
    }

    @PostMapping("/formation/recipice/save")
    public String saverecipicicer(HttpSession session,Model model,@RequestParam("numero") String numero,@RequestParam("dateExpiration") String expiration,@RequestParam("inscription") Long inscriptionId){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        Inscription inscription=inscriptionService.findOne(inscriptionId);
        Dossier dossier=inscription.getDossier();
        dossier.setCodeRecipicer(numero);
        dossier.setDataExpiration(expiration);
        dossierRepository.save(dossier);
        return  "redirect:/admin/formation/recipices";
    }
}
