package org.example.tradestore.processors.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.example.tradestore.exceptions.TradeExceptionCode;
import org.example.tradestore.exceptions.TradeValidationException;
import org.example.tradestore.model.Trade;
import org.example.tradestore.validators.InputTradeValidator;
import org.example.tradestore.validators.impl.GeneralTradeValidator;
import org.example.tradestore.validators.impl.MaturityDateValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TradeValidationProcessorTest {

    @Mock
    private GeneralTradeValidator generalTradeValidator;

    @Mock
    private MaturityDateValidator maturityDateValidator;

    @Spy
    private List<InputTradeValidator> validators = new ArrayList<InputTradeValidator>();

    @InjectMocks
    private TradeValidationProcessor tradeValidationProcessor;

    @BeforeEach
    public void init() {

        validators.add(generalTradeValidator);
        validators.add(maturityDateValidator);
    }

    @Test
    public void shouldPassWithoutAnyException() {

        Trade trade = new Trade();
        trade.setTradeId("T1");

        tradeValidationProcessor.processTrade(trade);

        verifyAllValidatorsInvoked(trade);

    }


    @Test
    public void shouldThrowExceptionForGeneralValidationError() {

        Trade trade = new Trade();
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setMaturityDate(DateUtils.addDays(new Date(), 3));
        trade.setVersion(1);

        Mockito.when(generalTradeValidator.validate(trade)).thenReturn(Arrays.asList(TradeExceptionCode.INVALID_TRADE_ID));

        try {
            tradeValidationProcessor.processTrade(trade);
            Assertions.fail("Exception expected");
        } catch (TradeValidationException ex) {
            verifyAllValidatorsInvoked(trade);
            Assertions.assertTrue(ex.getValidatedTrade().getErrorCodes().size() == 1);
            Assertions.assertTrue(ex.getValidatedTrade().getErrorCodes().get(0).equals(TradeExceptionCode.INVALID_TRADE_ID));
        }

    }


    @Test
    public void shouldThrowExceptionForMaturityDateLessThanToday() {

        Trade trade = new Trade();
        trade.setTradeId("T1");
        trade.setBookId("B1");
        trade.setCounterPartyId("CP-1");
        trade.setMaturityDate(DateUtils.addDays(new Date(), -3));
        trade.setVersion(1);

        Mockito.when(maturityDateValidator.validate(trade)).thenReturn(Arrays.asList(TradeExceptionCode.INVALID_MATURITY_DATE));


        try {
            tradeValidationProcessor.processTrade(trade);
            Assertions.fail("Exception expected");
        } catch (TradeValidationException ex) {
            verifyAllValidatorsInvoked(trade);
            Assertions.assertTrue(ex.getValidatedTrade().getErrorCodes().size() == 1);
            Assertions.assertTrue(ex.getValidatedTrade().getErrorCodes().get(0).equals(TradeExceptionCode.INVALID_MATURITY_DATE));
        }

    }

    private void verifyAllValidatorsInvoked(Trade trade) {
        Mockito.verify(generalTradeValidator, Mockito.times(1)).validate(trade);
        Mockito.verify(maturityDateValidator, Mockito.times(1)).validate(trade);
    }

}