package ua.goit.bot.controller.command;

import lombok.extern.slf4j.Slf4j;
import ua.goit.bot.controller.Controller;
import ua.goit.bot.controller.CurrencyBot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Slf4j
public final class Commandor extends Actions implements Controller {
    private static Commandor commandor;
    private static final Map<String, Consumer> commands = new HashMap<>();
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);
    private static final String GET_EXCHANGE_COMMAND = "/info";

    private Commandor(CurrencyBot currencyBot) {
        super(currencyBot);
        this.register(start, "/start");
        this.register(info, GET_EXCHANGE_COMMAND);
        this.register(sets, "/settings_");
        this.register(digits, "/digits_",
                "/digits_2",
                "/digits_3",
                "/digits_4");
        this.register(bankOption, "/bank_",
                "/bank_privatbank",
                "/bank_nationalbank",
                "/bank_monobank");
        this.register(currencyOption, "/currency_",
                "/currency_usd",
                "/currency_eur",
                "/currency_rur");
        this.register(closeTable, "/close");
        this.register(closeSettings, "/close_settings");
        this.register(notifyTime, "/notify_",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "Отключить уведомления");
    }

    public static Commandor getInstance(CurrencyBot currencyBot) {
        if (Objects.isNull(commandor)) {
            commandor = new Commandor(currencyBot);
        }
        return commandor;
    }

    public <T> void register(Consumer<T> action, String... inputCommands) {
        if (Objects.nonNull(inputCommands) && inputCommands.length > 0)
            for (String command : inputCommands) {
                commands.put(command, action);
            }
    }

    public <T> void call(String command, T chatInfo) {
        Consumer<T> action = commands.get(command);
        Command <T> method = new Command<>(action);
        method.setChatInfo(chatInfo);
        executor.execute(method);

    }

    @Override
    public void sendText(Long chatID) {
        call(GET_EXCHANGE_COMMAND, chatID);
        log.info("The info for " + chatID.toString() + "is sent");
    }

    @Override
    public void sendText(List<Long> chatIDs) {
        chatIDs.forEach(chatId -> {
            call(GET_EXCHANGE_COMMAND, chatId);
            log.info("The info for " + chatId.toString() + "is sent");
        });
    }
}
