package com.jarekjal.endo.controllers;

import com.jarekjal.endo.repo.TrainingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class TrainingsController {

    @Autowired
    TrainingsRepo trainingsRepo;

    @GetMapping("/trainings")
    public String getTrainings(Model model) {
        model.addAttribute("trainings", trainingsRepo.getTrainings());
        return "trainingsList";
    }

    @GetMapping("/training/{id}")
    public String getTraining(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("training", trainingsRepo.getTraining(id));
        return "training";
    }

    @GetMapping("/training/{id}/download")
    public ResponseEntity<Resource> downloadTraining(@PathVariable("id") Integer id) throws IOException {
        Path path = trainingsRepo.getTraining(id).getFilePath();
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        File file = new File(String.valueOf(path));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
