package com.temr1.Lesson2_3_maven;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    String url = "jdbc:postgresql://ep-purple-brook-a2aamnh8.eu-central-1.aws.neon.tech/Weather%20Info?user=danylo.shpak.2009&password=HAiU5RrDXIf4&sslmode=require";

    public void saveToDataBase(String cityName, String tempInfo){
        try{
            Connection connection = DriverManager.getConnection(url);
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS weatherTable (id SERIAL PRIMARY KEY, cityName TEXT, tempInfo TEXT)");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO weatherTable (cityName, tempInfo) VALUES (?, ?)");
            preparedStatement.setString(1, cityName);
            preparedStatement.setString(2, tempInfo);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Виникли деякі помилки із збереженням данних до бази данних!");
        }
    }

    public ArrayList<String> readFromDataBase(){
        ArrayList<String> array = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(url);
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM weatherTable");

            while (rs.next()){
                array.add(rs.getString("cityName"));
                array.add(rs.getString("tempInfo"));
            }
        }
        catch(Exception e){
            System.out.println("Виникли проблеми із читанням данних з бази данних!");

            return null;
        }

        return array;
    }
}
