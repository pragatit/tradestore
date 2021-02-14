package org.example.tradestore.validators;

import org.example.tradestore.exceptions.TradeExceptionCode;
import org.example.tradestore.model.Trade;

import java.util.List;

public interface UpdateTradeValidator {
    List<TradeExceptionCode> validate(Trade trade, org.example.tradestore.entity.Trade tradeEntity);
}
