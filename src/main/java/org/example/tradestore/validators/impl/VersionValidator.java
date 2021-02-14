package org.example.tradestore.validators.impl;

import org.example.tradestore.exceptions.TradeExceptionCode;
import org.example.tradestore.model.Trade;
import org.example.tradestore.validators.UpdateTradeValidator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Order(10)
public class VersionValidator implements UpdateTradeValidator {

    @Override
    public List<TradeExceptionCode> validate(Trade trade, org.example.tradestore.entity.Trade tradeEntity) {
        ArrayList<TradeExceptionCode> exceptionCodes = new ArrayList<>();
        if(trade.getVersion() < tradeEntity.getVersion()) exceptionCodes.add(TradeExceptionCode.INVALID_VERSION);
        return exceptionCodes;
    }

}
