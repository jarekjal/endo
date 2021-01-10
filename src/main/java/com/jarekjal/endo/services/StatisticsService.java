package com.jarekjal.endo.services;

import com.jarekjal.endo.models.Statistics;
import com.jarekjal.endo.models.TrackPoint;
import com.jarekjal.endo.repo.entities.Training;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

        private Training parsedTraining;

        public Statistics getStatistics(Training training) {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            try {
                SAXParser saxParser = saxParserFactory.newSAXParser();
                TCXHandler handler = new TCXHandler();
                saxParser.parse(new File(training.getFilePath().toString()), handler);
                if(handler.getTrainingCnt() <= 1) {
//                    System.out.println("Dane z xmla: " + handler.getTraining());
//                    System.out.println("TrackPoints #: " + handler.getTraining().getTrackpoints().size());
                    parsedTraining = handler.getTraining();
                } else {
                    System.out.println("Error: TrainingCnt > 1");
                }
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }

            List<String> hrs = parsedTraining.getTrackpoints().stream().map(TrackPoint::getHr).collect(Collectors.toList());
            List<Double> hrsValues;
            try {
                 hrsValues = hrs.stream().map(Double::parseDouble).collect(Collectors.toList());
            } catch (Exception e) {
                System.out.println("Can't parse HR values");
//                e.printStackTrace();
                return new Statistics();
            }
            double hrSum = hrsValues.stream().reduce(0.0, Double::sum);
            long hrCnt = hrsValues.stream().count();
            double maxHR = hrsValues.stream().max(Double::compareTo).orElse(0.0);
            double minHR = hrsValues.stream().min(Double::compareTo).orElse(0.0);
            double avgHR = hrSum / hrCnt;
            Statistics statistics = new Statistics();
            statistics.setAvgHR(Double.toString(avgHR));
            statistics.setMaxHR(Double.toString(maxHR));
            statistics.setMinHR(Double.toString(minHR));
            return statistics;

        }
}
