package org.example.tradestore.exceptions;

public enum TradeExceptionCode {
    INVALID_TRADE_ID("Invalid Trade ID."),
    INVALID_BOOK_ID("Invalid Book Id."),
    INVALID_COUNTER_PARTY_ID("invalid counter party."),
    INVALID_MATURITY_DATE("Invalid maturity date."),
    INVALID_VERSION("Invalid version.");

    private final String message;

    TradeExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
