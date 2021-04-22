package ua.goit.bot.banks.service.exchangeRates;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class ExchangeRatesPrivatBank {
    @SerializedName("ccy")
    private String exchangeCurrency;
    @SerializedName("base_ccy")
    private String baseCurrency;
    @SerializedName("buy")
    private BigDecimal rateBuy;
    @SerializedName("sale")
    private BigDecimal rateSell;

    public ExchangeRatesPrivatBank() {
    }

    public ExchangeRatesPrivatBank(String exchangeCurrency, String baseCurrency,
            BigDecimal rateBuy, BigDecimal rateSell) {
        this.exchangeCurrency = exchangeCurrency;
        this.baseCurrency = baseCurrency;
        this.rateBuy = rateBuy;
        this.rateSell = rateSell;
    }

    public String getExchangeCurrency() {
        return exchangeCurrency;
    }

    public void setExchangeCurrency(String exchangeCurrency) {
        this.exchangeCurrency = exchangeCurrency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public BigDecimal getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(BigDecimal rateBuy) {
        this.rateBuy = rateBuy;
    }

    public BigDecimal getRateSell() {
        return rateSell;
    }

    public void setRateSell(BigDecimal rateSell) {
        this.rateSell = rateSell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ExchangeRatesPrivatBank that = (ExchangeRatesPrivatBank) o;
        return Objects.equals(exchangeCurrency, that.exchangeCurrency)
                && Objects.equals(baseCurrency, that.baseCurrency) && Objects
                .equals(rateBuy, that.rateBuy) && Objects
                .equals(rateSell, that.rateSell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchangeCurrency, baseCurrency, rateBuy, rateSell);
    }

    @Override
    public String toString() {
        return "ExchangeRatesPrivatBank{" + "exchangeCurrency='"
                + exchangeCurrency + '\'' + ", baseCurrency='" + baseCurrency
                + '\'' + ", rateBuy=" + rateBuy + ", rateSell=" + rateSell
                + '}';
    }
}
