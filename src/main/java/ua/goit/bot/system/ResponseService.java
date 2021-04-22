package ua.goit.bot.system;

import ua.goit.bot.banks.service.BankApiConnectorFacade;
import ua.goit.bot.user.service.UserSettings;
import ua.goit.bot.user.service.UserSettingsStorage;

import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;

public class ResponseService {
    private static final ResponseService RESPONSE_SERVICE = new ResponseService();
    private static final BankApiConnectorFacade CONNECTOR = new BankApiConnectorFacade();
    private static final ResponseCache CACHE = ResponseCache.getInstance();
    private static final String BOLD_OPEN = "<b>";
    private static final String BOLD_CLOSE = "</b>";

    private ResponseService() {
    }

    public static ResponseService getInstance() {
        return RESPONSE_SERVICE;
    }

    public String get(Long ChatId) {
        UserSettingsStorage storage = UserSettingsStorage.getStorage();
        UserSettings settings = storage.getUserSettings(ChatId);
        Bank bank = settings.getBank();
        HashSet<Currency> currencies = settings.getExchange();
        int digitsAfterComa = settings.getDigitsAfterComma();
        StringBuilder builder = new StringBuilder(bank.getText() + ": \n");
        List<BankResponse> response = CACHE.response(bank);
        if (response == null) {
            response = CONNECTOR.getResponse(bank);
            if (response.size() > 0) {
                CACHE.saveResponse(response, bank);
            } else {
                return "Что-то пошло не так. Вероятно, проблемы с сервисом банка.\n" +
                        "Попробуйте позже, или выберете другой банк";
            }
        }
        response.stream().filter(
                bankResponse -> (currencies.contains(bankResponse.getFrom()) && bankResponse.getTo() == Currency.UAH))
                .map(BankResponse::new)
                .forEach(bankResponse ->
                        builder.append(String.format("%s/%s\n%s%s\n%s%s\n",
                                BOLD_OPEN+bankResponse.getFrom().name(),
                                Currency.UAH.name()+BOLD_CLOSE,
                                "Покупка: ",
                                bankResponse.getToBuy().setScale(digitsAfterComa, RoundingMode.HALF_UP),
                                "Продажа: ",
                                bankResponse.getToSold().setScale(digitsAfterComa, RoundingMode.HALF_UP))));
        return builder.toString();
    }
}

