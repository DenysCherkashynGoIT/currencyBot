package ua.goit.bot.view.menu;

public enum NotificationTime implements MenuBlock {
    TIME_9("9", "/notify_9"),
    TIME_10("10", "/notify_10"),
    TIME_11("11", "/notify_11"),
    TIME_12("12", "/notify_12"),
    TIME_13("13", "/notify_13"),
    TIME_14("14", "/notify_14"),
    TIME_15("15", "/notify_15"),
    TIME_16("16", "/notify_16"),
    TIME_17("17", "/notify_17"),
    TIME_OFF("Отключить уведомления", "/notify_off");

    private String text;
    private String command;

    NotificationTime(String text, String command) {
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
