package com.temr1.Lesson2_3_maven;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherSchedule {
    private final String weatherUrl = "https://ua.sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-%D0%BB%D1%8C%D0%B2%D1%96%D0%B2/";
    public String getURLOfTodayWeather(){
        return weatherUrl;
    }

    public String getURLOfTomorrowWeather(){
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String formattedDate = formatDate(tomorrow);
        return weatherUrl + formattedDate;
    }

    public String getURLOfAfterTomorrowWeather(){
        LocalDate afterTomorrow = LocalDate.now().plusDays(2);
        String formattedDate = formatDate(afterTomorrow);
        return weatherUrl + formattedDate;
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}
