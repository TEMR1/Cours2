package com.temr1.Lesson2_3_maven;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main{
    public static void main(String[] args) throws TelegramApiException {
        DataBase dataBase = new DataBase();
        dataBase.dataBase("Danylo","day",5);

//        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//        try {
//            botsApi.registerBot(new Module());
//            System.out.println("Bot started!");
//        } catch (TelegramApiException e) {
//            System.out.println("Error: " + e);
//        }
    }
}
