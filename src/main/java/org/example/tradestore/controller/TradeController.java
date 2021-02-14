package org.example.tradestore.controller;

import org.example.tradestore.model.Trade;
import org.example.tradestore.service.TradeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

    @Autowired
    private TradeStoreService tradeStoreService;

    /**
     * @param trade Trade to be stored
     */
    @PostMapping(value = "/trade")
    public void processTrade(@RequestBody Trade trade){
        tradeStoreService.initiateTradeStore(trade);
    }

}
