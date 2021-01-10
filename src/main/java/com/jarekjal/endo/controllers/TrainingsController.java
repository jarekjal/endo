package com.jarekjal.endo.controllers;

import com.jarekjal.endo.repo.entities.Training;
import com.jarekjal.endo.services.StatisticsService;
import com.jarekjal.endo.services.TrainingService;
import com.jarekjal.endo.services.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class TrainingsController {

    @Autowired
    TrainingService trainingService;

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    IAuthenticationFacade authenticationFacade;

    @GetMapping("/admin/trainings")
    public String getAllTrainings(Model model) {
        model.addAttribute("trainings", trainingService.getAllTrainings());
        return "trainingsList";
    }

    @GetMapping("/trainings")
    public String getTrainings(Model model) {
        model.addAttribute("trainings", trainingService.getTrainingsByUserName(authenticationFacade.getAuthentication().getName()));
        return "trainingsList";
    }

    @GetMapping("/training/{id}")
    public String getTraining(@PathVariable("id") Long id, Model model) {
        Training training = trainingService.getTrainingById(id);
        model.addAttribute("training", training);
        model.addAttribute("statistics", statisticsService.getStatistics(training));
        return "training";
    }

    @GetMapping("/training/{id}/download")
    public ResponseEntity<Resource> downloadTraining(@PathVariable("id") Long id) throws IOException {
        return trainingService.downloadTrainingFile(id);
    }
}
