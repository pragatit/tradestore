package org.example.tradestore.mapper;

import org.example.tradestore.entity.Trade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.ZoneId;

@Mapper(componentModel = "spring", imports = {ZoneId.class, LocalDate.class})
public interface TradeMapper {

    @Mapping(target = "expired", expression = "java(trade.getMaturityDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now()))")
    Trade toTradeEntity(org.example.tradestore.model.Trade trade);

}
