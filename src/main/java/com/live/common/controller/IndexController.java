package com.live.common.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.live.core.entities.Users;
import com.live.core.repository.UsersRepository;

@Controller
public class IndexController {
	@Autowired
    UsersRepository usersRepository;
	// Point d'entrée dans l'application
    @GetMapping("/")
    public String home(Model model,HttpSession session) {
        return "index";
    }

    // Acces à l'interface d'aministration
    @GetMapping("/admin")
    public String administration(Model model) {
        return "administration/administration";
    }

    // Acces à l'interface des apprenants
    @GetMapping("/rh")
    public String rh(Model model) {
        return "rh/ressources";
    }

    // Acces à l'interface des apprenants
    @GetMapping("/moniteurs")
    public String moniteur(Model model) {
        return "moniteurs/moniteurs";
    }

    // Acces à l'interface du personnel
    @GetMapping("/personnels")
    public String personnel(Model model) {
        return "personnels/personnel";
    }

    // Acces à l'interface de comptabilité
    @GetMapping("/comptabilites")
    public String comptabilite(Model model) {
        return "comptables/comptable";
    }
}
