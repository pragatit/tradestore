package org.example.tradestore.scheduler;

import org.example.tradestore.service.TradeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private TradeStoreService tradeStoreService;

    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleExpireFlagUpdate() {
        tradeStoreService.markTradeExpiry();
    }
}