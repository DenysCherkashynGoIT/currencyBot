package ua.goit.bot.banks.service;

import ua.goit.bot.banks.service.exchangeRates.ExchangeRatesPrivatBank;
import ua.goit.bot.system.Bank;
import ua.goit.bot.system.BankResponse;
import ua.goit.bot.system.Currency;

import java.util.function.Function;

public class PrivatBankApiConnector
        extends GenericConnector<ExchangeRatesPrivatBank> {
    private static final String CURRENCY_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

    public PrivatBankApiConnector() {
        super(ExchangeRatesPrivatBank.class);
    }

    public String getUrl() {
        return CURRENCY_URL;
    }

    public Function<ExchangeRatesPrivatBank, BankResponse> mappingFunction() {
        return exchangeRatesPrivatBank -> new BankResponse.Builder()
                .bankName(Bank.PRIVAT)
                .toBuy(exchangeRatesPrivatBank.getRateBuy())
                .toSold(exchangeRatesPrivatBank.getRateSell()).from(Currency
                        .byName(exchangeRatesPrivatBank.getExchangeCurrency()))
                .to(Currency.byName(exchangeRatesPrivatBank.getBaseCurrency()))
                .build();
    }
}
