package ua.goit.bot.banks.service;

import ua.goit.bot.banks.service.exchangeRates.ExchangeRatesMonobank;
import ua.goit.bot.system.Bank;
import ua.goit.bot.system.BankResponse;
import ua.goit.bot.system.Currency;

import java.util.function.Function;

public class MonobankApiConnector
        extends GenericConnector<ExchangeRatesMonobank> {
    private static final String CURRENCY_URL = "https://api.monobank.ua/bank/currency";

    public MonobankApiConnector() {
        super(ExchangeRatesMonobank.class);
    }

    public String getUrl() {
        return CURRENCY_URL;
    }

    public Function<ExchangeRatesMonobank, BankResponse> mappingFunction() {
        return exchangeRatesMonobank -> new BankResponse.Builder()
                .bankName(Bank.MONOBANK)
                .toBuy(exchangeRatesMonobank.getRateBuy())
                .toSold(exchangeRatesMonobank.getRateSell())
                .to(Currency.byIso(exchangeRatesMonobank.getBaseCurrencyCode()))
                .from(Currency.byIso(exchangeRatesMonobank.getCurrencyCodeA()))
                .build();

    }
}
