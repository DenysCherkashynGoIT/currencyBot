package ua.goit.bot.view.menu;

public enum BankOption implements MenuBlock {
    PRIVAT("Приватбанк", "/bank_privatbank"),
    NATIONALBANK("НБУ", "/bank_nationalbank"),
    MONOBANK("Монобанк", "/bank_monobank");

    private String text;
    private String command;

    BankOption(String text, String command) {
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
