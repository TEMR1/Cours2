package com.temr1.Lesson2_3_maven;

public class HistoryElement {
    private final String city;
    private final String temp;

    HistoryElement(String city, String temp){
        this.city = city;
        this.temp = temp;
    }

    public String getCity() {
        return city;
    }

    public String getTemp() {
        return temp;
    }
}
