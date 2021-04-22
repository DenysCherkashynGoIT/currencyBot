package ua.goit.bot.controller;

import java.util.List;

public interface Controller {
    void sendText(Long chatID);

    void sendText(List<Long> chatIDs);
}
