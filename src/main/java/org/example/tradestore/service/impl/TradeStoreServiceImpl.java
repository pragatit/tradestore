package org.example.tradestore.service.impl;

import org.example.tradestore.model.Trade;
import org.example.tradestore.processors.TradeProcessor;
import org.example.tradestore.repository.TradeRepository;
import org.example.tradestore.service.TradeStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TradeStoreServiceImpl implements TradeStoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeStoreServiceImpl.class);

    @Autowired
    private List<TradeProcessor> tradeProcessors;

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public void initiateTradeStore(Trade trade) {
        LOGGER.trace("Start Processing trade with id {} ", trade.getTradeId());
        tradeProcessors.stream().forEach(tradeProcessor -> tradeProcessor.processTrade(trade));
        LOGGER.trace("Processing ends for trade with id {} ", trade.getTradeId());
    }

    @Override
    @Transactional
    public void markTradeExpiry() {
        LOGGER.trace("Check and mark expired trades.");
        tradeRepository.markExpiredTrades(LocalDate.now());
        LOGGER.trace("End marking expired trades. ");
    }


}