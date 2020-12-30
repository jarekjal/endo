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

    public void addTraining(Training training) {
        if (isTrainingAlreadyAdded(training)) throw
                new IllegalArgumentException("Training with activityId: " + training.getActivityId() + " already added!");
        training.setId(trainings.size());
        trainings.add(training);
    }

    private boolean isTrainingAlreadyAdded(Training training) {
        if (trainings.stream().anyMatch(t -> t.getActivityId().equals(training.getActivityId()))) {
            return true;
        } else {
            return false;
        }
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

    public Training getTraining(int id) {
        return trainings.get(id);
    }
}
