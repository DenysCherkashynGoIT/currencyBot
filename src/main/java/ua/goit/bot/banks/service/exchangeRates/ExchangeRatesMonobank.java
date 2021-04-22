package ua.goit.bot.banks.service.exchangeRates;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class ExchangeRatesMonobank {
    @SerializedName("currencyCodeA")
    private int exchangeCurrencyCode;
    @SerializedName("currencyCodeB")
    private int baseCurrencyCode;
    private BigInteger date;
    private BigDecimal rateSell;
    private BigDecimal rateBuy;
    private BigDecimal rateCross;

    public ExchangeRatesMonobank() {
    }

    public ExchangeRatesMonobank(int exchangeCurrencyCode, int baseCurrencyCode,
            BigInteger data, BigDecimal rateSell, BigDecimal rateBuy,
            BigDecimal rateCross) {
        this.exchangeCurrencyCode = exchangeCurrencyCode;
        this.baseCurrencyCode = baseCurrencyCode;
        this.date = data;
        this.rateSell = rateSell;
        this.rateBuy = rateBuy;
        this.rateCross = rateCross;
    }

    public int getCurrencyCodeA() {
        return exchangeCurrencyCode;
    }

    public void setCurrencyCodeA(int currencyCodeA) {
        this.exchangeCurrencyCode = currencyCodeA;
    }

    public int getBaseCurrencyCode() {
        return baseCurrencyCode;
    }

    public void setBaseCurrencyCode(int baseCurrencyCode) {
        this.baseCurrencyCode = baseCurrencyCode;
    }

    public BigInteger getDate() {
        return date;
    }

    public void setDate(BigInteger date) {
        this.date = date;
    }

    public BigDecimal getRateSell() {
        return rateSell;
    }

    public void setRateSell(BigDecimal rateSell) {
        this.rateSell = rateSell;
    }

    public BigDecimal getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(BigDecimal rateBuy) {
        this.rateBuy = rateBuy;
    }

    public BigDecimal getRateCross() {
        return rateCross;
    }

    public void setRateCross(BigDecimal rateCross) {
        this.rateCross = rateCross;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ExchangeRatesMonobank that = (ExchangeRatesMonobank) o;
        return exchangeCurrencyCode == that.exchangeCurrencyCode
                && baseCurrencyCode == that.baseCurrencyCode && Objects
                .equals(rateSell, that.rateSell) && Objects
                .equals(rateBuy, that.rateBuy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchangeCurrencyCode, baseCurrencyCode, rateSell,
                rateBuy);
    }

    @Override
    public String toString() {
        return "ExchangeRatesMonobank{" + "exchangeCurrencyCode="
                + exchangeCurrencyCode + ", baseCurrencyCode="
                + baseCurrencyCode + ", data=" + date + ", rateSell=" + rateSell
                + ", rateBuy=" + rateBuy + ", rateCross=" + rateCross + '}';
    }
}
