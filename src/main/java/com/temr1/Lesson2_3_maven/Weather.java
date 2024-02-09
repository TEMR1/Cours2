package com.temr1.Lesson2_3_maven;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Weather {
    List<String> listOfDayTime = List.of("ніч", "ранок", "день", "вечір");
    WeatherEditor weatherEditor;
    ArrayList<WeatherEditor> weatherEditors = new ArrayList<>();
    ArrayList<String> temps = new ArrayList<>();

    public ArrayList<WeatherEditor> getWeather(String url){
        weatherEditors.clear();
        try {

            Document document = Jsoup.connect(url).get();
            Elements links = document.select("tr");

            int repeats = 0;
            boolean isTwoTemps = false;

            for(Element elem : links){
                if (listOfDayTime.contains(elem.child(0).text()))
                    repeats = Objects.requireNonNull(elem).select("td").size();
                else if (elem.className().equals("temperature")){
                    if (elem.select("td").size() == 8){
                        isTwoTemps = true;
                    }
                }
            }

            for (int index = 0; index < repeats; index++) {
                String time = "Немає відомості";

                for(Element link : links){
                    if (listOfDayTime.contains(link.child(index).text()))
                        time = link.child(index).text();
                    else if (link.className().equals("temperature")) {
                        if (isTwoTemps){
                            temps.add(link.child(index * 2).text());
                            temps.add(link.child(index * 2 + 1).text());
                        }
                        else{
                            temps.add(link.child(index).text());
                        }
                    }
                }

                weatherEditor = new WeatherEditor(time,temps);
                weatherEditors.add(weatherEditor);
                temps.clear();
            }

        } catch (IOException e) {
            System.out.println("Виникли якісь проблеми!");
        }

        return weatherEditors;
    }
}
