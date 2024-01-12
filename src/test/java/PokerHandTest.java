import org.example.Card;
import org.example.PokerHand;
import org.example.RankPokerHand;
import org.example.ValidateionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PokerHandTest {
    private static final Map<RankPokerHand, List<Card>> rankPokerHandListMap = new HashMap<>();
    
    static {
        
        try {
            rankPokerHandListMap.put(RankPokerHand.ROYAL_FLUSH,
                                     List.of(new Card("T", "S"),
                                             new Card("J", "S"),
                                             new Card("Q", "S"),
                                             new Card("K", "S"),
                                             new Card("A", "S")
                                     )
            );
            
            rankPokerHandListMap.put(RankPokerHand.STRAIGHT_FLUSH,
                                     List.of(new Card("2", "S"),
                                             new Card("3", "S"),
                                             new Card("4", "S"),
                                             new Card("5", "S"),
                                             new Card("6", "S")
                                     )
            );
            
            rankPokerHandListMap.put(RankPokerHand.KARE,
                                     List.of(new Card("T", "S"),
                                             new Card("T", "H"),
                                             new Card("T", "D"),
                                             new Card("T", "C"),
                                             new Card("A", "S")
                                     )
            );
            
            rankPokerHandListMap.put(RankPokerHand.FULL_HOUSE,
                                     List.of(new Card("2", "S"),
                                             new Card("2", "H"),
                                             new Card("T", "H"),
                                             new Card("T", "S"),
                                             new Card("T", "C")
                                     )
            );
            
            rankPokerHandListMap.put(RankPokerHand.FLASH,
                                     List.of(new Card("2", "S"),
                                             new Card("J", "S"),
                                             new Card("Q", "S"),
                                             new Card("3", "S"),
                                             new Card("A", "S")
                                     )
            );
            
            rankPokerHandListMap.put(RankPokerHand.STRAIGHT,
                                     List.of(new Card("2", "S"),
                                             new Card("3", "H"),
                                             new Card("4", "S"),
                                             new Card("5", "H"),
                                             new Card("6", "S")
                                     )
            );
            
            rankPokerHandListMap.put(RankPokerHand.SET,
                                     List.of(new Card("T", "S"),
                                             new Card("T", "H"),
                                             new Card("T", "C"),
                                             new Card("K", "S"),
                                             new Card("A", "S")
                                     )
            );
            
            rankPokerHandListMap.put(RankPokerHand.TWO_PAIRS,
                                     List.of(new Card("T", "S"),
                                             new Card("T", "H"),
                                             new Card("Q", "S"),
                                             new Card("Q", "H"),
                                             new Card("A", "S")
                                     )
            );
            
            rankPokerHandListMap.put(RankPokerHand.PAIR,
                                     List.of(new Card("5", "S"),
                                             new Card("5", "C"),
                                             new Card("Q", "S"),
                                             new Card("K", "S"),
                                             new Card("A", "S")
                                     )
            );
            
            rankPokerHandListMap.put(RankPokerHand.KICKER,
                                     List.of(new Card("T", "S"),
                                             new Card("2", "S"),
                                             new Card("3", "H"),
                                             new Card("4", "C"),
                                             new Card("5", "S")
                                     )
            );
        } catch (ValidateionException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static Stream<Arguments> shouldMatchAllCombinations() throws ValidateionException {
        return Stream.of(Arguments.of(rankPokerHandListMap.get(RankPokerHand.ROYAL_FLUSH), RankPokerHand.ROYAL_FLUSH),
                         Arguments.of(rankPokerHandListMap.get(RankPokerHand.STRAIGHT_FLUSH),
                                      RankPokerHand.STRAIGHT_FLUSH
                         ),
                         Arguments.of(rankPokerHandListMap.get(RankPokerHand.KARE), RankPokerHand.KARE),
                         Arguments.of(rankPokerHandListMap.get(RankPokerHand.FULL_HOUSE), RankPokerHand.FULL_HOUSE),
                         Arguments.of(rankPokerHandListMap.get(RankPokerHand.FLASH), RankPokerHand.FLASH),
                         Arguments.of(rankPokerHandListMap.get(RankPokerHand.STRAIGHT), RankPokerHand.STRAIGHT),
                         Arguments.of(rankPokerHandListMap.get(RankPokerHand.SET), RankPokerHand.SET),
                         Arguments.of(rankPokerHandListMap.get(RankPokerHand.TWO_PAIRS), RankPokerHand.TWO_PAIRS),
                         Arguments.of(rankPokerHandListMap.get(RankPokerHand.PAIR), RankPokerHand.PAIR),
                         Arguments.of(rankPokerHandListMap.get(RankPokerHand.KICKER), RankPokerHand.KICKER)
        );
    }
    
    
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
    
    @ParameterizedTest
    @MethodSource
    public void shouldMatchAllCombinations(
            List<Card> listCards,
            RankPokerHand expect
    ) throws ValidateionException {
        // given
        PokerHand pokerHandFirst = new PokerHand(listCards);
        
        // when
        RankPokerHand rankPokerHand = pokerHandFirst.getRank();
        
        // then
        assertEquals(expect, rankPokerHand);
    }
    
    
    @Test
    public void shouldBeEquals() throws ValidateionException {
        // given
        List<Card> royalFlush = rankPokerHandListMap.get(RankPokerHand.ROYAL_FLUSH);
        PokerHand first = new PokerHand(royalFlush);
        PokerHand second = new PokerHand(royalFlush);
        
        int expect = 0;
        
        // when
        int response = first.compareTo(second);
        
        // then
        assertEquals(expect, response);
    }
    
    
    @Test
    public void shouldFirstMoreThenSecond() throws ValidateionException {
        // given
        PokerHand first = new PokerHand(rankPokerHandListMap.get(RankPokerHand.ROYAL_FLUSH));
        PokerHand second = new PokerHand(rankPokerHandListMap.get(RankPokerHand.KICKER));
        
        int expect = 1;
        
        // when
        int response = first.compareTo(second);
        
        // then
        assertEquals(expect, response);
    }
    
    
    @Test
    public void shouldSecondMoreThenFirst() throws ValidateionException {
        // given
        PokerHand first = new PokerHand(rankPokerHandListMap.get(RankPokerHand.KICKER));
        PokerHand second = new PokerHand(rankPokerHandListMap.get(RankPokerHand.ROYAL_FLUSH));
        
        int expect = -1;
        
        // when
        int response = first.compareTo(second);
        
        // then
        assertEquals(expect, response);
    }
    
    
    @Test
    public void shouldBeAscOrder() throws ValidateionException {
        // given
        List<List<Card>> cards = new ArrayList<>(rankPokerHandListMap.values());
        List<PokerHand> pokerHands = cards.stream()
                .map(v -> {
                    try {
                        return new PokerHand(v);
                    } catch (ValidateionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        
        List<PokerHand> expectOrder =
                new ArrayList<>(List.of(new PokerHand(rankPokerHandListMap.get(RankPokerHand.KICKER)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.PAIR)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.TWO_PAIRS)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.SET)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.STRAIGHT)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.FLASH)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.FULL_HOUSE)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.KARE)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.STRAIGHT_FLUSH)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.ROYAL_FLUSH))
        ));
        
        // when
        Collections.sort(pokerHands);
        
        // then
        assertEquals(expectOrder, pokerHands);
    }
    
    
    @Test
    public void shouldBeDescOrder() throws ValidateionException {
        // given
        List<List<Card>> cards = new ArrayList<>(rankPokerHandListMap.values());
        List<PokerHand> pokerHands = cards.stream()
                .map(v -> {
                    try {
                        return new PokerHand(v);
                    } catch (ValidateionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        
        List<PokerHand> expectOrder =
                new ArrayList<>(List.of(new PokerHand(rankPokerHandListMap.get(RankPokerHand.ROYAL_FLUSH)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.STRAIGHT_FLUSH)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.KARE)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.FULL_HOUSE)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.FLASH)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.STRAIGHT)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.SET)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.TWO_PAIRS)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.PAIR)),
                                                              new PokerHand(rankPokerHandListMap.get(RankPokerHand.KICKER))
        ));
        
        // when
        pokerHands.sort(Collections.reverseOrder());
        
        // then
        assertEquals(expectOrder, pokerHands);
    }
    
    
}
