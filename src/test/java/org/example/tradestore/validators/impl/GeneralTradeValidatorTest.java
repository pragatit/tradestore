package org.example.tradestore.validators.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.example.tradestore.exceptions.TradeExceptionCode;
import org.example.tradestore.model.Trade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GeneralTradeValidatorTest {

    @InjectMocks
    private GeneralTradeValidator generalTradeValidator;

    @Test
    public void shouldThrowExceptionForEmptyTradeId() {

        Trade trade = new Trade();
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setMaturityDate(DateUtils.addDays(new Date(), 3));
        trade.setVersion(1);


        List<TradeExceptionCode> exceptionCodeList = generalTradeValidator.validate(trade);
        Assertions.assertTrue(exceptionCodeList.size() == 1);
        Assertions.assertTrue(exceptionCodeList.get(0).equals(TradeExceptionCode.INVALID_TRADE_ID));

    }

    @Test
    public void shouldThrowExceptionForEmptyFieldsOtherThanTradeId() {

        Trade trade = new Trade();
        trade.setTradeId("T1");

        List<TradeExceptionCode> exceptionCodeList = generalTradeValidator.validate(trade);
        Assertions.assertTrue(exceptionCodeList.size() == 4);
        Assertions.assertTrue(exceptionCodeList.get(0).equals(TradeExceptionCode.INVALID_BOOK_ID));
        Assertions.assertTrue(exceptionCodeList.get(1).equals(TradeExceptionCode.INVALID_COUNTER_PARTY_ID));
        Assertions.assertTrue(exceptionCodeList.get(2).equals(TradeExceptionCode.INVALID_MATURITY_DATE));
        Assertions.assertTrue(exceptionCodeList.get(3).equals(TradeExceptionCode.INVALID_VERSION));


    }

}