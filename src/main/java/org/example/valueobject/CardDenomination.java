package org.example.valueobject;

import org.example.ValidateionException;

public class CardDenomination extends ValueObject<String> {
    public CardDenomination(String value) throws ValidateionException {
        super(value);
    }
    
    @Override
    protected boolean isValidate(String value) throws ValidateionException {
        for (CardDenominationValue cardDenominationValue : CardDenominationValue.values()) {
            if (cardDenominationValue.getValue()
                    .equals(value)) {
                return true;
            }
        }
        
        return false;
    }
}
