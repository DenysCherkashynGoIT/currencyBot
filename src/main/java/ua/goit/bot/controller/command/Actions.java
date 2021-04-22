package ua.goit.bot.controller.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ua.goit.bot.controller.CurrencyBot;
import ua.goit.bot.system.ResponseService;
import ua.goit.bot.user.service.UserSettings;
import ua.goit.bot.view.Check;
import ua.goit.bot.view.Markup;
import ua.goit.bot.view.menu.*;

import java.time.LocalTime;
import java.util.Objects;
import java.util.function.Consumer;

public class Actions {
    private static Service service;
    private static UserProcessor process;
    private final ResponseService responseService = ResponseService.getInstance();

    public Actions(CurrencyBot currencyBot) {
        service = Service.getInstance(currencyBot);
        process = UserProcessor.getInstance(service);
    }

    protected Consumer<Update> start = (chatInfo) -> {
        process.getUser(chatInfo);
        String text = "Добро пожаловать!\n" +
                "Этот бот поможет отслеживать актуальные курсы валют";
        MenuBlock[] menu = Start.values();
        InlineKeyboardMarkup markup = Markup.inline(menu, 2);
        service.sendNew(chatInfo, text, markup);
    };

    protected Consumer<Object> info = (chatInfo) -> {
        UserSettings user = process.getUser(chatInfo);
        Long chatId = user.getChatId();
        String text = responseService.get(chatId);
        MenuBlock[] menu = Start.values();
        InlineKeyboardMarkup markup = Markup.inline(menu, 1);
        service.sendNew(chatInfo, text, markup);
    };

    protected Consumer<Update> sets = (chatInfo) -> {
        String text = "Настройки";
        MenuBlock[] menu = Settings.values();
        InlineKeyboardMarkup markup = Markup.inlineWithOptional(menu, 2, OptionalButtons.CLOSE_SETS);
        service.sendNew(chatInfo, text, markup);
    };

    protected Consumer<Update> digits = (chatInfo) -> {
        UserSettings user = process.getUser(chatInfo);
        String input = process.getButtonValue(chatInfo, "/digits_");
        process.pushDigits(user, input);
        String userSet = process.pullDigits(user);
        String text = "Выберите количество знаков после запятой:";
        MenuBlock[] menu = Digits.values();
        InlineKeyboardMarkup markup = Markup.inlineWithOptional(menu, 3, OptionalButtons.CLOSE);
        Check.one(markup, userSet);
        service.sendInstead(chatInfo, text, markup);
    };

    protected Consumer<Update> bankOption = (chatInfo) -> {
        UserSettings user = process.getUser(chatInfo);
        String input = process.getButtonValue(chatInfo, "/bank_");
        process.pushBank(user, input);
        String userSet = process.pullBank(user);
        String text = "Выберите банки:";
        MenuBlock[] menu = BankOption.values();
        InlineKeyboardMarkup markup = Markup.inlineWithOptional(menu, 1, OptionalButtons.CLOSE);
        Check.one(markup, userSet);
        service.sendInstead(chatInfo, text, markup);
    };

    protected Consumer<Update> currencyOption = (chatInfo) -> {
        UserSettings user = process.getUser(chatInfo);
        String input = process.getButtonValue(chatInfo, "/currency_");
        process.pushCurrency(user, input);
        String[] userSet = process.pullCurrency(user);
        String text = "Выберите котируемые валюты:";
        MenuBlock[] menu = CurrencyOption.values();
        InlineKeyboardMarkup markup = Markup.inlineWithOptional(menu, 1, OptionalButtons.CLOSE);
        Check.many(markup, userSet);
        service.sendInstead(chatInfo, text, markup);
    };

    protected Consumer<Update> notifyTime = (chatInfo) -> {
        UserSettings user = process.getUser(chatInfo);
        String input = service.getInputData(chatInfo, true);
        String text;
        ReplyKeyboardMarkup markup = null;
        if (input.equals("/notify_")) {
            text = "Выберите оповещения из предложенных вариантов:";
            MenuBlock[] menu = NotificationTime.values();
            markup = Markup.reply(menu, 9);
        } else {
            process.pushNotify(user, input);
            LocalTime userSet = user.getTime();
            text = (Objects.nonNull(userSet)) ?
                    String.format("Установлено ежедневное оповещение в %d:00", userSet.getHour()) :
                    "Ежедневное оповещение отключено";
        }
        service.sendNew(chatInfo, text, markup);
    };

    protected Consumer<Update> closeTable = (chatInfo) -> {
        String text = "Настройки";
        MenuBlock[] menu = Settings.values();
        InlineKeyboardMarkup markup = Markup.inlineWithOptional(menu, 2, OptionalButtons.CLOSE_SETS);
        service.sendInstead(chatInfo, text, markup);
    };

    protected Consumer<Update> closeSettings = (chatInfo) -> {
        service.sendInstead(chatInfo, " ", null);
    };
}