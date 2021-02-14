package org.example.tradestore.validators.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.example.tradestore.exceptions.TradeExceptionCode;
import org.example.tradestore.exceptions.TradeValidationException;
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
public class MaturityDateValidatorTest {

    @InjectMocks
    private MaturityDateValidator maturityDateValidator;

    @Test
    public void shouldThrowExceptionForMaturityDateLessThanToday() {

        Trade trade = new Trade();
        trade.setTradeId("T1");
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setMaturityDate(DateUtils.addDays(new Date(), -3));
        trade.setVersion(1);

        List<TradeExceptionCode> exceptionCodeList = maturityDateValidator.validate(trade);
        Assertions.assertTrue(exceptionCodeList.size() == 1);
        Assertions.assertTrue(exceptionCodeList.get(0).equals(TradeExceptionCode.INVALID_MATURITY_DATE));

    }

    @Test
    public void shouldNotThrowExceptionForValidMaturityDate() {

        Trade trade = new Trade();
        trade.setTradeId("T1");
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setMaturityDate(DateUtils.addDays(new Date(), 3));
        trade.setVersion(1);

        try {
            maturityDateValidator.validate(trade);
        } catch (TradeValidationException ex) {
            Assertions.fail("Exception not expected");
        }


    }

}