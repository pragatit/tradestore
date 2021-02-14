package org.example.tradestore.model;

import lombok.Getter;
import lombok.Setter;
import org.example.tradestore.exceptions.TradeExceptionCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class ValidatedTrade {
    private Trade trade;
    private List<TradeExceptionCode> errorCodes;

    public ValidatedTrade(Trade trade, List<TradeExceptionCode> errorCodes) {
        this.trade = trade;
        this.errorCodes = errorCodes;
    }
}
