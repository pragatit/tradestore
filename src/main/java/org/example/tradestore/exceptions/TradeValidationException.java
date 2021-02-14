package org.example.tradestore.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.tradestore.model.ValidatedTrade;

@Getter
@AllArgsConstructor
public class TradeValidationException extends RuntimeException {

    private ValidatedTrade validatedTrade;

}
