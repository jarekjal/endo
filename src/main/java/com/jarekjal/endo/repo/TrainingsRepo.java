package com.jarekjal.endo.repo;

import com.jarekjal.endo.services.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingsRepo {

    @Autowired
    IAuthenticationFacade authenticationFacade;

    private List<Training> trainings;

    public TrainingsRepo() {
        this.trainings = new ArrayList<>();
    }

    public void addTraining(Training training) {
        // TODO: is it needed to block adding the same training multiple times?
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
        String currentUser = authenticationFacade.getAuthentication().getName();
        return trainings.stream().filter(training -> currentUser.equals(training.getUserName())).collect(Collectors.toList());
    }

    public int getTrainingsCount() {
        String currentUser = authenticationFacade.getAuthentication().getName();
        return (int) trainings.stream().filter(training -> currentUser.equals(training.getUserName())).count();
    }

    public void deleteTrainings() {
        //TODO: delete only currentUser's trainings
        trainings = new ArrayList<>();
    }

    public Training getTraining(int id) {
        return trainings.get(id);
    }
}
