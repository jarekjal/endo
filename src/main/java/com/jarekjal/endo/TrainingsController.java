package com.jarekjal.endo;

import com.jarekjal.endo.repo.TrainingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrainingsController {

    @Autowired
    TrainingsRepo trainingsRepo;

    @GetMapping("/trainings")
    public String getTrainings(Model model){

        model.addAttribute("trainings", trainingsRepo.getTrainings());

        return "trainingsList";


    }

}
