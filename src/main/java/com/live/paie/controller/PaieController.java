package com.live.paie.controller;

import com.live.core.entities.Personnel;
import com.live.core.service.PersonnelService;
import com.live.paie.entities.*;
import com.live.paie.service.*;
import com.live.reports.BulletinsReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Null;

@Controller
@RequestMapping(value = "/admin/paies")
public class PaieController {
    @Autowired
    public AvancesService avancesService;
    @Autowired
    public PretsService pretsService;
    @Autowired
    public PrimesVariablesService primeVariableService;
    @Autowired
    public ProfessionService professionService;
    @Autowired
    public CNPSService cnpsService;
    @Autowired
    public TypeCongeService typeCongeService;
    @Autowired
    public TypeContratService typeContratService;
    @Autowired
    public CreditsService creditsService;
    @Autowired
    public ContratService contratService;
    @Autowired
    public PrimesFixesService primesFixesService;
    @Autowired
    public EnfantsService enfantsService;
    @Autowired
    public PersonnelService personnelService;
    @Autowired
    public CongeService congeService;
    @Autowired
    public BulletinPaieService bulletinPaieService;
    @Autowired
    public BulletinsReportService bulletinsReportService;

    /*
     *******************************
     * Debut du module de paies
     * **********************
     */
    @GetMapping("/show")
    public String paies(HttpSession session, Model model) {

        return "/administration/paies/index";
    }

