package ua.goit.bot.controller.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ua.goit.bot.system.Bank;
import ua.goit.bot.system.Currency;
import ua.goit.bot.user.service.UserSettings;
import ua.goit.bot.user.service.UserSettingsStorage;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;

public final class UserProcessor {
    private static UserProcessor userProcessor;
    private static Service service;
    private final UserSettingsStorage userStorage = UserSettingsStorage.getStorage();

    private UserProcessor(Service serviceInstance) {
        service = serviceInstance;
    }

    public static UserProcessor getInstance(Service serviceInstance) {
        if (Objects.isNull(userProcessor)) {
            userProcessor = new UserProcessor(serviceInstance);
        }
        return userProcessor;
    }

    public <T> void createUser(T chatInfo) {
        Long chatId = service.getChatId(chatInfo);
        UserSettings user = new UserSettings(chatId);
        userStorage.putUserSettings(user);
    }

    public <T> UserSettings getUser(T chatInfo) {
        Long chatId = service.getChatId(chatInfo);
        if (Objects.nonNull(chatId)) {
            UserSettings user = userStorage.getUserSettings(chatId);
            if (Objects.isNull(user)) {
                createUser(chatId);
                user = getUser(chatId);
            }
            return user;
        }
        return null;
    }

    public String getButtonValue(Update chatInfo, String commandKey) {
        String input = service.getInputData(chatInfo);
        return detachValue(commandKey, input);
    }

    public String detachValue(String commonItem, String input) {
        if (input.contains(commonItem)) {
            input = input.replace(commonItem, "");
        }
        return input;
    }

    public void pushDigits(UserSettings user, String value) {
        if (!value.isBlank()) {
            Integer userSet = Integer.valueOf(value);
            user.setDigitsAfterComma(userSet);
        }
    }

    public void pushBank(UserSettings user, String value) {
        Bank bank = Bank.getByValue(value);
        if (Objects.nonNull(bank)) user.setBank(bank);
    }

    public void pushCurrency(UserSettings user, String value) {
        value = value.toUpperCase();
        Currency currency = Currency.byName(value);
        if (!currency.name().equals("UNKNOWN")) {
            Long chatId = user.getChatId();
            userStorage.updateCurrency(chatId, currency);
        }
    }

    public void pushNotify(UserSettings user, String value) {
        LocalTime time;
        if (value.equals("?????????????????? ??????????????????????")) {
            time = null;
        } else {
            Integer userSet = Integer.valueOf(value);
            time = LocalTime.of(userSet, 0);
        }
        user.setTime(time);
    }

    public String pullDigits(UserSettings user) {
        int userSet = user.getDigitsAfterComma();
        return "/digits_" + userSet;
    }

    public String pullBank(UserSettings user) {
        Bank bank = user.getBank();
        String userSet = bank.getValue().toLowerCase();
        return "/bank_" + userSet;
    }

    public String[] pullCurrency(UserSettings user) {
        HashSet<Currency> currencies = user.getExchange();
        return currencies.stream().
                map(currency -> "/currency_" + currency.getName().toLowerCase())
                .toArray(String[]::new);
    }
}
