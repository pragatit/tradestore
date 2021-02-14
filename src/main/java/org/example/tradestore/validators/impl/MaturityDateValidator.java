package org.example.tradestore.validators.impl;

import org.example.tradestore.exceptions.TradeExceptionCode;
import org.example.tradestore.model.Trade;
import org.example.tradestore.validators.InputTradeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Order(20)
public class MaturityDateValidator implements InputTradeValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MaturityDateValidator.class);

    @Override
    public List<TradeExceptionCode> validate(Trade trade) {
        List<TradeExceptionCode> tradeExceptionCodes = new ArrayList<>();
        if(trade.getMaturityDate()!= null && isBeforeToday(trade.getMaturityDate()))tradeExceptionCodes.add(TradeExceptionCode.INVALID_MATURITY_DATE);

        LOGGER.trace("MaturityDateValidator completed validation for trade id {} ", trade.getTradeId());

        return tradeExceptionCodes;
    }

    private boolean isBeforeToday(Date maturityDate) {
        return maturityDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now());
    }
}
