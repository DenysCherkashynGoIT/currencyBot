package ua.goit.bot.view.menu;

public enum CurrencyOption implements MenuBlock {
    USD("доллар США (USD)", "/currency_usd"),
    EUR("евро (EUR)", "/currency_eur"),
    RUB("рос.рубль (RUB)", "/currency_rur");

    private String text;
    private String command;

    CurrencyOption(String text, String command) {
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
