package com.temr1.Lesson2_3_maven;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherProgram extends JFrame implements ActionListener {
    private final WeatherSchedule weatherSchedule;
    private final Weather weather;
    private final JTextArea textArea = new JTextArea();;

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
                String todayUrl = weatherSchedule.getURLOfTodayWeather();
                weather.getWeather(todayUrl);
                break;
            case "Tomorrow":
                String tomorrowUrl = weatherSchedule.getURLOfTomorrowWeather();
                weather.getWeather(tomorrowUrl);
                break;
            case "After tomorrow":
                String afterTomorrowUrl = weatherSchedule.getURLOfAfterTomorrowWeather();
                weather.getWeather(afterTomorrowUrl);
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

    public void setNewTextToTextArea(String time, int averageTemp){
        String text = textArea.getText();
        textArea.setText(text + "\nЧас: " + time + ". Середня температура: " + averageTemp);
    }
}
