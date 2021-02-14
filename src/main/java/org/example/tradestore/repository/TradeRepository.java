package org.example.tradestore.repository;

import org.example.tradestore.entity.Trade;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TradeRepository extends CrudRepository<Trade, String> {

    @Modifying
    @Query("update Trade t set t.expired = true where t.maturityDate < :date")
    void markExpiredTrades(@Param("date") LocalDate date);
}
