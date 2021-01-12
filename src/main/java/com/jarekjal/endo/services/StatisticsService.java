package com.jarekjal.endo.services;

import com.jarekjal.endo.helpers.Calculator;
import com.jarekjal.endo.models.Statistics;
import com.jarekjal.endo.models.TrackPoint;
import com.jarekjal.endo.repo.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    TrainingParserService trainingParserService;

    private Training parsedTraining;

    public Statistics getStatistics(Training training) {
        try {
            parsedTraining = trainingParserService.getTrainingFromPath(training.getFilePath()).orElseThrow(Exception::new);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> hrs = parsedTraining.getTrackpoints().stream().map(TrackPoint::getHr).collect(Collectors.toList());
        List<String> alt = parsedTraining.getTrackpoints().stream().map(TrackPoint::getAlt).collect(Collectors.toList());
        List<String> cad = parsedTraining.getTrackpoints().stream().map(TrackPoint::getCad).collect(Collectors.toList());

        Calculator calculator = new Calculator(hrs);
        Statistics statistics = new Statistics();
        if (calculator.isValid()) {
            statistics.setAvgHR(Integer.toString((int)calculator.getAvg()));
            statistics.setMaxHR(Integer.toString((int)calculator.getMax()));
            statistics.setMinHR(Integer.toString((int)calculator.getMin()));
        }
        calculator = new Calculator(alt);
        if (calculator.isValid()) {
            statistics.setAvgAlt(Integer.toString((int)calculator.getAvg()));
            statistics.setMaxAlt(Integer.toString((int)calculator.getMax()));
            statistics.setMinAlt(Integer.toString((int)calculator.getMin()));
        }
        calculator = new Calculator(cad);
        if (calculator.isValid()) {
            statistics.setAvgCad(Integer.toString((int)calculator.getAvg()));
            statistics.setMaxCad(Integer.toString((int)calculator.getMax()));
            statistics.setMinCad(Integer.toString((int)calculator.getMin()));
        }
        return statistics;
    }
}
