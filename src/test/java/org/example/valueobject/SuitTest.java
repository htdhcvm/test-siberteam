package org.example.valueobject;

import org.example.ValidateionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuitTest {
    
    @ParameterizedTest
    @EnumSource(SuitValue.class)
    public void suitShouldBeCreated(SuitValue suitValue) throws ValidateionException {
        // given
        String value = suitValue.name();
        
        // when
        Suit suit = new Suit(value);
        
        // then
        assertEquals(suit.getValue(), value);
    }
    
    @Test
    public void suitShouldThrowException() {
        // given
        String value = "q";
        String expect = String.format("%s %s", ValueObject.TEXT_ERROR, value);
        
        // when:
        ValidateionException exception = assertThrows(ValidateionException.class, () -> new Suit(value));
        
        // then
        assertEquals(exception.getMessage(), expect);
    }
    
    @Test
    public void suitsShouldBeEquals() throws ValidateionException {
        // given
        Suit suitFirst = new Suit("S");
        Suit suitSecond = new Suit("S");
        
        boolean expect = true;
        
        // when
        boolean isEquals = suitFirst.equals(suitSecond);
        
        // then
        assertEquals(expect, isEquals);
    }
    
    
    @Test
    public void suitsShouldBeNotEquals() throws ValidateionException {
        // given
        Suit suitFirst = new Suit("S");
        Suit suitSecond = new Suit("H");
        
        boolean expect = false;
        
        // when
        boolean isEquals = suitFirst.equals(suitSecond);
        
        // then
        assertEquals(expect, isEquals);
    }
    
}
