import org.example.Card;
import org.example.PokerHand;
import org.example.RankPokerHand;
import org.example.ValidateionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PokerHandTest {
    
    
    @Test
    public void pokerHandsShouldBeCreate() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("6", "S"),
                                                              new Card("5", "S"),
                                                              new Card("4", "S"),
                                                              new Card("3", "S"),
                                                              new Card("2", "S")
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
    
    
    @Test//parameter test
    public void pokerHandsIsRoyalFlush() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("T", "S"),
                                                              new Card("J", "S"),
                                                              new Card("Q", "S"),
                                                              new Card("K", "S"),
                                                              new Card("A", "S")
        ));
        
        PokerHand pokerHandFirst = new PokerHand(listCardsForHand);
        
        // when
        RankPokerHand rankPokerHand = pokerHandFirst.getRank();
        
        // then
        assertEquals(RankPokerHand.ROYAL_FLUSH, rankPokerHand);
    }
    
    
    @Test
    public void pokerHandsIsStraightFlush() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("2", "S"),
                                                              new Card("3", "S"),
                                                              new Card("4", "S"),
                                                              new Card("5", "S"),
                                                              new Card("6", "S")
        ));
        
        PokerHand pokerHandFirst = new PokerHand(listCardsForHand);
        
        // when
        RankPokerHand rankPokerHand = pokerHandFirst.getRank();
        
        // then
        assertEquals(RankPokerHand.STRAIGHT_FLUSH, rankPokerHand);
    }
    
    
    @Test
    public void pokerHandsIsKare() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("T", "S"),
                                                              new Card("T", "H"),
                                                              new Card("T", "D"),
                                                              new Card("T", "C"),
                                                              new Card("A", "S")
        ));
        
        PokerHand pokerHandFirst = new PokerHand(listCardsForHand);
        
        // when
        RankPokerHand rankPokerHand = pokerHandFirst.getRank();
        
        // then
        assertEquals(RankPokerHand.KARE, rankPokerHand);
    }
    
    
    @Test
    public void pokerHandsIsFullHouse() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("2", "S"),
                                                              new Card("2", "H"),
                                                              new Card("T", "H"),
                                                              new Card("T", "S"),
                                                              new Card("T", "C")
        ));
        
        PokerHand pokerHandFirst = new PokerHand(listCardsForHand);
        
        // when
        RankPokerHand rankPokerHand = pokerHandFirst.getRank();
        
        // then
        assertEquals(RankPokerHand.FULL_HOUSE, rankPokerHand);
    }
    
    
    @Test
    public void pokerHandsIsFlash() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("2", "S"),
                                                              new Card("J", "S"),
                                                              new Card("Q", "S"),
                                                              new Card("3", "S"),
                                                              new Card("A", "S")
        ));
        
        PokerHand pokerHandFirst = new PokerHand(listCardsForHand);
        
        // when
        RankPokerHand rankPokerHand = pokerHandFirst.getRank();
        
        // then
        assertEquals(RankPokerHand.FLASH, rankPokerHand);
    }
    
    
    @Test
    public void pokerHandsIsStraight() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("2", "S"),
                                                              new Card("3", "H"),
                                                              new Card("4", "S"),
                                                              new Card("5", "H"),
                                                              new Card("6", "S")
        ));
        
        PokerHand pokerHandFirst = new PokerHand(listCardsForHand);
        
        // when
        RankPokerHand rankPokerHand = pokerHandFirst.getRank();
        
        // then
        assertEquals(RankPokerHand.STRAIGHT, rankPokerHand);
    }
    
    
    @Test
    public void pokerHandsIsSet() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("T", "S"),
                                                              new Card("T", "H"),
                                                              new Card("T", "C"),
                                                              new Card("K", "S"),
                                                              new Card("A", "S")
        ));
        
        PokerHand pokerHandFirst = new PokerHand(listCardsForHand);
        
        // when
        RankPokerHand rankPokerHand = pokerHandFirst.getRank();
        
        // then
        assertEquals(RankPokerHand.SET, rankPokerHand);
    }
    
    
    @Test
    public void pokerHandsIsTwoPairs() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("T", "S"),
                                                              new Card("T", "H"),
                                                              new Card("Q", "S"),
                                                              new Card("Q", "H"),
                                                              new Card("A", "S")
        ));
        
        PokerHand pokerHandFirst = new PokerHand(listCardsForHand);
        
        // when
        RankPokerHand rankPokerHand = pokerHandFirst.getRank();
        
        // then
        assertEquals(RankPokerHand.TWO_PAIRS, rankPokerHand);
    }
    
    
    @Test
    public void pokerHandsIsPair() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("5", "S"),
                                                              new Card("5", "C"),
                                                              new Card("Q", "S"),
                                                              new Card("K", "S"),
                                                              new Card("A", "S")
        ));
        
        PokerHand pokerHandFirst = new PokerHand(listCardsForHand);
        
        // when
        RankPokerHand rankPokerHand = pokerHandFirst.getRank();
        
        // then
        assertEquals(RankPokerHand.PAIR, rankPokerHand);
    }
    
    @Test
    public void pokerHandsIsKicker() throws ValidateionException {
        // given
        List<Card> listCardsForHand = new ArrayList<>(List.of(new Card("T", "S"),
                                                              new Card("2", "S"),
                                                              new Card("3", "H"),
                                                              new Card("4", "C"),
                                                              new Card("5", "S")
        ));
        
        PokerHand pokerHandFirst = new PokerHand(listCardsForHand);
        
        // when
        RankPokerHand rankPokerHand = pokerHandFirst.getRank();
        
        // then
        assertEquals(RankPokerHand.KICKER, rankPokerHand);
    }
    
    
    
