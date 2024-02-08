package com.temr1.Lesson2_3_maven;

import java.util.ArrayList;

public class WeatherEditor {
    private String time;
    private int averageTemp;

    public WeatherEditor(String time, ArrayList<String> temps){
        if (temps.isEmpty()){
            System.out.println("Ми не змогли відшукати температуру!");
        }

        else if (temps.size() == 1){
            firstLetterToUpCase(time);

            String temp = temps.getFirst().substring(0, temps.getFirst().length() - 1);
            averageTemp = Integer.parseInt(temp);
        }
        else{
            firstLetterToUpCase(time);
            ArrayList<Integer> listOfTemps = new ArrayList<>();

            for(String tempsElem : temps){
                String temp = tempsElem.substring(0,tempsElem.length() - 1);
                listOfTemps.add(Integer.parseInt(temp));
            }

            int sum = 0;
            for(Integer temp : listOfTemps){
                sum+=temp;
            }

            averageTemp = sum / temps.size();
        }
    }

    private void firstLetterToUpCase(String time){
        char firstChar = Character.toUpperCase(time.charAt(0));
        this.time = firstChar + time.substring(1);
    }

    public String getTime() {
        return time;
    }

    public int getAverageTemp() {
        return averageTemp;
    }
}
