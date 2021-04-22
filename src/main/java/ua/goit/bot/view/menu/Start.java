package ua.goit.bot.view.menu;

public enum Start implements MenuBlock {
    INFO("Курс", "/info"),
    SETTINGS("Настройки", "/settings_");

    private String text;
    private String command;

    Start(String text, String command) {
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
