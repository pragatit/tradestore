package org.example.tradestore.processors.impl;

import org.example.tradestore.exceptions.TradeExceptionCode;
import org.example.tradestore.exceptions.TradeValidationException;
import org.example.tradestore.mapper.TradeMapper;
import org.example.tradestore.model.Trade;
import org.example.tradestore.repository.TradeRepository;
import org.example.tradestore.validators.UpdateTradeValidator;
import org.example.tradestore.validators.impl.VersionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TradeUpdateProcessorTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private TradeMapper tradeMapper;

    @Mock
    private VersionValidator versionValidator;

    @Spy
    private List<UpdateTradeValidator> updateTradeValidators = new ArrayList<>();

    @InjectMocks
    private TradeUpdateProcessor tradeUpdateProcessor;

    @BeforeEach
    public void setup(){
        updateTradeValidators.add(versionValidator);
    }

    @Test
    public void shouldCallUpdateForNewTrade(){
        Trade trade = new Trade();
        trade.setTradeId("T1");

        Mockito.when(tradeRepository.findById(trade.getTradeId())).thenReturn(Optional.empty());
        org.example.tradestore.entity.Trade tradeEntity = new org.example.tradestore.entity.Trade();
        Mockito.when(tradeMapper.toTradeEntity(trade)).thenReturn(tradeEntity);

        tradeUpdateProcessor.processTrade(trade);

        Mockito.verify(tradeRepository).findById(trade.getTradeId());
        Mockito.verify(tradeRepository).save(tradeEntity);
    }

    @Test
    public void shouldCallValidatorsForExistingTrade(){
        Trade trade = new Trade();
        trade.setTradeId("T1");

        org.example.tradestore.entity.Trade tradeEntity = new org.example.tradestore.entity.Trade();

        Mockito.when(tradeRepository.findById(trade.getTradeId())).thenReturn(Optional.of(tradeEntity));

        org.example.tradestore.entity.Trade tradeEntityUpdate = new org.example.tradestore.entity.Trade();
        tradeEntityUpdate.setTradeId("T1");
        Mockito.when(tradeMapper.toTradeEntity(trade)).thenReturn(tradeEntityUpdate);

        tradeUpdateProcessor.processTrade(trade);

        Mockito.verify(tradeRepository).findById(trade.getTradeId());
        Mockito.verify(tradeRepository).save(tradeEntityUpdate);
    }

    @Test
    public void shouldNotCallSaveTradeIfValidationError(){
        Trade trade = new Trade();
        trade.setTradeId("T1");

        org.example.tradestore.entity.Trade tradeEntity = new org.example.tradestore.entity.Trade();

        Mockito.when(tradeRepository.findById(trade.getTradeId())).thenReturn(Optional.of(tradeEntity));
        Mockito.when(versionValidator.validate(trade, tradeEntity)).thenReturn(Arrays.asList(TradeExceptionCode.INVALID_VERSION));


        org.example.tradestore.entity.Trade tradeEntityUpdate = new org.example.tradestore.entity.Trade();
        tradeEntityUpdate.setTradeId("T1");

        try {
            tradeUpdateProcessor.processTrade(trade);
            Assertions.fail("Exception expected");
        }catch (TradeValidationException ex) {
            Mockito.verify(tradeRepository).findById(trade.getTradeId());
            Mockito.verify(tradeMapper, Mockito.never()).toTradeEntity(trade);
            Mockito.verify(tradeRepository, Mockito.never()).save(tradeEntityUpdate);
        }
    }

}