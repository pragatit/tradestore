package org.example.tradestore.processors.impl;

import org.example.tradestore.exceptions.TradeExceptionCode;
import org.example.tradestore.model.Trade;
import org.example.tradestore.model.ValidatedTrade;
import org.example.tradestore.exceptions.TradeValidationException;
import org.example.tradestore.processors.TradeProcessor;
import org.example.tradestore.validators.InputTradeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(20)
public class TradeValidationProcessor implements TradeProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeValidationProcessor.class);

    @Autowired
    private List<InputTradeValidator> tradeValidators;

    @Override
    public void processTrade(Trade trade) {

        List<TradeExceptionCode> exceptionList = tradeValidators.stream()
                                                                    .flatMap(tradeValidator -> tradeValidator.validate(trade).stream())
                                                                    .collect(Collectors.toList());
        if(exceptionList.size() > 0){
            LOGGER.error("Validation error for trade with id {}", trade.getTradeId());
            throw new TradeValidationException(new ValidatedTrade(trade, exceptionList));
        }

    }

}
