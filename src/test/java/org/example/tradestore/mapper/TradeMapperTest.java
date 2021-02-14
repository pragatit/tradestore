package org.example.tradestore.mapper;

import org.example.tradestore.model.Trade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest
public class TradeMapperTest {

    @Autowired
    private TradeMapper tradeMapper;

    @Test
    public void shouldMapTrade(){

        Trade trade = new Trade();
        trade.setTradeId("T1");
        trade.setMaturityDate(Date.from(LocalDate.now().plusDays(100).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        org.example.tradestore.entity.Trade tradeEntity = tradeMapper.toTradeEntity(trade);

        Assertions.assertEquals(trade.getTradeId(), tradeEntity.getTradeId());
        Assertions.assertFalse(tradeEntity.isExpired());

    }

}