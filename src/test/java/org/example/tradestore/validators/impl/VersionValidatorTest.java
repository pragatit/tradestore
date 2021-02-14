package org.example.tradestore.validators.impl;

import org.example.tradestore.exceptions.TradeExceptionCode;
import org.example.tradestore.model.Trade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class VersionValidatorTest {

    @InjectMocks
    private VersionValidator versionValidator;

    @Test
    public void shouldAddExceptionIfVersionIsLessThanExisting(){
        Trade trade = new Trade();
        trade.setVersion(1);

        org.example.tradestore.entity.Trade tradeEntity = new org.example.tradestore.entity.Trade();
        tradeEntity.setVersion(3);

        List<TradeExceptionCode> exceptionCodes = versionValidator.validate(trade, tradeEntity);

        Assertions.assertTrue(exceptionCodes.size() > 0);
        Assertions.assertTrue(exceptionCodes.get(0).equals(TradeExceptionCode.INVALID_VERSION));
    }

    @Test
    public void shouldNotAddExceptionForSameVersion(){
        Trade trade = new Trade();
        trade.setVersion(1);

        org.example.tradestore.entity.Trade tradeEntity = new org.example.tradestore.entity.Trade();
        tradeEntity.setVersion(1);

        List<TradeExceptionCode> exceptionCodes = versionValidator.validate(trade, tradeEntity);

        Assertions.assertTrue(exceptionCodes.size() == 0);
    }

    @Test
    public void shouldNotAddExceptionForGreaterVersion(){
        Trade trade = new Trade();
        trade.setVersion(2);

        org.example.tradestore.entity.Trade tradeEntity = new org.example.tradestore.entity.Trade();
        tradeEntity.setVersion(1);

        List<TradeExceptionCode> exceptionCodes = versionValidator.validate(trade, tradeEntity);

        Assertions.assertTrue(exceptionCodes.size() == 0);
    }


}