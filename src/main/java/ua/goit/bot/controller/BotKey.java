package ua.goit.bot.controller;

public enum BotKey {
    USER_NAME("some_bot_name"),
    TOKEN("some_bot_token");

    private final String value;

    BotKey(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
