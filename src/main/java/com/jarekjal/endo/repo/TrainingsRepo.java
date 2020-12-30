package com.jarekjal.endo.repo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingsRepo {

    private List<Training> trainings;

    public TrainingsRepo() {
        this.trainings = new ArrayList<>();
    }

    public void addTraining(Training training){
        trainings.add(training);
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public int getTrainingsCount() {
        return trainings.size();
    }

    public void deleteTrainings() {
        trainings = new ArrayList<>();
    }
}
