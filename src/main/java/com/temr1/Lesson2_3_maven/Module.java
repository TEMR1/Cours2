package com.temr1.Lesson2_3_maven;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Module extends TelegramLongPollingBot {
    SendMessage sendMessage = new SendMessage();
    private final Weather weather = new Weather(this);
    private boolean isFindingWeather = false;
    private boolean cityIsFind = false;

    public Module(){
        ArrayList<BotCommand> commandsList = new ArrayList<>();
        commandsList.add(new BotCommand("/weather", "Показує погоду в вказаному місті!"));
        commandsList.add(new BotCommand("/history", "Показує історію запитів!"));

        try{
            this.execute(new SetMyCommands(commandsList, new BotCommandScopeDefault(), null));
        }catch (TelegramApiException e){
            System.out.println("Error with executing commands menu: " + e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        String userMessageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        if (userMessageText.equals("/weather")) {
            sendMessage.setChatId(chatId);
            sendMessage.setText("Вкажіть місто для пошуку погоди!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                System.out.println("Помилка в надсиланні данних!");
            }

            isFindingWeather = true;
        }

        else if (isFindingWeather) {
            ArrayList<WeatherEditor> weatherEditors = weather.getWeather(userMessageText);

            if (cityIsFind && weatherEditors != null){
                StringBuilder stringBuilder = new StringBuilder();

                for(WeatherEditor weatherEditor : weatherEditors)
                    stringBuilder.append("Час: ").append(weatherEditor.getTime()).append(". Середня температура: ").append(weatherEditor.getAverageTemp()).append("\n");

                sendMessage.setChatId(chatId);
                sendMessage.setText(stringBuilder.toString());



                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    System.out.println("Помилка в надсиланні данних!");
                }
            }
            else{
                sendMessage.setChatId(chatId);
                sendMessage.setText("Виникли деякі проблеми!");

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    System.out.println("Виниколи проблеми!");
                }

            }
            isFindingWeather = false;
        }
    }

    @Override
    public String getBotUsername() {
        return "https://t.me/MyGlobalWeatherByCityBot";
    }

    @Override
    public String getBotToken() {
        return "6102260601:AAGSjf9UIsQCIlJ5b0KwTMO05VzsBKpjZWI";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

//------------------------------------------GETTERS AND SETTERS--------------------------------------------
    public void setCityIsFind(boolean cityIsFind) {
        this.cityIsFind = cityIsFind;
    }
}
