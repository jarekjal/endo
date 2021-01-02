package com.jarekjal.endo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {

        String message = null;
        if (error != null) {
            message = "Niepoprawne hasło lub nazwa użytkownika";
        }
        if (logout != null) {
            message = "Zostałeś wylogowany poprawnie";
        }
        model.addAttribute("message", message);
        return "login";
    }


}
