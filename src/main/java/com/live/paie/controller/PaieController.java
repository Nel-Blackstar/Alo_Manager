package com.live.paie.controller;

import com.live.core.entities.Personnel;
import com.live.core.service.PersonnelService;
import com.live.paie.entities.*;
import com.live.paie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/paies")
public class PaieController {
    @Autowired
    public ProfessionService professionService;
    @Autowired
    public CNPSService cnpsService;
    @Autowired
    public TypeCongeService typeCongeService;
    @Autowired
    public TypeContratService typeContratService;
    @Autowired
    public ContratService contratService;
    @Autowired
    public PrimesFixesService primesFixesService;
    @Autowired
    public EnfantsService enfantsService;
    @Autowired
    public PersonnelService personnelService;
    /*
     *******************************
     * Debut du module de paies
     * **********************
     */
    @GetMapping("/show")
    public String paies(HttpSession session, Model model){

        return "/administration/paies/index";
    }
    /*
     *******************************
     * Gestion des enfants
     * **********************
     */
    @GetMapping("/enfants")
    public String enfants(HttpSession session, Model model){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("enfant",new Enfants());
        model.addAttribute("enfants",enfantsService.findAll());
        model.addAttribute("personnels",personnelService.findAll());
        return "administration/paies/enfants/index";
    }
    @PostMapping("/enfants/save")
    public String saveEnfants(HttpSession session, @Valid Enfants enfant, BindingResult bindingResult, Model model,@RequestParam("personnels") List<Personnel> ps){
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("enfant",enfant);
            model.addAttribute("enfants",enfantsService.findAll());
            model.addAttribute("personnels",personnelService.findAll());
       	 	return "administration/paies/enfants/index";
	 	}
    	enfantsService.save(enfant);
    	enfant.setPersonnel(ps);
    	if (enfant.getId() != null){
        	enfant.setId((Long) enfant.getId());
        }
    	List<Enfants> e=new ArrayList<>();
    	e.add(enfant);
    	for(Personnel p : ps) {
    		for(Enfants ef : p.getEnfants()) {
    			e.add(ef);
    		}
    		p.setEnfants(e);
    		personnelService.save(p);
    	}
    	enfantsService.save(enfant);
        session.setAttribute("infos","Enregistrement effectuer");
        return "redirect:/admin/paies/enfants";
    }
    @RequestMapping("/enfants/update/{id}")
    public String updateEnfant(HttpSession session,Model model,@PathVariable long id) {

        Enfants enfant = enfantsService.findOne(id);
        model.addAttribute("enfant", enfant);
        model.addAttribute("enfants",enfantsService.findAll());
        model.addAttribute("personnels",personnelService.findAll());
        return "administration/paies/enfants/index";

    }
    @RequestMapping("/enfants/delete/{id}")
    public String deleteEnfants(HttpSession session,@PathVariable long id) {

        try {
            Enfants enfant = enfantsService.findOne(id);
            this.enfantsService.delete(enfant);
        }catch (Exception e){
            session.setAttribute("infos","Suppression Impossible , Les Personnels sont aussi parents de cette enfants, Supprimer le Personnel et Réessayer");
        }
        session.setAttribute("infos","Suppression Effectuer !");
        return "redirect:/admin/paies/enfants";

    }
    /*
     *******************************
     * Professions
     * **********************
     */
    @GetMapping("/professions")
    public String professions(HttpSession session, Model model){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("profession",new Profession());
        model.addAttribute("professions",professionService.findAll());
        model.addAttribute("cnpss",cnpsService.findAll());
        return "/administration/paies/Professions/index";
    }

    @PostMapping("/profession/save")
    public String saveProfessions(HttpSession session, Profession profession){
        if (profession.getId() != null){
            profession.setId((Long) profession.getId());
        }
        this.professionService.save(profession);
        session.setAttribute("infos","Enregistrement effectuer");
        return "redirect:/admin/paies/professions";
    }

    @RequestMapping("/profession/update/{id}")
    public String updateProfession(HttpSession session,Model model,@PathVariable long id) {

        Profession profession = professionService.findOne(id);
        model.addAttribute("profession", profession);
        model.addAttribute("professions",professionService.findAll());
        model.addAttribute("cnpss",cnpsService.findAll());
        return "/administration/paies/Professions/index";

    }
    @RequestMapping("/profession/delete/{id}")
    public String deleteProfession(HttpSession session,@PathVariable long id) {

        try {
            Profession profession = professionService.findOne(id);
            this.professionService.delete(profession);
        }catch (Exception e){
            session.setAttribute("infos","Suppression Impossible , Le Personnel Exerce cette Profession, Supprimer le Personnel occupant cette Profession et Réessayer");
        }
        session.setAttribute("infos","Suppression Effectuer !");
        return "redirect:/admin/paies/professions";

    }

    /*
     *******************************
     * CNPS
     * **********************
     */
    @GetMapping("/cnps")
    public String cnps(HttpSession session, Model model){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("cnps",new CNPS());
        model.addAttribute("cnpss",cnpsService.findAll());
        return "/administration/paies/cnps/index";
    }

    @PostMapping("/cnps/save")
    public String saveCnps(HttpSession session, CNPS cnps){
        if (cnps.getId() != null){
            cnps.setId((Long) cnps.getId());
        }
        this.cnpsService.save(cnps);
        session.setAttribute("infos","Enregistrement effectuer");
        return "redirect:/admin/paies/cnps";
    }

    @RequestMapping("/cnps/update/{id}")
    public String updateCnps(HttpSession session,Model model,@PathVariable long id) {

        CNPS cnps = cnpsService.findOne(id);
        model.addAttribute("cnps", cnps);
        model.addAttribute("cnpss",cnpsService.findAll());
        return "/administration/paies/cnps/index";

    }
    @RequestMapping("/cnps/delete/{id}")
    public String deleteCnps(HttpSession session,@PathVariable long id) {

        CNPS cnps = cnpsService.findOne(id);
        try {
            this.cnpsService.delete(cnps);
            session.setAttribute("infos","Suppression Effectuer !");
        }catch (Exception e){
            session.setAttribute("infos","Suppression impossible, Cette CNPS est Utilisé Pour des Professions");
        }
        return "redirect:/admin/paies/cnps";

    }


    /*
     *******************************
     * Type Congés
     * **********************
     */
    @GetMapping("/typeConge")
    public String typeConger(HttpSession session, Model model){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("typeConge",new TypeConge());
        model.addAttribute("typeConges",typeCongeService.findAll());
        return "/administration/paies/typeConge/index";
    }

    @PostMapping("/typeConge/save")
    public String saveTypeConge(HttpSession session, TypeConge typeConge){
        if (typeConge.getId() != null){
            typeConge.setId((Long) typeConge.getId());
        }
        this.typeCongeService.save(typeConge);
        session.setAttribute("infos","Enregistrement effectuer");
        return "redirect:/admin/paies/typeConge";
    }

    @RequestMapping("/typeConge/update/{id}")
    public String updateTypeConge(HttpSession session,Model model,@PathVariable long id) {

        TypeConge typeConge = typeCongeService.findOne(id);
        model.addAttribute("typeConge", typeConge);
        model.addAttribute("typeConges",typeCongeService.findAll());
        return "/administration/paies/typeConge/index";

    }
    @RequestMapping("/typeConge/delete/{id}")
    public String deleteTypeConge(HttpSession session,@PathVariable long id) {

        TypeConge typeConge = typeCongeService.findOne(id);
        try {
            this.typeCongeService.delete(typeConge);
            session.setAttribute("infos","Suppression Effectuer !");
        }catch (Exception e){
            session.setAttribute("infos","Suppression impossible, Ce type de conger est Utilisé Pour le Personnel");
        }
        return "redirect:/admin/paies/typeConge";

    }


    /*
     *******************************
     * Type Contract
     * **********************
     */
    @GetMapping("/typeContrat")
    public String typeContrat(HttpSession session, Model model){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("typeContrat",new TypeContrat());
        model.addAttribute("typeContrats",typeContratService.findAll());
        return "/administration/paies/typeContrat/index";
    }

    @PostMapping("/typeContrat/save")
    public String saveTypeContrat(HttpSession session, TypeContrat typeContrat){
        if (typeContrat.getId() != null){
            typeContrat.setId((Long) typeContrat.getId());
        }
        this.typeContratService.save(typeContrat);
        session.setAttribute("infos","Enregistrement effectuer");
        return "redirect:/admin/paies/typeContrat";
    }

    @RequestMapping("/typeContrat/update/{id}")
    public String updateTypeContrat(HttpSession session,Model model,@PathVariable long id) {

        TypeContrat typeContrat = typeContratService.findOne(id);
        model.addAttribute("typeContrat", typeContrat);
        model.addAttribute("typeContrats",typeContratService.findAll());
        return "/administration/paies/typeContrat/index";

    }
    @RequestMapping("/typeContrat/delete/{id}")
    public String deleteTypeContrat(HttpSession session,@PathVariable long id) {

        TypeContrat typeContrat = typeContratService.findOne(id);
        try {
            this.typeContratService.delete(typeContrat);
            session.setAttribute("infos","Suppression Effectuer !");
        }catch (Exception e){
            session.setAttribute("infos","Suppression impossible, Ce type de Contrat est Utilisé Pour des Contract avec le personnel");
        }
        return "redirect:/admin/paies/typeContrat";

    }


    /*
     *******************************
     * Primes Fixe
     * **********************
     */
    @GetMapping("/primeFixe")
    public String primeFixe(HttpSession session, Model model){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("primeFixe",new PrimesFixes());
        model.addAttribute("primeFixes",primesFixesService.findAll());
        return "/administration/paies/primeFixe/index";
    }

    @PostMapping("/primeFixe/save")
    public String savePrimeFixe(HttpSession session, PrimesFixes primeFixe){
        if (primeFixe.getId() != null){
            primeFixe.setId((Long) primeFixe.getId());
        }
        this.primesFixesService.save(primeFixe);
        session.setAttribute("infos","Enregistrement effectuer");
        return "redirect:/admin/paies/primeFixe";
    }

    @RequestMapping("/primeFixe/update/{id}")
    public String updatePrimeFixe(HttpSession session,Model model,@PathVariable long id) {

        PrimesFixes primeFixe = primesFixesService.findOne(id);
        model.addAttribute("primeFixe", primeFixe);
        model.addAttribute("primeFixes",primesFixesService.findAll());
        return "/administration/paies/primeFixe/index";

    }
    @RequestMapping("/primeFixe/delete/{id}")
    public String deletePrimeFixe(HttpSession session,@PathVariable long id) {

        PrimesFixes primeFixe = primesFixesService.findOne(id);
        try {
            this.primesFixesService.delete(primeFixe);
            session.setAttribute("infos","Suppression Effectuer !");
        }catch (Exception e){
            session.setAttribute("infos","Suppression impossible, Cette Prime est Attribuer a un ou plusieur personnel");
        }
        return "redirect:/admin/paies/primeFixe";

    }


}
