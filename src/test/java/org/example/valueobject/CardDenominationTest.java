package org.example.valueobject;

import org.example.ValidateionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardDenominationTest {
    
    
    @ParameterizedTest
    @EnumSource(CardDenominationValue.class)
    public void cardDenominationShouldBeCreated(CardDenominationValue cardDenominationValue) throws ValidateionException {
        // given
        String value = cardDenominationValue.getValue();
        
        // when
        CardDenomination cardDenomination = new CardDenomination(value);
        
        // then
        assertEquals(cardDenomination.getValue(), value);
    }
    
    
    @Test
    public void cardDenominationShouldThrowException() {
        // given
        String value = "lq";
        String expect = String.format("%s %s", ValueObject.TEXT_ERROR, value);
        
        // when:
        ValidateionException exception = assertThrows(ValidateionException.class, () -> new CardDenomination(value));
        
        // then
        assertEquals(exception.getMessage(), expect);
    }
    
    
    
    @Test
    public void cardDenominationsShouldBeEquals() throws ValidateionException {
        // given
        CardDenomination cardDenominationFirst = new CardDenomination("2");
        CardDenomination cardDenominationSecond = new CardDenomination("2");
        
        boolean expect = true;
        
        // when
        boolean isEquals = cardDenominationFirst.equals(cardDenominationSecond);
        
        // then
        assertEquals(expect, isEquals);
    }
    
    
    @Test
    public void cardDenominationsShouldBeNotEquals() throws ValidateionException {
        // given
        CardDenomination cardDenominationFirst = new CardDenomination("2");
        CardDenomination cardDenominationSecond = new CardDenomination("3");
        
        boolean expect = false;
        
        // when
        boolean isEquals = cardDenominationFirst.equals(cardDenominationSecond);
        
        // then
        assertEquals(expect, isEquals);
    }
    
    
    
}
