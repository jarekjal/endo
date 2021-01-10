package com.jarekjal.endo.controllers;

import com.jarekjal.endo.models.Credentials;
import com.jarekjal.endo.repo.entities.User;
import com.jarekjal.endo.repo.UserRepo;
import com.jarekjal.endo.repo.entities.UserRole;
import com.jarekjal.endo.repo.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserRoleRepo userRoleRepo;

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
    public String createUser(@ModelAttribute("credentials") Credentials credentials){

        System.out.println("Creation of new user: " + credentials.getUserName() + " pass: " + credentials.getPassword());
        System.out.println("Znaleziono w bazie" + userRepo.findById(credentials.getUserName()));

        if (userRepo.findById(credentials.getUserName()).isPresent()){
            return "redirect:/signIn?error";
        } else {
            userRepo.save(new User(credentials.getUserName(), credentials.getPassword(), true));
            userRoleRepo.save(new UserRole(credentials.getUserName(), Role.ROLE_USER.name()));
            return "login";
        }
    }

}
