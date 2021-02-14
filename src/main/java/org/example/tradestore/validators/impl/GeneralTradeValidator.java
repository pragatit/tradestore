package org.example.tradestore.validators.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.tradestore.exceptions.TradeExceptionCode;
import org.example.tradestore.model.Trade;
import org.example.tradestore.validators.InputTradeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(10)
public class GeneralTradeValidator implements InputTradeValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralTradeValidator.class);

    @Override
    public List<TradeExceptionCode> validate(Trade trade) {

        List<TradeExceptionCode> tradeExceptionCodes = new ArrayList<>();

        if(StringUtils.isEmpty(trade.getTradeId())) tradeExceptionCodes.add(TradeExceptionCode.INVALID_TRADE_ID);
        if(StringUtils.isEmpty(trade.getBookId())) tradeExceptionCodes.add(TradeExceptionCode.INVALID_BOOK_ID);
        if(StringUtils.isEmpty(trade.getCounterPartyId())) tradeExceptionCodes.add(TradeExceptionCode.INVALID_COUNTER_PARTY_ID);
        if(ObjectUtils.isEmpty(trade.getMaturityDate())) tradeExceptionCodes.add(TradeExceptionCode.INVALID_MATURITY_DATE);
        if(trade.getVersion() < 1) tradeExceptionCodes.add(TradeExceptionCode.INVALID_VERSION);

        LOGGER.trace("GeneralTradeValidator completed validation for trade id {} ", trade.getTradeId());

        return tradeExceptionCodes;
    }
}
