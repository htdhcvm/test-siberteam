package org.example.valueobject;


import org.example.ValidateionException;

public final class Suit extends ValueObject<String> {
    public Suit(String value) throws ValidateionException {
        super(value);
    }
    
    @Override
    protected boolean isValidate(String value) {

        for (SuitValue suitValue : SuitValue.values()) {
            if (suitValue.name()
                    .equals(value)) {
                return true;
            }
        }
        
        return false;
    }
}
