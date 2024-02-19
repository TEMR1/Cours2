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
    private final Module module;
    private final List<String> listOfDayTime = List.of("ніч", "ранок", "день", "вечір");
    private String temp1 = "Немає відомості";
    private String temp2 = "Немає відомості";
    private String dayTime = "Немає відомості";

    public Weather(Module module){
        this.module = module;
    }

    public ArrayList<WeatherArguments> getWeather(String city){
        ArrayList<WeatherArguments> weatherArguments = new ArrayList<>();

        String baseUrl = "https://ua.sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-";
        String url = baseUrl + city;
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("tr");

            for (int index = 0; index < Objects.requireNonNull(links.first()).childrenSize(); index++) {
                for(Element link : links){
                    if (listOfDayTime.contains(link.child(index).text()))
                        dayTime = link.child(index).text();

                    else if (link.className().equals("temperature")){
                        temp1 = link.child(index * 2).text();
                        temp2 = link.child(index * 2 + 1).text();
                    }
                }

                WeatherArguments weatherArgument = new WeatherArguments(dayTime,temp1,temp2);
                weatherArguments.add(weatherArgument);
            }

        } catch (IOException e) {
            System.out.println("You have some problems!");
            return null;
        }

        return weatherArguments;
    }
}
