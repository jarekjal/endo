package com.jarekjal.endo.controllers;

import com.jarekjal.endo.models.Credentials;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {

    @GetMapping("/signIn")
    public String signInPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {

        String message = null;
        if (error != null) {
            message = "Użytkownik już istnieje!";
        }
        model.addAttribute("credentials", new Credentials());
        model.addAttribute("message", message);
        return "signIn";
    }

    @PostMapping("/signIn")
    public String createUser(@ModelAttribute("credentials") Credentials credentials, Model model){

        System.out.println("Creation of new user: " + credentials.getUserName() + " pass: " + credentials.getPassword());
        boolean userCreated = false;

        if (userCreated) {
            return "login";
        } else {
            return "redirect:/signIn?error";
        }
    }

}
