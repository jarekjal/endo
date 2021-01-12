package com.jarekjal.endo.helpers;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private final List<String> stringValues;
    private double avg;
    private double min;
    private double max;
    private boolean valid = false;

    public Calculator(List<String> stringValues) {
        this.stringValues = stringValues;
        calculate();
    }

    private void calculate() {
        List<Double> values = new ArrayList<>();
        for (String sv : stringValues) {
            try {
                double v = Double.parseDouble(sv);
                values.add(v);
            } catch (Exception ignored) {
            }
        }
        if (values.size() > 0) {
            double sum = values.stream().reduce(0.0, Double::sum);
            long cnt = values.size();
            max = values.stream().max(Double::compareTo).orElse(0.0);
            min = values.stream().min(Double::compareTo).orElse(0.0);
            avg = sum / cnt;
            valid = true;
        } else {
            System.out.println("Can't parse values");
            valid = false;
        }
    }

    public double getAvg() {
        return avg;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public boolean isValid() {
        return valid;
    }
}
