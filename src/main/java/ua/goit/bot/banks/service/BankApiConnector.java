package ua.goit.bot.banks.service;

import ua.goit.bot.system.BankResponse;

import java.util.List;

public interface BankApiConnector {
    List<BankResponse> getHttpResponse();
}
