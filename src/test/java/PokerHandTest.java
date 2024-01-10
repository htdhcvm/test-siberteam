import org.example.Card;
import org.example.PokerHand;
import org.example.ValidateionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PokerHandTest {
    
    
    @Test
    public void pokerHandsShouldBeCreate() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("2", "S"),
                                                              new Card("3", "S"),
                                                              new Card("4", "S"),
                                                              new Card("5", "S"),
                                                              new Card("6", "S")
        ));
        
        // when
        PokerHand pokerHandFirst = new PokerHand(listCardsForHand);
        PokerHand pokerHandSecond = new PokerHand(listCardsForHand);
        
        // then
        assertEquals(pokerHandFirst, pokerHandSecond);
    }
    
    
    @Test
    public void pokerHandsNotEquals() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("2", "S"),
                                                              new Card("3", "S"),
                                                              new Card("4", "S"),
                                                              new Card("5", "S"),
                                                              new Card("6", "S")
        ));
        
        // when
        PokerHand pokerHandFirst = new PokerHand(new ArrayList<>(listCardsForHand));
        listCardsForHand.set(0, new Card("2", "H"));
        PokerHand pokerHandSecond = new PokerHand(listCardsForHand);
        
        // then
        assertNotEquals(pokerHandFirst, pokerHandSecond);
    }
    
    
    @Test
    public void pokerHandsNotValidWithReplays() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("2", "S"),
                                                              new Card("2", "S"),
                                                              new Card("4", "S"),
                                                              new Card("5", "S"),
                                                              new Card("6", "S")
        ));
        
        String expect = PokerHand.ERROR_MESSAGE_REPLAYS;
        
        // when
        ValidateionException exception = assertThrows(ValidateionException.class,
                                                      () -> new PokerHand(listCardsForHand)
        );
        
        // then
        assertEquals(expect, exception.getMessage());
    }
    
    
    @Test
    public void pokerHandsNotValidToLarge() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("2", "S"),
                                                              new Card("3", "S"),
                                                              new Card("4", "S"),
                                                              new Card("5", "S"),
                                                              new Card("6", "S"),
                                                              new Card("7", "S")
        ));
        
        String expect = String.format("%s %o", PokerHand.ERROR_MESSAGE_SIZE, listCardsForHand.size());
        
        // when
        ValidateionException exception = assertThrows(ValidateionException.class,
                                                      () -> new PokerHand(listCardsForHand)
        );
        
        // then
        assertEquals(expect, exception.getMessage());
    }
}
