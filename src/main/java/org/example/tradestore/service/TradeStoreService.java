package org.example.tradestore.service;

import org.example.tradestore.model.Trade;


public interface TradeStoreService {
     void initiateTradeStore(Trade trade);

     void markTradeExpiry();
}