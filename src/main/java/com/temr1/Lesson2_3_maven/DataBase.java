package com.temr1.Lesson2_3_maven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBase {
    String url = "jdbc:postgresql://ep-silent-waterfall-a23bxftj.eu-central-1.aws.neon.tech/Weather%20Info?user=danylo.shpak.2009&password=VOHM8rXk1jKE&sslmode=require";

    public void dataBase(String userText, String time, int temp){
        try{
            Connection connection = DriverManager.getConnection(url);

            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS weatherTable");
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS weatherTable (id SERIAL PRIMARY KEY, userText TEXT, time TEXT, temp INTEGER)");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO weatherTable (userText, time, temp) VALUES (?, ?, ?)");
            preparedStatement.setString(1, userText);
            preparedStatement.setString(2, time);
            preparedStatement.setInt(3, temp);
            preparedStatement.executeUpdate();

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM weatherTable");

            while (rs.next()){
                System.out.println(rs.getString("time"));
            }

        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Виникли деякі помилки з базою данних!");
        }
    }
}
