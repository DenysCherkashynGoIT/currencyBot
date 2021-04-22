package ua.goit.bot.user.service;

public interface Storage {
    void putUserSettings(UserSettings userSettings);

    UserSettings getUserSettings(Long chatId);
}
