package com.jarekjal.endo.services;

import com.jarekjal.endo.exceptions.FileStorageException;
import com.jarekjal.endo.repo.TrainingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @Autowired
    FileContentService fileContentService;

    @Autowired
    TrainingsRepo trainingsRepo;

    public void uploadFile(MultipartFile file) {
        try {
            Path copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            trainingsRepo.addTraining(fileContentService.getFileContent(copyLocation));
            System.out.println("Trainings in repo: " + trainingsRepo.getTrainingsCount());

        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file "
                    + file.getOriginalFilename() + ". Please try again! Cause: " + e.getMessage());
        }
    }

}
