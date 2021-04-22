package ua.goit.bot.banks.service;

import ua.goit.bot.banks.service.exchangeRates.ExchangeRatesMonobank;
import ua.goit.bot.banks.service.exchangeRates.ExchangeRatesNBU;
import ua.goit.bot.banks.service.exchangeRates.ExchangeRatesPrivatBank;
import ua.goit.bot.system.Bank;
import ua.goit.bot.system.BankResponse;

import java.util.List;

public class BankApiConnectorFacade {

    private final GenericConnector<ExchangeRatesPrivatBank> privatBankApiConnector = new PrivatBankApiConnector();
    private final GenericConnector<ExchangeRatesMonobank> monobankApiConnector = new MonobankApiConnector();
    private final GenericConnector<ExchangeRatesNBU> nbuApiConnector = new NbuApiConnector();

    public BankApiConnectorFacade() {
    }

    public List<BankResponse> getResponse(Bank bank) {
        switch (bank) {
        case PRIVAT -> {
            return privatBankApiConnector.getHttpResponse();
        }
        case MONOBANK -> {
            return monobankApiConnector.getHttpResponse();
        }
        case NATIONALBANK -> {
            return nbuApiConnector.getHttpResponse();
        }
        default -> throw new IllegalStateException("Unexpected value: " + bank);
        }
    }
}

