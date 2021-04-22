package ua.goit.bot.banks.service;

import ua.goit.bot.banks.service.exchangeRates.ExchangeRatesNBU;
import ua.goit.bot.system.Bank;
import ua.goit.bot.system.BankResponse;
import ua.goit.bot.system.Currency;

import java.util.function.Function;

public class NbuApiConnector extends GenericConnector<ExchangeRatesNBU> {
    private static final String CURRENCY_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    public NbuApiConnector() {
        super(ExchangeRatesNBU.class);
    }

    public String getUrl() {
        return CURRENCY_URL;
    }

    public Function<ExchangeRatesNBU, BankResponse> mappingFunction() {
        return exchangeRatesNBU -> new BankResponse.Builder()
                .bankName(Bank.NATIONALBANK).toSold(exchangeRatesNBU.getRate())
                .toBuy(exchangeRatesNBU.getRate()).from(Currency
                        .byIso(exchangeRatesNBU.getExchangeCurrencyCode()))
                .to(Currency.UAH).build();
    }
}
