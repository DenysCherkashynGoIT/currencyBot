package ua.goit.bot.view.menu;

public enum OptionalButtons implements MenuBlock {
    CLOSE("Закрыть", "/close"),
    CLOSE_SETS("Закрыть", "/close_settings");

    private String text;
    private String command;

    OptionalButtons(String text, String command) {
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
