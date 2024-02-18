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
    private final DataBase dataBase = new DataBase();
    private boolean isFindingWeather = false;
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

        if (userMessageText.charAt(0) == '/' && !isFindingWeather) {
            switch(userMessageText){
                case "/weather":
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Вкажіть місто для пошуку погоди!");

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        System.out.println("Помилка в надсиланні данних!");
                    }

                    isFindingWeather = true;
                    break;
                case "/history":
                    ArrayList<String> historyArray = dataBase.readFromDataBase();

                    if (historyArray != null){
                        StringBuilder stringBuilder = new StringBuilder();

                        for (String element : historyArray){
                            stringBuilder.append("Місто: ").append(element);

                            if(historyArray.indexOf(element) < historyArray.size())
                                stringBuilder.append("Погода: ").append(historyArray.get(historyArray.indexOf(element) + 1));
                            else
                                return;
                        }
                        sendMessage.setChatId(chatId);
                        sendMessage.setText(stringBuilder.toString());
                    }
                    else{
                        sendMessage.setChatId(chatId);
                        sendMessage.setText("Вибачте, але на данний момент нічого ще не збережено!");
                    }

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        System.out.println("Помилка в надсиланні данних!");
                    }

                    break;
            }
        }

        else if (isFindingWeather) {
            ArrayList<WeatherArguments> weatherArguments = weather.getWeather(userMessageText);

            if (weatherArguments != null) {
                StringBuilder stringBuilder = new StringBuilder();

                for (WeatherArguments weatherEditor : weatherArguments)
                    stringBuilder.append("Час: ").append(weatherEditor.getTime()).append(". Середня температура: ").append(weatherEditor.getAverageTemp()).append("\n");

                sendMessage.setChatId(chatId);
                sendMessage.setText(stringBuilder.toString());

                try {
                    execute(sendMessage);
                    dataBase.saveToDataBase(userMessageText, stringBuilder.toString());
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
                    System.out.println("Виникли проблеми!");
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
}
