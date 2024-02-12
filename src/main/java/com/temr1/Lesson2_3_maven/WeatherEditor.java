package com.temr1.Lesson2_3_maven;

public class WeatherEditor {
    private final String time;
    private final int averageTemp;
    public WeatherEditor(String time, String temp1, String temp2){
        char firstChar = Character.toUpperCase(time.charAt(0));
        this.time = firstChar + time.substring(1);

        String numberOfTemp1 = temp1.substring(1,temp1.length() - 1);
        String numberOfTemp2 = temp2.substring(1,temp2.length() - 1);

        averageTemp = (Integer.parseInt(numberOfTemp1) + Integer.parseInt(numberOfTemp2)) / 2;
    }

    public String getTime() {
        return time;
    }

    public int getAverageTemp() {
        return averageTemp;
    }
}
