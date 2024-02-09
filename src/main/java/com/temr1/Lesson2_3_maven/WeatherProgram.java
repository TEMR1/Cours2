package com.temr1.Lesson2_3_maven;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WeatherProgram extends JFrame implements ActionListener {
    private final WeatherSchedule weatherSchedule;
    private final Weather weather;
    private final JTextArea textArea = new JTextArea();
    private String url;

    public WeatherProgram(){
        weatherSchedule = new WeatherSchedule();
        weather = new Weather();

        setSize(490,490);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea.setBounds(10,150,450,290);
        textArea.setLineWrap(true);

        add(textArea);
        createButtons();

        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command){
            case "Today":
                url = weatherSchedule.getURLOfTodayWeather();
                textArea.setText("Температура на сьогодні: ");
                setTextToTextArea(weather.getWeather(url));
                break;
            case "Tomorrow":
                url = weatherSchedule.getURLOfTomorrowWeather();
                textArea.setText("Температура на завтра: ");
                setTextToTextArea(weather.getWeather(url));
                break;
            case "After tomorrow":
                url = weatherSchedule.getURLOfAfterTomorrowWeather();
                textArea.setText("Температура на після завтра: ");
                setTextToTextArea(weather.getWeather(url));
                break;
            case "C":
                textArea.setText("");
                break;
        }
    }

    private void createButtons(){
        createButton(10,10,135,60,"Today");
        createButton(165,10,135,60,"Tomorrow");
        createButton(320,10,135,60,"After tomorrow");
        createButton(400,80,50,50,"C");
    }

    private void createButton(int x, int y, int width, int height, String name){
        JButton button = new JButton(name);
        button.setBounds(x,y, width, height);
        button.addActionListener(this);
        add(button);
    }

    private void setTextToTextArea(ArrayList<WeatherEditor> weatherEditors){
        for(WeatherEditor weatherEditor : weatherEditors){
            String text = textArea.getText();
            textArea.setText(text + "\n Час: " + weatherEditor.getTime() + ". Температура: " + weatherEditor.getAverageTemp());
        }
    }
}
