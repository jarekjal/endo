package com.jarekjal.endo.repo;

import java.nio.file.Path;

public class Training {
    private long id;
    private String activityId;
    private String activity;
    private String startTime;
    private double totalTime;
    private double distance;
    private double calories;

    private long trackPoints;
    private Path filePath;
    private String userName;

    public Training(String userName, String activityId, String activity, String startTime, double totalTime, double distance, double calories, long trackPoints, Path filePath) {
        this.activityId = activityId;
        this.activity = activity;
        this.startTime = startTime;
        this.totalTime = totalTime;
        this.distance = distance;
        this.calories = calories;
        this.trackPoints = trackPoints;
        this.filePath = filePath;
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public long getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(long trackPoints) {
        this.trackPoints = trackPoints;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
