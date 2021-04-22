package ua.goit.bot.view.menu;

public enum Digits implements MenuBlock {
    DIGITS_2(".00", "/digits_2"),
    DIGITS_3(".000", "/digits_3"),
    DIGITS_4(".0000", "/digits_4");

    private String text;
    private String command;

    Digits(String text, String command) {
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
