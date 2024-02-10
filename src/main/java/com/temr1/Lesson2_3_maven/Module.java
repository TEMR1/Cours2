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
    String token = "6102260601:AAGSjf9UIsQCIlJ5b0KwTMO05VzsBKpjZWI";
    String userName = "https://t.me/MyGlobalWeatherByCityBot";

    public Module(){
        ArrayList<BotCommand> commandsList = new ArrayList<>();
        commandsList.add(new BotCommand("/weather", "Показывает погоду в указанном городе"));

        try{
            this.execute(new SetMyCommands(commandsList, new BotCommandScopeDefault(), null));
        }catch (TelegramApiException e){
            System.out.println("Error with executing commands menu: "+e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        String userMessageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Вы написали: " + userMessageText);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        // Уникальное имя вашего бота
        return userName;
    }

    @Override
    public String getBotToken() {
        // Токен вашего бота, полученный от BotFather в Telegram
        return token;
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
