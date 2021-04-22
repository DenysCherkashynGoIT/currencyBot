package ua.goit.bot.banks.service.exchangeRates;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class ExchangeRatesNBU {
    @SerializedName("r030")
    private int exchangeCurrencyCode;
    @SerializedName("txt")
    private String nameExchangeCurrency;
    private BigDecimal rate;
    @SerializedName("cc")
    private String exchangeCurrency;
    private String exchangedate;

    public ExchangeRatesNBU() {
    }

    public ExchangeRatesNBU(int exchangeCurrencyCode,
            String nameExchangeCurrency, BigDecimal rate,
            String exchangeCurrency, String exchangedate) {
        this.exchangeCurrencyCode = exchangeCurrencyCode;
        this.nameExchangeCurrency = nameExchangeCurrency;
        this.rate = rate;
        this.exchangeCurrency = exchangeCurrency;
        this.exchangedate = exchangedate;
    }

    public int getExchangeCurrencyCode() {
        return exchangeCurrencyCode;
    }

    public void setExchangeCurrencyCode(int exchangeCurrencyCode) {
        this.exchangeCurrencyCode = exchangeCurrencyCode;
    }

    public String getNameExchangeCurrency() {
        return nameExchangeCurrency;
    }

    public void setNameExchangeCurrency(String nameExchangeCurrency) {
        this.nameExchangeCurrency = nameExchangeCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getExchangeCurrency() {
        return exchangeCurrency;
    }

    public void setExchangeCurrency(String exchangeCurrency) {
        this.exchangeCurrency = exchangeCurrency;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ExchangeRatesNBU that = (ExchangeRatesNBU) o;
        return exchangeCurrencyCode == that.exchangeCurrencyCode && Objects
                .equals(that.rate, rate) && Objects
                .equals(nameExchangeCurrency, that.nameExchangeCurrency)
                && Objects.equals(exchangeCurrency, that.exchangeCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchangeCurrencyCode, nameExchangeCurrency, rate,
                exchangeCurrency);
    }

    @Override
    public String toString() {
        return "ExchangeRatesNBU{" + "exchangeCurrencyCode="
                + exchangeCurrencyCode + ", nameExchangeCurrency='"
                + nameExchangeCurrency + '\'' + ", rate=" + rate
                + ", exchangeCurrency='" + exchangeCurrency + '\''
                + ", exchangedate=" + exchangedate + '}';
    }
}
