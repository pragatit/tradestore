package org.example.tradestore.validators;

import org.example.tradestore.exceptions.TradeExceptionCode;
import org.example.tradestore.model.Trade;
import org.example.tradestore.model.ValidatedTrade;

import java.util.List;

public interface InputTradeValidator {
    List<TradeExceptionCode> validate(Trade trade);
}
