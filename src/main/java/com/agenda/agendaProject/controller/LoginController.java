package com.agenda.agendaProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        // dps nois passa uma msg ou dados para a página de login usando o Model
        // tipo model.atribute "porfavor faça login" e no html uma th::text
        return "login";
    }
}
