package org.example.tradestore.processors.impl;

import org.example.tradestore.exceptions.TradeExceptionCode;
import org.example.tradestore.exceptions.TradeValidationException;
import org.example.tradestore.model.Trade;
import org.example.tradestore.model.ValidatedTrade;
import org.example.tradestore.processors.TradeProcessor;
import org.example.tradestore.repository.TradeRepository;
import org.example.tradestore.mapper.TradeMapper;
import org.example.tradestore.validators.UpdateTradeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Order(40)
public class TradeUpdateProcessor implements TradeProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeUpdateProcessor.class);

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private List<UpdateTradeValidator> updateTradeValidators;

    @Override
    @Transactional
    public void processTrade(Trade trade) {
        LOGGER.trace("Update trade start");
        Optional<org.example.tradestore.entity.Trade> optionalTrade = tradeRepository.findById(trade.getTradeId());
        if(optionalTrade.isPresent()){
            List<TradeExceptionCode> tradeExceptionCodes = updateTradeValidators.stream()
                    .flatMap(updateTradeValidator -> updateTradeValidator.validate(trade, optionalTrade.get()).stream()).collect(Collectors.toList());
            if(tradeExceptionCodes.size() > 0){
                throw new TradeValidationException(new ValidatedTrade(trade, tradeExceptionCodes));
            }
        }
        org.example.tradestore.entity.Trade tradeEntity = tradeMapper.toTradeEntity(trade);
        tradeRepository.save(tradeEntity);
        LOGGER.trace("Update trade ends");

    }
}
