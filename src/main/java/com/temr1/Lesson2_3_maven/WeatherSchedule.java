package com.temr1.Lesson2_3_maven;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherSchedule {
    public String getURLOfTodayWeather(){
        return "https://ua.sinoptik.ua/";
    }

    public String getURLOfTomorrowWeather(){
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String formattedDate = formatDate(tomorrow);
        return "https://ua.sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-%D0%B2%D1%87%D0%BE%D1%80%D0%B0%D0%B9%D1%88%D0%B5/" + formattedDate;
    }

    public String getURLOfAfterTomorrowWeather(){
        LocalDate afterTomorrow = LocalDate.now().plusDays(2);
        String formattedDate = formatDate(afterTomorrow);
        return "https://ua.sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-%D0%BA%D0%B8%D1%97%D0%B2/" + formattedDate;
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}
