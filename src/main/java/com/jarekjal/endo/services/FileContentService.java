package com.jarekjal.endo.services;

import com.jarekjal.endo.exceptions.FileStorageException;
import com.jarekjal.endo.repo.entities.Training;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Path;

@Service
public class FileContentService {

    public Training getFileContent(Path location) {
        File xmlFile = new File(String.valueOf(location));
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        Document document;
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not parse file "
                    + xmlFile + ". Please try again!");
        }

        System.out.println("----- Data read from " + xmlFile + " -----");
        int activities = document.getElementsByTagName("Activities").getLength();
        System.out.println("Activities: " + activities);
        String activity = document.getElementsByTagName("Activity").item(0).getAttributes().getNamedItem("Sport").getTextContent();
        System.out.println("Activity: " + activity);
        String activityId = document.getElementsByTagName("Id").item(0).getTextContent();
        System.out.println("Activity id: " + activityId);
        String startTime = document.getElementsByTagName("Lap").item(0).getAttributes().getNamedItem("StartTime").getTextContent();
        System.out.println("Lap Start Time: " + startTime);
        String totalTimeSeconds = document.getElementsByTagName("TotalTimeSeconds").item(0).getTextContent();
        System.out.println("Lap Total Time: " + totalTimeSeconds);
        String distanceMeters = document.getElementsByTagName("DistanceMeters").item(0).getTextContent();
        System.out.println("Distance: " + distanceMeters);
        String calories = document.getElementsByTagName("Calories").item(0).getTextContent();
        System.out.println("calories: " + calories);
        int tracks = document.getElementsByTagName("Track").getLength();
        System.out.println("Tracks: " + tracks);
        long trackpoints = document.getElementsByTagName("Trackpoint").getLength();
        System.out.println("Trackpoints: " + trackpoints);
        System.out.println("-----");

        return new Training(
                "",
                activityId,
                activity,
                startTime,
                Double.parseDouble(totalTimeSeconds),
                Double.parseDouble(distanceMeters),
                Double.parseDouble(calories),
                trackpoints,
                location);

    }

}
