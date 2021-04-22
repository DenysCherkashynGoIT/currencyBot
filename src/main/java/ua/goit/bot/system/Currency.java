package ua.goit.bot.system;

import java.util.Arrays;
import java.util.Optional;

public enum Currency {
    USD("USD", 840),
    EUR("EUR", 978),
    RUB("RUR", 643),
    UAH("UAH", 980),
    UNKNOWN("", -1);

    private final String name;
    private final int iso;

    Currency(String value, int iso) {
        this.name = value;
        this.iso = iso;
    }

    public static Currency byName(String name) {
        Optional<Currency> currency = Arrays.stream(Currency.values()).
                filter(curr -> curr.getName().equals(name)).findAny();
        return currency.orElse(UNKNOWN);
    }

    public static Currency byIso(int iso) {
        Optional<Currency> currency = Arrays.stream(Currency.values()).
                filter(curr -> curr.getIso() == iso).findAny();
        return currency.orElse(UNKNOWN);
    }

    public String getName() {
        return this.name;
    }

    public int getIso() {
        return iso;
    }
}
