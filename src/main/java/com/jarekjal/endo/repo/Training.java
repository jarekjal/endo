package com.jarekjal.endo.repo;

public class Training {
    private String activityId;
    private String activity;
    private String startTime;
    private double totalTime;
    private double distance;
    private double calories;
    private long trackPoints;

    public Training(String activityId, String activity, String startTime, double totalTime, double distance, double calories, long trackPoints) {
        this.activityId = activityId;
        this.activity = activity;
        this.startTime = startTime;
        this.totalTime = totalTime;
        this.distance = distance;
        this.calories = calories;
        this.trackPoints = trackPoints;
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
}
