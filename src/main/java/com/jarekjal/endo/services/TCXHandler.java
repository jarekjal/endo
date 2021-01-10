package com.jarekjal.endo.services;

import com.jarekjal.endo.models.TrackPoint;
import com.jarekjal.endo.repo.entities.Training;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class TCXHandler extends DefaultHandler {

    private Training training = null;
    private List<TrackPoint> track = null;
    private TrackPoint trackpoint = null;
    private StringBuilder data = null;

    private boolean bTrackPoint = false;
    private boolean bTime = false;
    private boolean bLatitudeDegrees = false;
    private boolean bLongitudeDegrees = false;
    private boolean bAltitudeMeters = false;
    private boolean bDistanceMeters = false;
    private boolean bHeartRateBpm = false;
    private boolean bValue = false;
    private boolean bCadence = false;

    private int trainingCnt = 0; // wieksze od 1 to blad!!!

    public int getTrainingCnt() {
        return trainingCnt;
    }

    public Training getTraining() {
        return training;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Activity")) {
            trainingCnt++;
            training = new Training();
            String activity = attributes.getValue("Sport");
            training.setActivity(activity);
        } else if (qName.equalsIgnoreCase("Track")) {
            track = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("TrackPoint")) {
            trackpoint = new TrackPoint();
            bTrackPoint = true;
        } else if (qName.equalsIgnoreCase("Time")) {
            if (bTrackPoint) {
                bTime = true;
            }
        } else if (qName.equalsIgnoreCase("LatitudeDegrees")) {
            if (bTrackPoint) {
                bLatitudeDegrees = true;
            }
        } else if (qName.equalsIgnoreCase("LongitudeDegrees")) {
            if (bTrackPoint) {
                bLongitudeDegrees = true;
            }
        } else if (qName.equalsIgnoreCase("AltitudeMeters")) {
            if (bTrackPoint) {
                bAltitudeMeters = true;
            }
        } else if (qName.equalsIgnoreCase("DistanceMeters")) {
            if (bTrackPoint) {
                bDistanceMeters = true;
            }
        } else if (qName.equalsIgnoreCase("HeartRateBpm")){
            if (bTrackPoint) {
                bHeartRateBpm = true;
            }
        } else if (qName.equalsIgnoreCase("Value")){
            if (bTrackPoint && bHeartRateBpm) {
                bValue = true;
            }
        } else if (qName.equalsIgnoreCase("Cadence")){
            if (bTrackPoint) {
                bCadence = true;
            }
        }

        // create the data container
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Activity")) {
            training.setTrackpoints(track);
        } else if (qName.equalsIgnoreCase("TrackPoint")) {
            track.add(trackpoint);
            trackpoint = null;
            bTrackPoint = false;
        } else if (bTime) {
            if (bTrackPoint) {
                trackpoint.setTime(data.toString());
            }
            bTime = false;
        } else if (bLatitudeDegrees) {
            if (bTrackPoint) {
                trackpoint.setLat(data.toString());
            }
            bLatitudeDegrees = false;
        } else if (bLongitudeDegrees) {
            if (bTrackPoint) {
                trackpoint.setLon(data.toString());
            }
            bLongitudeDegrees = false;
        } else if (bAltitudeMeters) {
            if (bTrackPoint) {
                trackpoint.setAlt(data.toString());
            }
            bAltitudeMeters = false;
        } else if (bDistanceMeters) {
            if (bTrackPoint) {
                trackpoint.setDist(data.toString());
            }
            bDistanceMeters = false;
        } else if (qName.equalsIgnoreCase("HeartRateBpm")) {
            bHeartRateBpm = false;
        } else if (bValue) {
            if (bHeartRateBpm) {
                trackpoint.setHr(data.toString());
            }
            bValue = false;
        } else if (bCadence) {
            if (bTrackPoint) {
                trackpoint.setCad(data.toString());
            }
            bCadence = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
