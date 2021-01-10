package com.jarekjal.endo.services;

import com.jarekjal.endo.repo.TrainingRepo;
import com.jarekjal.endo.repo.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TrainingService {

    @Autowired
    TrainingRepo trainingRepo;


    public Iterable<Training> getAllTrainings() {
        return trainingRepo.findAll();
    }

    public Iterable<Training> getTrainingsByUserName(String user) {
        return trainingRepo.findByUserName(user);
    }

    public Training getTrainingById(Long id) {
        return trainingRepo.findById(id).orElseThrow(() -> new RuntimeException("Training with id: "+ id +" not found"));
    }

    public ResponseEntity<Resource> downloadTrainingFile(Long id) throws IOException {
        Training training = getTrainingById(id);
        Path path = training.getFilePath();
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
