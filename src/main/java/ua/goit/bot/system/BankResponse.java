package ua.goit.bot.system;

import java.math.BigDecimal;

public class BankResponse {

    private BigDecimal toBuy;
    private BigDecimal toSold;
    private Currency to;
    private Currency from;
    private Bank bank;

    public BankResponse() {
    }

    public BankResponse(BankResponse bankResponse) {
        this.toBuy = bankResponse.toBuy;
        this.toSold = bankResponse.toSold;
        this.to = bankResponse.to;
        this.from = bankResponse.from;
        this.bank = bankResponse.bank;
    }

    public BigDecimal getToBuy() {
        return toBuy;
    }

    public BigDecimal getToSold() {
        return toSold;
    }

    public Currency getTo() {
        return to;
    }

    public Currency getFrom() {
        return from;
    }

    public Bank getBank() {
        return bank;
    }

    public String getBankValue() {
        return bank.getValue();
    }

    @Override
    public String toString() {
        return "Курс в " + this.getBank() + ": " + this.getFrom() + "/" + this.getTo() +
                "\nПродажа: " + this.toSold + "\nПокупка: " + this.toBuy;
    }

    public static class Builder {
        private BankResponse newBankResponse;

        public Builder() {
            newBankResponse = new BankResponse();
        }

        public Builder to(Currency to) {
            newBankResponse.to = to;
            return this;
        }

        public Builder from(Currency from) {
            newBankResponse.from = from;
            return this;
        }

        public Builder bankName(Bank name) {
            newBankResponse.bank = name;
            return this;
        }

        public Builder toBuy(BigDecimal toBuy) {
            newBankResponse.toBuy = toBuy;
            return this;
        }

        public Builder toSold(BigDecimal toSold) {
            newBankResponse.toSold = toSold;
            return this;
        }

        public BankResponse build() {
            return newBankResponse;
        }
    }
}

