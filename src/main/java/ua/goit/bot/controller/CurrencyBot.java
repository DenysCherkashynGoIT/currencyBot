package ua.goit.bot.controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.goit.bot.controller.command.Commandor;
import ua.goit.bot.notification.NotificationTask;


public class CurrencyBot extends TelegramLongPollingBot {
    private final Commandor commandor;

    public CurrencyBot() {
        this.commandor = Commandor.getInstance(this);
        new NotificationTask(commandor).send();
    }

    @Override
    public String getBotUsername() {
        return BotKey.USER_NAME.get();
    }

    @Override
    public String getBotToken() {
        return BotKey.TOKEN.get();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String input = null;
        if (update.hasMessage() && update.getMessage().hasText()) {
            input = update.getMessage().getText();
        } else if (update.hasCallbackQuery()) {
            input = update.getCallbackQuery().getData();
        }
        commandor.call(input, update);
    }
}
