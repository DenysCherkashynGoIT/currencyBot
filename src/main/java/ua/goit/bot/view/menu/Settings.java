package ua.goit.bot.view.menu;

public enum Settings implements MenuBlock {
    DIGITS("Округление", "/digits_"),
    BANK("Банк", "/bank_"),
    CURRENCY("Валюты", "/currency_"),
    NOTIFY("Время оповещений", "/notify_");

    private String text;
    private String command;

    Settings(String text, String command) {
        this.text = text;
        this.command = command;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getCommand() {
        return this.command;
    }
}
