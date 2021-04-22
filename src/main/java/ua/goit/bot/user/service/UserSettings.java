package ua.goit.bot.user.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import lombok.extern.slf4j.Slf4j;
import ua.goit.bot.system.Bank;
import ua.goit.bot.system.Currency;

import java.time.LocalTime;
import java.util.HashSet;

@Getter @Setter @Slf4j
public class UserSettings {
    private @Setter(AccessLevel.NONE) Long chatId;
    private int digitsAfterComma;
    private Bank bank;
    private HashSet<Currency> exchange = new HashSet<>();
    private LocalTime time;

    public UserSettings(Long chatID) {
        this.chatId = chatID;
        digitsAfterComma = 2;
        bank = Bank.PRIVAT;
        exchange.add(Currency.USD);
        time = LocalTime.of(9, 0);
        log.info("The UserSettings for " + chatID.toString() + " was created");
    }
}
