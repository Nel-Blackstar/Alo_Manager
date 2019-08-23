package com.live.paie.controller;

import com.live.paie.entities.Profession;
import com.live.paie.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin/paies")
public class PaieController {
    @Autowired
    public ProfessionService professionService;
    /*
     *******************************
     * Debut du module de paies
     * **********************
     */
    @GetMapping("/show")
    public String paies(HttpSession session, Model model){

        return "/administration/paies/index";
    }

    @GetMapping("/professions")
    public String professions(HttpSession session, Model model){
        if (session.getAttribute("infos") != null){
            model.addAttribute("info",session.getAttribute("infos"));
            session.removeAttribute("infos");
        }
        model.addAttribute("profession",new Profession());
        model.addAttribute("professions",professionService.findAll());
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
        return "/administration/paies/Professions/index";

    }
    @RequestMapping("/profession/delete/{id}")
    public String deleteProfession(HttpSession session,@PathVariable long id) {

        Profession profession = professionService.findOne(id);
        this.professionService.delete(profession);
        session.setAttribute("infos","Suppression Effectuer !");
        return "redirect:/admin/paies/professions";

    }


}
