package ua.goit.bot.system;

import java.util.Arrays;

public enum Bank {
    PRIVAT("privatbank","Приватбанк"),
    NATIONALBANK("nationalbank","НацБанк"),
    MONOBANK("monobank","Монобанк");
    private final String value;
    private final String text;
    Bank(String value, String text){
        this.value=value;
        this.text=text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static Bank getByValue(String value){
        return Arrays.stream(Bank.values())
                .filter(bank -> bank.getValue()
                        .equals(value)).findFirst().orElse(null);
    }
}