//    @Test
//    public void t() throws ValidateionException {
//        // given
//        PokerHand flushRoyal = new PokerHand(List.of(new Card("T", "S"),
//                                                            new Card("J", "S"),
//                                                            new Card("Q", "S"),
//                                                            new Card("K", "S"),
//                                                            new Card("A", "S")
//        ));
//
//        PokerHand straightFlush = new PokerHand(List.of(new Card("2", "S"),
//                                                        new Card("3", "S"),
//                                                        new Card("4", "S"),
//                                                        new Card("5", "S"),
//                                                        new Card("6", "S")
//        ));
//
//        PokerHand kare = new PokerHand(List.of(new Card("T", "S"),
//                                               new Card("T", "H"),
//                                               new Card("T", "D"),
//                                               new Card("T", "C"),
//                                               new Card("A", "S")
//        ));
//
//        PokerHand fullHouse = new PokerHand(List.of(new Card("2", "S"),
//                                                    new Card("2", "H"),
//                                                    new Card("T", "H"),
//                                                    new Card("T", "S"),
//                                                    new Card("T", "C")
//        ));
//
//        PokerHand flush = new PokerHand(List.of(new Card("2", "S"),
//                                                new Card("J", "S"),
//                                                new Card("Q", "S"),
//                                                new Card("3", "S"),
//                                                new Card("A", "S")
//        ));
//
//
//        PokerHand straight = new PokerHand(List.of(new Card("2", "S"),
//                                                   new Card("3", "H"),
//                                                   new Card("4", "S"),
//                                                   new Card("5", "H"),
//                                                   new Card("6", "S")
//        ));
//
//        PokerHand set = new PokerHand(List.of(new Card("T", "S"),
//                                              new Card("T", "H"),
//                                              new Card("T", "C"),
//                                              new Card("K", "S"),
//                                              new Card("A", "S")
//        ));
//
//
//        PokerHand twoPairs = new PokerHand(List.of(new Card("T", "S"),
//                                                   new Card("T", "H"),
//                                                   new Card("Q", "S"),
//                                                   new Card("Q", "H"),
//                                                   new Card("A", "S")
//        ));
//
//        PokerHand pairs = new PokerHand(List.of(new Card("5", "S"),
//                                                new Card("5", "C"),
//                                                new Card("Q", "S"),
//                                                new Card("K", "S"),
//                                                new Card("A", "S")
//        ));
//
//        PokerHand kicker = new PokerHand(List.of(new Card("T", "S"),
//                                                 new Card("2", "S"),
//                                                 new Card("3", "H"),
//                                                 new Card("4", "C"),
//                                                 new Card("5", "S")
//        ));
//
//
//        List<PokerHand> pokerHands = new ArrayList<>(List.of(straightFlush,
//                                                             kicker,
//                                                             straight,
//                                                             kare,
//                                                             fullHouse,
//                                                             pairs,
//                                                             twoPairs,
//                                                             set,
//                                                             flush,
//                                                             flushRoyal
//        ));
//        // when
//
//        System.out.println(pokerHands);
//        System.out.println("\n");
//        Collections.sort(pokerHands);
//
//        System.out.println(pokerHands);
//        System.out.println("\n");
//
//        Collections.sort(pokerHands,Collections.reverseOrder() );
//        System.out.println(pokerHands);
//        System.out.println("\n");
//        // then
//    }
}
