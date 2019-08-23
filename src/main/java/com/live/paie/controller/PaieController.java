package com.live.paie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin/paie")
public class PaieController {
    /*
     *******************************
     * Debut du module de paies
     * **********************
     */
    @GetMapping("show")
    public String paies(HttpSession session){

        return "/administration/paies/index";
    }
}
