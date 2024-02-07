package com.temr1.Lesson2_3_maven;

public class Weather {
    private final String time;
    private final double averageTemp;
    public Weather(String time, String temp1, String temp2){
        this.time = time;

        String numberOfTemp1 = temp1.substring(0,temp1.length() - 1);
        String numberOfTemp2 = temp2.substring(0,temp2.length() - 1);

        averageTemp =  (double) (Integer.parseInt(numberOfTemp1) + Integer.parseInt(numberOfTemp2)) / 2;
    }

    public String getTime() {
        return time;
    }

    public double getAverageTemp() {
        return averageTemp;
    }
}
