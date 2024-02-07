package com.temr1.Lesson2_3_maven;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String url = "https://ua.sinoptik.ua/";
        List<String> listOfDayTime = List.of("ніч", "ранок", "день", "вечір");

        int tempIndex = 0;
        String temp1 = "Немає відомості";
        String temp2 = "Немає відомості";
        String dayTime = "Немає відомості";

        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("tr");

            System.out.println("Погода на сьогодні: ");

            for (int index = 0; index < Objects.requireNonNull(links.first()).childrenSize(); index++) {

                for(Element link : links){
                    if (listOfDayTime.contains(link.child(index).text()))
                        dayTime = link.child(index).text();

                    else if (link.className().equals("temperature")){
                        temp1 = link.child(tempIndex).text();
                        temp2 = link.child(tempIndex + 1).text();
                    }
                }
                tempIndex += 2;

                Weather weather = new Weather(dayTime,temp1,temp2);
                System.out.println("Час: " + weather.getTime() + ". Середня температура: " + weather.getAverageTemp());
            }

        } catch (IOException e) {
            System.out.println("You have some problems!");
        }
    }
}