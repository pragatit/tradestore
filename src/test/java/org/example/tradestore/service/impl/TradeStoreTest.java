package org.example.tradestore.service.impl;

import org.example.tradestore.processors.TradeProcessor;
import org.example.tradestore.processors.impl.TradeUpdateProcessor;
import org.example.tradestore.processors.impl.TradeValidateProcessor;
import org.example.tradestore.validators.InputTradeValidator;
import org.example.tradestore.validators.UpdateTradeValidator;
import org.example.tradestore.validators.impl.GeneralTradeValidator;
import org.example.tradestore.validators.impl.MaturityDateValidator;
import org.example.tradestore.validators.impl.VersionValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TradeStoreTest {

    @Autowired
    private List<TradeProcessor> tradeProcessors;

    @Autowired
    private List<InputTradeValidator> inputTradeValidators;

    @Autowired
    private List<UpdateTradeValidator> updateTradeValidators;

    @Test
    public void shouldCallProcessorsInSequence(){
        assertEquals(2, tradeProcessors.size());
        assertTrue(tradeProcessors.get(0) instanceof TradeValidateProcessor);
        assertTrue(tradeProcessors.get(1) instanceof TradeUpdateProcessor);
    }

    @Test
    public void shouldCallInputValidatorsInSequence(){
        assertEquals(2, inputTradeValidators.size());
        assertTrue(inputTradeValidators.get(0) instanceof GeneralTradeValidator);
        assertTrue(inputTradeValidators.get(1) instanceof MaturityDateValidator);
    }

    @Test
    public void shouldCallUpdateValidatorsInSequence(){
        assertEquals(1, updateTradeValidators.size());
        assertTrue(updateTradeValidators.get(0) instanceof VersionValidator);
    }

}