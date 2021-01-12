package com.jarekjal.endo.services;

import com.jarekjal.endo.repo.entities.Training;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class TrainingParserService {

    public Optional<Training> getTrainingFromPath(Path path){
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            TCXHandler handler = new TCXHandler();
            saxParser.parse(new File(path.toString()), handler);
            if(handler.getTrainingCnt() <= 1) {
                return Optional.of(handler.getTraining());
            } else {
                System.out.println("Error: TrainingCnt > 1");
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
