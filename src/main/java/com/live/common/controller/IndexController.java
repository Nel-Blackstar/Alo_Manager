package com.live.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // Point d'entrée dans l'application
    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    // Acces à l'interface d'aministration
    @GetMapping("/admin")
    public String administration(Model model) {
        return "administration/administration";
    }

    // Acces à l'interface des apprenants
    @GetMapping("/apprenants")
    public String reception(Model model) {
        return "apprenants/apprenant";
    }

    // Acces à l'interface du personnel
    @GetMapping("/personnels")
    public String restaurant(Model model) {
        return "personnels/personnel";
    }

    // Acces à l'interface de comptabilité
    @GetMapping("/comptabilites")
    public String comptabilite(Model model) {
        return "comptables/comptabilite";
    }
}
