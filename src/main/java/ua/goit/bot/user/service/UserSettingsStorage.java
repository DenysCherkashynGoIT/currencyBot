package ua.goit.bot.user.service;

import lombok.extern.slf4j.Slf4j;
import ua.goit.bot.system.Bank;
import ua.goit.bot.system.Currency;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
public class UserSettingsStorage implements Storage {
    private static final UserSettingsStorage STORAGE = new UserSettingsStorage();
    private final ConcurrentHashMap<Long, UserSettings> userSettingsStorage = new ConcurrentHashMap<>();

    private UserSettingsStorage() {
    }

    public static UserSettingsStorage getStorage(){
        return STORAGE;
    }

    @Override
    public void putUserSettings(UserSettings userSettings) {
        Long chatId = userSettings.getChatId();
        userSettingsStorage.putIfAbsent(chatId, userSettings);
        log.debug("The user " + chatId.toString() + "is saved in storage", userSettings);
    }

    @Override
    public UserSettings getUserSettings(Long chatId) {
        return userSettingsStorage.get(chatId);
    }

    public void updateBank(Long chatId, Bank bank) {
        userSettingsStorage.computeIfPresent(chatId, (keyLong, userSettings) -> {
            userSettings.setBank(bank);
            log.info("The userSettings for " + chatId.toString() + " is updated", bank);
            return userSettings;
        });
    }

    public void updateDigitsAfterComma(Long chatId, int digitsAfterComma) {
        userSettingsStorage.computeIfPresent(chatId, (keyLong, userSettings) -> {
            userSettings.setDigitsAfterComma(digitsAfterComma);
            log.info("The userSettings for " + chatId.toString() + " is updated", digitsAfterComma);
            return userSettings;
        });
    }

    public void updateTime(Long chatId, String text) {
        userSettingsStorage.computeIfPresent(chatId, (keyLong, userSettings) -> {
            userSettings.setTime(LocalTime.of(Integer.parseInt(text), 0));
            log.info("The userSettings for " + chatId.toString() + " is updated", text);
            return userSettings;
        });
    }

    public void updateCurrency(Long chatId, Currency currency) {

        userSettingsStorage.computeIfPresent(chatId, (keyLong, userSettings) -> {
            if (userSettings.getExchange().contains(currency)) {
                userSettings.getExchange().remove(currency);
            } else {
                userSettings.getExchange().add(currency);
            }
            log.info("The userSettings for " + chatId.toString() + " is updated", currency);
            return userSettings;
        });
    }

    public List<Long> getChatIdsByTime(Integer time){
        log.info("The list for " + time + " is collecting");
        return userSettingsStorage.values().stream()
                .filter(userSettings -> userSettings.getTime().equals(LocalTime.of(time, 0)))
                .map(UserSettings::getChatId)
                .collect(Collectors.toList());
    }
}