    /*
     *******************************
     * Recapitulatif du personnel
     * **********************
     */
    @RequestMapping("/voir-personnel/{id}")
    public String voirPersonnel(HttpSession session, Model model, @PathVariable long id) {
        if (session.getAttribute("infos") != null) {
            model.addAttribute("info", session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        Personnel personnel = personnelService.findOne(id);
        model.addAttribute("personnel", personnel);
        model.addAttribute("enfant", new Enfants());
        model.addAttribute("enfants", enfantsService.findByPersonnel(personnel));
        model.addAttribute("conge", new Conge());
        model.addAttribute("credit", new Credits());
        model.addAttribute("avance", new Avances());
        model.addAttribute("typeConge", this.typeCongeService.findAll());
        model.addAttribute("prime", new PrimesVariables());
        model.addAttribute("prets", new Prets());
        model.addAttribute("primesFixes", primesFixesService.findAll());
        return "administration/personnels/view";
    }

    /*
     *******************************
     * Gestion des enfants
     * **********************
     */
    @PostMapping("/enfants/save")
    public String saveEnfants(HttpSession session, @RequestParam("personne") long parent, @Valid Enfants enfant, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("enfant", enfant);
            model.addAttribute("enfants", enfantsService.findAll());
            model.addAttribute("personnels", personnelService.findAll());
            return "redirect:/admin/paies/voir-personnel/" + enfant.getPersonnel().getId();
        }
        Personnel perso = personnelService.findOne(parent);
        enfant.setPersonnel(perso);
        Personnel p = enfant.getPersonnel();
        List<Enfants> enfs = new ArrayList<Enfants>();
        if (p.getEnfants() != null) {
            for (Enfants enf : p.getEnfants()) {
                enfs.add(enf);
            }
        }
        enfs.add(enfantsService.save(enfant));
        p.setEnfants(enfs);
        personnelService.save(p);
        session.setAttribute("infos", "Enregistrement effectuer");
        return "redirect:/admin/paies/voir-personnel/" + enfant.getPersonnel().getId();
    }

    @RequestMapping("/enfants/delete/{id}")
    public String deleteEnfants(HttpSession session, @PathVariable long id) {
        Enfants enfant = enfantsService.findOne(id);
        try {
            Personnel personnel = enfant.getPersonnel();
            List<Enfants> enfs = personnel.getEnfants();
            enfs.remove(enfant);
            personnel.setEnfants(enfs);
            this.personnelService.save(personnel);
            this.enfantsService.delete(enfant);
            session.setAttribute("infos", "Suppression Effectuer !");
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression Impossible");
        }
        return "redirect:/admin/paies/voir-personnel/" + enfant.getPersonnel().getId();

    }

    /*
     *******************************
     * Professions
     * **********************
     */
    @GetMapping("/professions")
    public String professions(HttpSession session, Model model) {
        if (session.getAttribute("infos") != null) {
            model.addAttribute("info", session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("profession", new Profession());
        model.addAttribute("professions", professionService.findAll());
        model.addAttribute("cnpss", cnpsService.findAll());
        return "/administration/paies/Professions/index";
    }

    @PostMapping("/profession/save")
    public String saveProfessions(HttpSession session, Profession profession) {
        if (profession.getId() != null) {
            profession.setId((Long) profession.getId());
        }
        this.professionService.save(profession);
        session.setAttribute("infos", "Enregistrement effectuer");
        return "redirect:/admin/paies/professions";
    }

    @RequestMapping("/profession/update/{id}")
    public String updateProfession(HttpSession session, Model model, @PathVariable long id) {

        Profession profession = professionService.findOne(id);
        model.addAttribute("profession", profession);
        model.addAttribute("professions", professionService.findAll());
        model.addAttribute("cnpss", cnpsService.findAll());
        return "/administration/paies/Professions/index";

    }

    @RequestMapping("/profession/delete/{id}")
    public String deleteProfession(HttpSession session, @PathVariable long id) {

        try {
            Profession profession = professionService.findOne(id);
            this.professionService.delete(profession);
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression Impossible , Le Personnel Exerce cette Profession, Supprimer le Personnel occupant cette Profession et Réessayer");
        }
        session.setAttribute("infos", "Suppression Effectuer !");
        return "redirect:/admin/paies/professions";

    }

    /*
     *******************************
     * CNPS
     * **********************
     */
    @GetMapping("/cnps")
    public String cnps(HttpSession session, Model model) {
        if (session.getAttribute("infos") != null) {
            model.addAttribute("info", session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("cnps", new CNPS());
        model.addAttribute("cnpss", cnpsService.findAll());
        return "/administration/paies/cnps/index";
    }

    @PostMapping("/cnps/save")
    public String saveCnps(HttpSession session, CNPS cnps) {
        if (cnps.getId() != null) {
            cnps.setId((Long) cnps.getId());
        }
        this.cnpsService.save(cnps);
        session.setAttribute("infos", "Enregistrement effectuer");
        return "redirect:/admin/paies/cnps";
    }

    @RequestMapping("/cnps/update/{id}")
    public String updateCnps(HttpSession session, Model model, @PathVariable long id) {

        CNPS cnps = cnpsService.findOne(id);
        model.addAttribute("cnps", cnps);
        model.addAttribute("cnpss", cnpsService.findAll());
        return "/administration/paies/cnps/index";

    }

    @RequestMapping("/cnps/delete/{id}")
    public String deleteCnps(HttpSession session, @PathVariable long id) {

        CNPS cnps = cnpsService.findOne(id);
        try {
            this.cnpsService.delete(cnps);
            session.setAttribute("infos", "Suppression Effectuer !");
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression impossible, Cette CNPS est Utilisé Pour des Professions");
        }
        return "redirect:/admin/paies/cnps";

    }


    /*
     *******************************
     * Type Congés
     * **********************
     */
    @GetMapping("/typeConge")
    public String typeConger(HttpSession session, Model model) {
        if (session.getAttribute("infos") != null) {
            model.addAttribute("info", session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("typeConge", new TypeConge());
        model.addAttribute("typeConges", typeCongeService.findAll());
        return "/administration/paies/typeConge/index";
    }

    @PostMapping("/typeConge/save")
    public String saveTypeConge(HttpSession session, TypeConge typeConge) {
        if (typeConge.getId() != null) {
            typeConge.setId((Long) typeConge.getId());
        }
        this.typeCongeService.save(typeConge);
        session.setAttribute("infos", "Enregistrement effectuer");
        return "redirect:/admin/paies/typeConge";
    }

    @RequestMapping("/typeConge/update/{id}")
    public String updateTypeConge(HttpSession session, Model model, @PathVariable long id) {

        TypeConge typeConge = typeCongeService.findOne(id);
        model.addAttribute("typeConge", typeConge);
        model.addAttribute("typeConges", typeCongeService.findAll());
        return "/administration/paies/typeConge/index";

    }

    @RequestMapping("/typeConge/delete/{id}")
    public String deleteTypeConge(HttpSession session, @PathVariable long id) {

        TypeConge typeConge = typeCongeService.findOne(id);
        try {
            this.typeCongeService.delete(typeConge);
            session.setAttribute("infos", "Suppression Effectuer !");
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression impossible, Ce type de conger est Utilisé Pour le Personnel");
        }
        return "redirect:/admin/paies/typeConge";

    }


    /*
     *******************************
     * Type Contract
     * **********************
     */
    @GetMapping("/typeContrat")
    public String typeContrat(HttpSession session, Model model) {
        if (session.getAttribute("infos") != null) {
            model.addAttribute("info", session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("typeContrat", new TypeContrat());
        model.addAttribute("typeContrats", typeContratService.findAll());
        return "/administration/paies/typeContrat/index";
    }

    @PostMapping("/typeContrat/save")
    public String saveTypeContrat(HttpSession session, TypeContrat typeContrat) {
        if (typeContrat.getId() != null) {
            typeContrat.setId((Long) typeContrat.getId());
        }
        this.typeContratService.save(typeContrat);
        session.setAttribute("infos", "Enregistrement effectuer");
        return "redirect:/admin/paies/typeContrat";
    }

    @RequestMapping("/typeContrat/update/{id}")
    public String updateTypeContrat(HttpSession session, Model model, @PathVariable long id) {

        TypeContrat typeContrat = typeContratService.findOne(id);
        model.addAttribute("typeContrat", typeContrat);
        model.addAttribute("typeContrats", typeContratService.findAll());
        return "/administration/paies/typeContrat/index";

    }

    @RequestMapping("/typeContrat/delete/{id}")
    public String deleteTypeContrat(HttpSession session, @PathVariable long id) {

        TypeContrat typeContrat = typeContratService.findOne(id);
        try {
            this.typeContratService.delete(typeContrat);
            session.setAttribute("infos", "Suppression Effectuer !");
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression impossible, Ce type de Contrat est Utilisé Pour des Contract avec le personnel");
        }
        return "redirect:/admin/paies/typeContrat";

    }


    /*
     *******************************
     * Primes Fixe
     * **********************
     */
    @GetMapping("/primeFixe")
    public String primeFixe(HttpSession session, Model model) {
        if (session.getAttribute("infos") != null) {
            model.addAttribute("info", session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("primeFixe", new PrimesFixes());
        model.addAttribute("primeFixes", primesFixesService.findAll());
        return "/administration/paies/primeFixe/index";
    }

    @PostMapping("/primeFixe/save")
    public String savePrimeFixe(HttpSession session, PrimesFixes primeFixe) {
        if (primeFixe.getId() != null) {
            primeFixe.setId((Long) primeFixe.getId());
        }
        this.primesFixesService.save(primeFixe);
        session.setAttribute("infos", "Enregistrement effectuer");
        return "redirect:/admin/paies/primeFixe";
    }

    @RequestMapping("/primeFixe/update/{id}")
    public String updatePrimeFixe(HttpSession session, Model model, @PathVariable long id) {

        PrimesFixes primeFixe = primesFixesService.findOne(id);
        model.addAttribute("primeFixe", primeFixe);
        model.addAttribute("primeFixes", primesFixesService.findAll());
        return "/administration/paies/primeFixe/index";

    }

    @RequestMapping("/primeFixe/delete/{id}")
    public String deletePrimeFixe(HttpSession session, @PathVariable long id) {

        PrimesFixes primeFixe = primesFixesService.findOne(id);
        try {
            this.primesFixesService.delete(primeFixe);
            session.setAttribute("infos", "Suppression Effectuer !");
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression impossible, Cette Prime est Attribuer a un ou plusieur personnel");
        }
        return "redirect:/admin/paies/primeFixe";

    }

    //distribution de la prime
    @RequestMapping("/primeFixe/distribuer/{id}")
    public String distributePrimeFixe(HttpSession session, Model model, @PathVariable long id) {
        PrimesFixes primeFixe = primesFixesService.findOne(id);
        model.addAttribute("primeFixe", primeFixe);
        model.addAttribute("personnels",personnelService.findAll());
        return "/administration/paies/primeFixe/show";

    }

    @RequestMapping("/primeFixe/distribuer")
    public String saveDistribution(HttpSession session,@RequestParam(value="primes",required = false) List<Long> primers, @RequestParam("primeFixe") Long prime){
        PrimesFixes primesFixes=primesFixesService.findOne(prime);
        for (Long primer : primers){
           Personnel personnel = personnelService.findOne(primer);
            if (personnel.getPrimesFixes() != null){
                List<PrimesFixes> allPrimes = personnel.getPrimesFixes();
                if(!allPrimes.contains(primesFixes)){
                    allPrimes.add(primesFixes);
                    personnel.setPrimesFixes(allPrimes);
                }
            }else{
                List<PrimesFixes> allPrimes = new ArrayList<PrimesFixes>();
                allPrimes.add(primesFixes);
                personnel.setPrimesFixes(allPrimes);
            }
            personnelService.save(personnel);
        }
        session.setAttribute("infos","Distribution de prime effectuer");
        return "redirect:/admin/paies/primeFixe";
    }


    /*
     *******************************
     * Credits
     * **********************
     */
    @PostMapping("/credit/save")
    public String saveCredit(HttpSession session, Credits credit) {
        Personnel perso = credit.getPersonnel();
        Personnel p = credit.getPersonnel();
        List<Credits> credits = new ArrayList<Credits>();
        if (p.getCredits() != null) {
            for (Credits crd : p.getCredits()) {
                credits.add(crd);
            }
        }
        credits.add(this.creditsService.save(credit));
        p.setCredits(credits);
        personnelService.save(p);
        session.setAttribute("infos", "Enregistrement effectuer");
        return "redirect:/admin/paies/voir-personnel/" + credit.getPersonnel().getId();
    }

    @RequestMapping("/credit/delete/{id}")
    public String deleteCredit(HttpSession session, @PathVariable long id) {

        Credits credit = creditsService.findOne(id);
        try {
            Personnel personnel = credit.getPersonnel();
            List<Credits> credits = personnel.getCredits();
            credits.remove(credit);
            personnel.setCredits(credits);
            this.personnelService.save(personnel);
            this.creditsService.delete(credit);
            session.setAttribute("infos", "Suppression Effectuer !");
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression impossible");
        }
        return "redirect:/admin/paies/voir-personnel/" + credit.getPersonnel().getId();

    }

    /*
     *******************************
     * Contrats
     * **********************
     */
    @GetMapping("/contrats")
    public String contrats(HttpSession session, Model model) {
        if (session.getAttribute("infos") != null) {
            model.addAttribute("info", session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("contrat", new Contrat());
        model.addAttribute("contrats", contratService.findAll());
        model.addAttribute("personnels", personnelService.findAll());
        model.addAttribute("typeContrats", typeContratService.findAll());
        model.addAttribute("professions", professionService.findAll());
        return "/administration/paies/contrat/index";
    }

    @PostMapping("/contrat/save")
    public String saveContrat(HttpSession session, Contrat contrat) {
        if (contrat.getId() != null) {
            contrat.setId((Long) contrat.getId());
        }
        this.contratService.save(contrat);
        session.setAttribute("infos", "Enregistrement effectuer");
        return "redirect:/admin/paies/contrats";
    }

    @RequestMapping("/contrat/update/{id}")
    public String updateContrat(HttpSession session, Model model, @PathVariable long id) {

        Contrat contrat = contratService.findOne(id);
        model.addAttribute("contrat", contrat);
        model.addAttribute("contrats", contratService.findAll());
        model.addAttribute("personnels", personnelService.findAll());
        model.addAttribute("typeContrats", typeContratService.findAll());
        model.addAttribute("professions", professionService.findAll());
        return "/administration/paies/contrat/index";

    }

    @RequestMapping("/contrat/delete/{id}")
    public String deleteContrat(HttpSession session, @PathVariable long id) {

        Contrat contrat = contratService.findOne(id);
        try {
            this.contratService.delete(contrat);
            session.setAttribute("infos", "Suppression Effectuer !");
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression impossible");
        }
        return "redirect:/admin/paies/contrats";

    }

    /*
     *******************************
     * Conger
     * **********************
     */
    @PostMapping("/conges/save")
    public String saveConges(HttpSession session, Conge conge) {
        if (conge.getId() != null) {
            conge.setId((Long) conge.getId());
        }
        Personnel personnel = conge.getPersonnel();
        List<Conge> c = new ArrayList<Conge>();
        for (Conge con : personnel.getConge()) {
            c.add(con);
        }
        c.add(this.congeService.save(conge));
        personnel.setConge(c);
        personnelService.save(personnel);
        session.setAttribute("infos", "Enregistrement effectuer");
        return "redirect:/admin/paies/voir-personnel/" + conge.getPersonnel().getId();
    }

    @RequestMapping("/conge/delete/{id}")
    public String deleteConge(HttpSession session, @PathVariable long id) {

        Conge conge = congeService.findOne(id);
        try {
            Personnel personnel = conge.getPersonnel();
            List<Conge> c = personnel.getConge();
            c.remove(conge);
            personnel.setConge(c);
            personnelService.save(personnel);
            this.congeService.delete(conge);
            session.setAttribute("infos", "Suppression Effectuer !");
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression impossible");
        }
        return "redirect:/admin/paies/voir-personnel/" + conge.getPersonnel().getId();

    }
	
    @ResponseBody
	 @GetMapping(value = "/conges/personnel")
	 public String getPersonnelConge(@RequestParam("pid") Long id) throws IOException {
	      Personnel p=personnelService.findOne(id);
	      //congé normale tous les employers
	      Contrat contrat=p.getContrat();
	      LocalDate date =contrat.getDateEmbauche();
	      LocalDate auDay = LocalDate.now();
	      int thisYear=auDay.getYear();
	      int thisMont=auDay.getMonthValue();
	      int thisEmbaucheYear=date.getYear();
	      int thisEmbaucheMont=date.getMonthValue();
	      int nombreAnneeTravail=thisYear-thisEmbaucheYear;
	      int nombreDeMoisDeTravail=0;
	      nombreDeMoisDeTravail=thisMont-thisEmbaucheMont;
	      
	      int nobreTotalEnMois=nombreAnneeTravail*12+nombreDeMoisDeTravail;
	      int nombreDeCongeNormale=nobreTotalEnMois*2;
	      //congé enfant de moins de 5ans
	      List<Enfants> enfants=p.getEnfants();
	      int nombresDeCongeEnfants=0;
	      if(!enfants.isEmpty()){
	    	  for(Enfants e: enfants) {
	    		  LocalDate dateNaissanceEnfant=e.getDate_naissance();
	    		  int anneNaisEnfant=dateNaissanceEnfant.getYear();
	    		  int moisNaisEnfant=dateNaissanceEnfant.getMonthValue();
	    		  int ageAnneeEnfant=thisYear-anneNaisEnfant;
	    		  int moisEnfant=thisMont-moisNaisEnfant;
	    		  int totalMoisEnfant=ageAnneeEnfant*12+moisEnfant;
	    		  if(totalMoisEnfant<60) {
	    			  nombresDeCongeEnfants+=2*totalMoisEnfant;  
	    		  }
	    	  }
	      }
	      //recupération des congés du personnel
	      List<Conge> conges=p.getConge();
	      Long nombreDeCongePris=(long) 0;
	      if(!conges.isEmpty()) {
	    	  for(Conge c : conges) {
	    		  Long jours=c.getDuree();
	    		  //on exclut les congés de type id 2 martenité
	    		  if(c.getTypeConge().getId()!=(long)2) {
	    		  nombreDeCongePris+=jours;
	    		  }
	    	  }
	      }
	      //nombre de jours prix: nombreDeCongePris;
	      //nombre de jours dont il a droit depuis son embauche: int nombre=nombreDeCongeNormale+nombresDeCongeEnfants;
	      int nombre=nombreDeCongeNormale+nombresDeCongeEnfants;
	      //nombre de jours de congés a prendre encore depuis le début: float nombreAprendre=nombre-nombreDeCongePris;
	      float nombreAprendre=nombre-nombreDeCongePris;
	      //test : http://localhost:8085/admin/paies/conges/personnel?pid=1
	      return "Nombre de mois au travail: "+nombreDeMoisDeTravail+",<br> Nombre d'années au travail:"+nombreAnneeTravail+",<br> nombre total en mois "+nobreTotalEnMois+",<br> Arriver année: "+thisEmbaucheYear+",<br> Moi d'arriver : "+thisEmbaucheMont+",<br> Nombre de congé normal : "+nombreDeCongeNormale+",<br> Nombre  de congé enfant: "+nombresDeCongeEnfants+",<br> Nombre total de jours de congé : "+nombre+",<br> nombre de jours pris : "+nombreDeCongePris+",<br> nombre restant a prendre : "+nombreAprendre;
	 }
    @ResponseBody
	 @GetMapping(value = "/prets/personnel")
	 public String getPersonnelPrets(@RequestParam("pid") Long id) throws IOException {
	      Personnel p=personnelService.findOne(id);
	      //congé normale tous les employers
	      Contrat contrat=p.getContrat();
	      LocalDate date =contrat.getDateEmbauche();
	      LocalDate auDay = LocalDate.now();
	      int thisYear=auDay.getYear();
	      int thisMont=auDay.getMonthValue();
	      int thisEmbaucheYear=date.getYear();
	      int thisEmbaucheMont=date.getMonthValue();
	      int nombreAnneeTravail=thisYear-thisEmbaucheYear;
	      int nombreDeMoisDeTravail=0;
	      nombreDeMoisDeTravail=thisMont-thisEmbaucheMont;
	      
	      int nobreTotalEnMois=nombreAnneeTravail*12+nombreDeMoisDeTravail;
	      List<Prets> pretses=p.getPrets();
	      float valeurs=0;
	      float retenues=0;
	      int nombre=0;
	      float aRetenir=0;
	      if(!pretses.isEmpty()) {
	    	  for(Prets prets : pretses) {
	    		  valeurs+= prets.getValeur();
	    		  nombre++;	 
	    		  retenues+=prets.getRetenueMensuelle();
	    	  }
	    	  aRetenir=valeurs*retenues/100;
	      }
	      //valeurs total : valeurs
	      //retenues total: retenues
	      //montant a retenir le mois c'est la moyenne sommes des valeurs sur la sommes des retenus : aRetenir
	      
	      //test : http://localhost:8085/admin/paies/prets/personnel?pid=1
	      return "Nombre de mois au travail: "+nombreDeMoisDeTravail+",<br> Nombre d'années au travail:"+nombreAnneeTravail+",<br> nombre total en mois "+nobreTotalEnMois+",<br> Arriver année: "+thisEmbaucheYear+",<br> Moi d'arriver : "+thisEmbaucheMont+",<br> Nombre de prêts : "+nombre+",<br> Valeur total des prets : "+valeurs+",<br> retenu total des prets :"+retenues+",<br> A retenir ce mois : "+aRetenir;
	 }
    @ResponseBody
	 @GetMapping(value = "/credits/personnel")
	 public String getPersonnelCredits(@RequestParam("pid") Long id) throws IOException {
	      Personnel p=personnelService.findOne(id);
	      //congé normale tous les employers
	      Contrat contrat=p.getContrat();
	      LocalDate date =contrat.getDateEmbauche();
	      LocalDate auDay = LocalDate.now();
	      int thisYear=auDay.getYear();
	      int thisMont=auDay.getMonthValue();
	      int thisEmbaucheYear=date.getYear();
	      int thisEmbaucheMont=date.getMonthValue();
	      int nombreAnneeTravail=thisYear-thisEmbaucheYear;
	      int nombreDeMoisDeTravail=0;
	      nombreDeMoisDeTravail=thisMont-thisEmbaucheMont;
	      
	      int nobreTotalEnMois=nombreAnneeTravail*12+nombreDeMoisDeTravail;
	      List<Credits> credits=p.getCredits();
	      float valeurs=0;
	      float retenues=0;
	      float interet=0;
	      int nombre=0;
	      float aRetenir=0;
	      if(!credits.isEmpty()) {
	    	  for(Credits c : credits) {
	    		  valeurs+= c.getValeur();
	    		  nombre++;	 
	    		  retenues+=c.getRetenue();
	    		  interet+=c.getTauxInteret();
	    	  }
	    	  valeurs+=interet;
	    	  interet=(interet*valeurs)/100;
	    	  aRetenir=(valeurs*retenues/100);
	      }
	      //valeurs total : valeurs
	      //retenues total: retenues
	      //interets total: interet
	      //montant a retenir le mois c'est la moyenne sommes des valeurs sur la sommes des retenus : aRetenir
	      
	      //test : http://localhost:8085/admin/paies/credits/personnel?pid=1
	      return "Nombre de mois au travail: "+nombreDeMoisDeTravail+",<br> Nombre d'années au travail:"+nombreAnneeTravail+",<br> nombre total en mois "+nobreTotalEnMois+",<br> Arriver année: "+thisEmbaucheYear+",<br> Moi d'arriver : "+thisEmbaucheMont+",<br> Nombre de prêts : "+nombre+",<br> Valeur total des credits : "+valeurs+",<br> retenu total des credits :"+retenues+",<br> montant total des interets :"+interet+",<br> A retenir ce mois : "+aRetenir;
	 }
    @ResponseBody
	 @GetMapping(value = "/avances/personnel")
	 public String getPersonnelAvances(@RequestParam("pid") Long id) throws IOException {
	      Personnel p=personnelService.findOne(id);
	      //congé normale tous les employers
	      Contrat contrat=p.getContrat();
	      LocalDate date =contrat.getDateEmbauche();
	      LocalDate auDay = LocalDate.now();
	      int thisYear=auDay.getYear();
	      int thisMont=auDay.getMonthValue();
	      int thisEmbaucheYear=date.getYear();
	      int thisEmbaucheMont=date.getMonthValue();
	      int nombreAnneeTravail=thisYear-thisEmbaucheYear;
	      int nombreDeMoisDeTravail=0;
	      nombreDeMoisDeTravail=thisMont-thisEmbaucheMont;
	      
	      int nobreTotalEnMois=nombreAnneeTravail*12+nombreDeMoisDeTravail;
	      List<Avances> avances=p.getAvances();
	      float valeurs=0;
	      int nombre=0;
	      if(!avances.isEmpty()) {
	    	  for(Avances a : avances) {
	    		  valeurs+= a.getValeur();
	    		  nombre++;
	    	  }
	      }
	      //valeurs total des avances: valeurs
	      
	      //test : http://localhost:8085/admin/paies/avances/personnel?pid=1
	      return "Nombre de mois au travail: "+nombreDeMoisDeTravail+",<br> Nombre d'années au travail:"+nombreAnneeTravail+",<br> nombre total en mois "+nobreTotalEnMois+",<br> Arriver année: "+thisEmbaucheYear+",<br> Moi d'arriver : "+thisEmbaucheMont+",<br> Nombre des avances : "+nombre+",<br> Valeur total des avances : "+valeurs;
	 }
    @ResponseBody
	 @GetMapping(value = "/primes-variables/personnel")
	 public String getPersonnelPrimes(@RequestParam("pid") Long id) throws IOException {
	      Personnel p=personnelService.findOne(id);
	      //congé normale tous les employers
	      Contrat contrat=p.getContrat();
	      LocalDate date =contrat.getDateEmbauche();
	      LocalDate auDay = LocalDate.now();
	      int thisYear=auDay.getYear();
	      int thisMont=auDay.getMonthValue();
	      int thisEmbaucheYear=date.getYear();
	      int thisEmbaucheMont=date.getMonthValue();
	      int nombreAnneeTravail=thisYear-thisEmbaucheYear;
	      int nombreDeMoisDeTravail=0;
	      nombreDeMoisDeTravail=thisMont-thisEmbaucheMont;
	      
	      int nobreTotalEnMois=nombreAnneeTravail*12+nombreDeMoisDeTravail;
	      List<PrimesVariables> primes=p.getPrimesVariables();
	      float valeurs=0;
	      int nombre=0;
	      if(!primes.isEmpty()) {
	    	  for(PrimesVariables pv : primes) {
	    		  valeurs+= pv.getValeur();
	    		  nombre++;
	    	  }
	      }
	      //valeurs total des avances: valeurs
	      
	      //test : http://localhost:8085/admin/paies/primes-variables/personnel?pid=1
	      return "Nombre de mois au travail: "+nombreDeMoisDeTravail+",<br> Nombre d'années au travail:"+nombreAnneeTravail+",<br> nombre total en mois "+nobreTotalEnMois+",<br> Arriver année: "+thisEmbaucheYear+",<br> Moi d'arriver : "+thisEmbaucheMont+",<br> Nombre de primes variables : "+nombre+",<br> Valeur total des primes variables : "+valeurs;
	 }
    
    @ResponseBody
	 @GetMapping(value = "/primes-fixes/personnel")
	 public String getPersonnelPrimesFixes(@RequestParam("pid") Long id) throws IOException {
	      Personnel p=personnelService.findOne(id);
	      //congé normale tous les employers
	      Contrat contrat=p.getContrat();
	      LocalDate date =contrat.getDateEmbauche();
	      LocalDate auDay = LocalDate.now();
	      int thisYear=auDay.getYear();
	      int thisMont=auDay.getMonthValue();
	      int thisEmbaucheYear=date.getYear();
	      int thisEmbaucheMont=date.getMonthValue();
	      int nombreAnneeTravail=thisYear-thisEmbaucheYear;
	      int nombreDeMoisDeTravail=0;
	      nombreDeMoisDeTravail=thisMont-thisEmbaucheMont;
	      
	      int nobreTotalEnMois=nombreAnneeTravail*12+nombreDeMoisDeTravail;
	      List<PrimesFixes> primes=p.getPrimesFixes();
	      float valeurs=0;
	      int nombre=0;
	      if(!primes.isEmpty()) {
	    	  for(PrimesFixes pv : primes) {
	    		  valeurs+= pv.getValeur();
	    		  nombre++;
	    	  }
	      }
	      //valeurs total des avances: valeurs
	      
	      //test : http://localhost:8085/admin/paies/primes-fixes/personnel?pid=1
	      return "Nombre de mois au travail: "+nombreDeMoisDeTravail+",<br> Nombre d'années au travail:"+nombreAnneeTravail+",<br> nombre total en mois "+nobreTotalEnMois+",<br> Arriver année: "+thisEmbaucheYear+",<br> Moi d'arriver : "+thisEmbaucheMont+",<br> Nombre de primes fixes : "+nombre+",<br> Valeur total des primes fixes : "+valeurs;
	 }


    /*
     *******************************
     * Prime Variable
     * **********************
     */
    @PostMapping("/primeVariable/save")
    public String savePv(HttpSession session, PrimesVariables pv) {
        if (pv.getId() != null) {
            pv.setId((Long) pv.getId());
        }
        Personnel personnel = pv.getPersonnel();
        List<PrimesVariables> pvs = new ArrayList<PrimesVariables>();
        for (PrimesVariables p : personnel.getPrimesVariables()) {
            pvs.add(p);
        }
        pvs.add(this.primeVariableService.save(pv));
        personnel.setPrimesVariables(pvs);
        personnelService.save(personnel);
        session.setAttribute("infos", "Enregistrement effectuer");
        return "redirect:/admin/paies/voir-personnel/" + pv.getPersonnel().getId();
    }

    @RequestMapping("/primeVariable/delete/{id}")
    public String deletePv(HttpSession session, @PathVariable long id) {

        PrimesVariables pv = primeVariableService.findOne(id);
        try {
            Personnel personnel = pv.getPersonnel();
            List<PrimesVariables> c = personnel.getPrimesVariables();
            c.remove(pv);
            personnel.setPrimesVariables(c);
            personnelService.save(personnel);
            this.primeVariableService.delete(pv);
            session.setAttribute("infos", "Suppression Effectuer !");
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression impossible");
        }
        return "redirect:/admin/paies/voir-personnel/" + pv.getPersonnel().getId();

    }

    /*
     *******************************
     * Avances
     * **********************
     */
    @PostMapping("/avance/save")
    public String saveAvance(HttpSession session, Avances avances) {
        if (avances.getId() != null) {
            avances.setId((Long) avances.getId());
        }
        Personnel personnel = avances.getPersonnel();
        List<Avances> avs = new ArrayList<Avances>();
        for (Avances av : personnel.getAvances()) {
            avs.add(av);
        }
        avs.add(this.avancesService.save(avances));
        personnel.setAvances(avs);
        personnelService.save(personnel);
        session.setAttribute("infos", "Enregistrement effectuer");
        return "redirect:/admin/paies/voir-personnel/" + avances.getPersonnel().getId();
    }

    @RequestMapping("/avance/delete/{id}")
    public String deleteAvance(HttpSession session, @PathVariable long id) {

        Avances avances = avancesService.findOne(id);
        try {
            Personnel personnel = avances.getPersonnel();
            List<Avances> avs = personnel.getAvances();
            avs.remove(avances);
            personnel.setAvances(avs);
            personnelService.save(personnel);
            this.avancesService.delete(avances);
            session.setAttribute("infos", "Suppression Effectuer !");
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression impossible");
        }
        return "redirect:/admin/paies/voir-personnel/" + avances.getPersonnel().getId();

    }

    /*
     *******************************
     * Prets
     * **********************
     */
    @PostMapping("/prets/save")
    public String savePrets(HttpSession session, Prets prets) {
        if (prets.getId() != null) {
            prets.setId((Long) prets.getId());
        }
        Personnel personnel = prets.getPersonnel();
        List<Prets> prts = new ArrayList<Prets>();
        for (Prets prt : personnel.getPrets()) {
            prts.add(prt);
        }
        prts.add(this.pretsService.save(prets));
        personnel.setPrets(prts);
        personnelService.save(personnel);
        session.setAttribute("infos", "Enregistrement effectuer");
        return "redirect:/admin/paies/voir-personnel/" + prets.getPersonnel().getId();
    }

    @RequestMapping("/prets/delete/{id}")
    public String deletePrets(HttpSession session, @PathVariable long id) {

        Prets prets = pretsService.findOne(id);
        try {
            Personnel personnel = prets.getPersonnel();
            List<Prets> prts = personnel.getPrets();
            prts.remove(prets);
            personnel.setPrets(prts);
            personnelService.save(personnel);
            this.pretsService.delete(prets);
            session.setAttribute("infos", "Suppression Effectuer !");
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression impossible");
        }
        return "redirect:/admin/paies/voir-personnel/" + prets.getPersonnel().getId();

    }
    /*
     *******************************
     * Prime Fixe
     * **********************
     */

    @PostMapping("/addPrimeFixe/save")
    public String savepF(HttpSession session, @RequestParam("primeFixe") PrimesFixes pf,@RequestParam("personnel") Personnel personnel){
        List<PrimesFixes> prts=new ArrayList<PrimesFixes>();
        for (PrimesFixes prt : personnel.getPrimesFixes()){
            prts.add(prt);
        }
        if (!prts.contains(pf)){
            prts.add(pf);
            session.setAttribute("infos","Enregistrement effectuer");
        }else {
            session.setAttribute("infos","cette Prime A deja été accorder au Personnel "+personnel.getNom());
        }
        personnel.setPrimesFixes(prts);
        personnelService.save(personnel);
        return "redirect:/admin/paies/voir-personnel/"+personnel.getId();
    }
    @RequestMapping("/addPrimeFixe/delete/{id}/{personnel}")
    public String deletePf(HttpSession session, @PathVariable("id") long id,@PathVariable("personnel") long personnel) {

       PrimesFixes pf = primesFixesService.findOne(id);
        Personnel perso=personnelService.findOne(personnel);
        try {
            List<PrimesFixes> prts = perso.getPrimesFixes();
            prts.remove(pf);
            perso.setPrimesFixes(prts);
            personnelService.save(perso);
            session.setAttribute("infos", "La prime a ete retiré avec success!");
        } catch (Exception e) {
            session.setAttribute("infos", "Suppression impossible");
        }
        return "redirect:/admin/paies/voir-personnel/" + perso.getId();

    }
    /*
     *******************************
     * Bulletin
     * **********************
     */
    @GetMapping("/bulletins")
    public String bulletin(HttpSession session, Model model) {
        if (session.getAttribute("infos") != null) {
            model.addAttribute("info", session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("personnels", personnelService.findAll());

        return "/administration/paies/bulletin/index";
    }
    @GetMapping("/bulletin/personnel/{id}")
    public String viewBulletin(HttpSession session, Model model,@PathVariable("id") Long id) {
        if (session.getAttribute("infos") != null) {
            model.addAttribute("info", session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        
        Personnel personnel=personnelService.findOne(id);
        Contrat contrat=personnel.getContrat();
	      LocalDate date =contrat.getDateEmbauche();
	      LocalDate auDay = LocalDate.now();
	      int thisYear=auDay.getYear();
	      int thisMont=auDay.getMonthValue();
	      int thisEmbaucheYear=date.getYear();
	      int thisEmbaucheMont=date.getMonthValue();
	      int nombreAnneeTravail=thisYear-thisEmbaucheYear;
	      int nombreDeMoisDeTravail=0;
	      nombreDeMoisDeTravail=thisMont-thisEmbaucheMont;
	      
	      int nobreTotalEnMois=nombreAnneeTravail*12+nombreDeMoisDeTravail;
	      int nombreDeCongeNormale=nobreTotalEnMois*2;
	      //congé enfant de moins de 5ans
	      List<Enfants> enfants=personnel.getEnfants();
	      int nombresDeCongeEnfants=0;
	      if(!enfants.isEmpty()){
	    	  for(Enfants e: enfants) {
	    		  LocalDate dateNaissanceEnfant=e.getDate_naissance();
	    		  int anneNaisEnfant=dateNaissanceEnfant.getYear();
	    		  int moisNaisEnfant=dateNaissanceEnfant.getMonthValue();
	    		  int ageAnneeEnfant=thisYear-anneNaisEnfant;
	    		  int moisEnfant=thisMont-moisNaisEnfant;
	    		  int totalMoisEnfant=ageAnneeEnfant*12+moisEnfant;
	    		  if(totalMoisEnfant<60) {
	    			  nombresDeCongeEnfants+=2*totalMoisEnfant;  
	    		  }
	    	  }
	      }
	      //recupération des congés du personnel
	      List<Conge> conges=personnel.getConge();
	      Long nombreDeCongePris=(long) 0;
	      if(!conges.isEmpty()) {
	    	  for(Conge c : conges) {
	    		  Long jours=c.getDuree();
	    		  //on exclut les congés de type id 2 martenité
	    		  if(c.getTypeConge().getId()!=(long)2) {
	    		  nombreDeCongePris+=jours;
	    		  }
	    	  }
	      }
	      //nombre de jours prix: nombreDeCongePris;
	      //nombre de jours dont il a droit depuis son embauche: int nombre=nombreDeCongeNormale+nombresDeCongeEnfants;
	      int nombre=nombreDeCongeNormale+nombresDeCongeEnfants;
	      //nombre de jours de congés a prendre encore depuis le début: float nombreAprendre=nombre-nombreDeCongePris;
	      float nombreAprendre=nombre-nombreDeCongePris;
	      float nombreHeurs=30*9;
	      BulletinPaie b=new BulletinPaie();
	      b.setNbreCongesPayes(nombreDeCongePris);
	      b.setNbreHeureTravaillees(nombreHeurs);
	      NumberFormat nf = new DecimalFormat("00");
	      b.setNbreJoursFeries((long) 0);
	      b.setDescription("Du "+LocalDate.now().getYear()+"-"+nf.format(LocalDate.now().getMonthValue())+"-00 au "+LocalDate.now());
	      List<BulletinPaie> bulletins=bulletinPaieService.findAll();
	      int nombreBuletin=bulletins.size();
	      if(nombreBuletin<nobreTotalEnMois) {
	    	  model.addAttribute("bulletin", b); 
	      }
          model.addAttribute("personnel", personnel);

        return "/administration/paies/bulletin/liste";
    }
    
    @PostMapping("/createBulletinPaie/save")
    public String saveBulletinPaie(HttpSession session, @Valid BulletinPaie bulletin,@RequestParam("per") Long pID){
        List<BulletinPaie> blts=new ArrayList<>();
        Personnel personnel=personnelService.findOne(pID);
        for (BulletinPaie b : personnel.getBulletinPaie()){
        	blts.add(b);
        }
        bulletinPaieService.save(bulletin);
        if (!blts.contains(bulletin)){
        	blts.add(bulletin);
        }
        session.setAttribute("infos","Enregistrement effectuer");
        personnel.setBulletinPaie(blts);
        //Gestion des éléments de la fiche de paie
        //prêts
          List<Prets> pretss=personnel.getPrets();
          float valeurs=0;
	      float retenues=0;
	      int nombre=0;
	      float aRetenir=0;
	      if(!pretss.isEmpty()) {
	    	  for(Prets prets : pretss) {
	    		  valeurs+= prets.getValeur();
	    		  nombre++;	 
	    		  retenues+=prets.getRetenueMensuelle();
	    	  }
	    	  aRetenir=valeurs*retenues/100;
	      }
	      //valeurs total : valeurs
	      //retenues total: retenues
	      //montant a retenir le mois c'est la moyenne sommes des valeurs sur la sommes des retenus : aRetenir
        
        personnelService.save(personnel);
        bulletinsReportService.generateBulletinsPdfReport(personnel.getBulletinPaie());
        return "redirect:/admin/paies/bulletin/personnel/"+personnel.getId();
    }

    @GetMapping("/bulletin/update/personnel/{id}/{pid}")
    public String updateBulletinPaie(HttpSession session, Model model,@PathVariable("id") Long id,@PathVariable("pid") Long pid) {
        if (session.getAttribute("infos") != null) {
            model.addAttribute("info", session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("bulletin", bulletinPaieService.findOne(id));
        Personnel personnel=personnelService.findOne(pid);
        model.addAttribute("personnel", personnel);

        return "/administration/paies/bulletin/liste";
    }

}
