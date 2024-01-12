package org.example.valueobject;

import org.example.ValidateionException;

import java.util.Objects;

public class CardDenomination extends ValueObject<String> implements Comparable<CardDenomination> {
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
    
    @Override
    public int compareTo(CardDenomination cardDenominationCompare) {
        Integer weightFirst = CardDenominationValue.getWeight(this.getValue());
        Integer weightSecond = CardDenominationValue.getWeight(cardDenominationCompare.getValue());
        
        if (Objects.equals(weightSecond, weightFirst)) {
            return 0;
        }
        
        if (weightFirst < weightSecond) {
            return -1;
        }
        
        return 1;
    }
}
