package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PokerHand {
    
    public final static String ERROR_MESSAGE_SIZE = "Invalid size hand:";
    public final static String ERROR_MESSAGE_REPLAYS = "Invalid poker hand have a replays";
    
    private final static Integer SIZE_HAND = 5;
    private final List<Card> cardList;
    
    public PokerHand(List<Card> cardList) throws ValidateionException {
        if (!isValidCount(cardList)) {
            throw new ValidateionException(String.format("%s %o", ERROR_MESSAGE_SIZE, cardList.size()));
        }
        
        if (!isValidHand(cardList)) {
            throw new ValidateionException(ERROR_MESSAGE_REPLAYS);
        }
        
        this.cardList = cardList;
    }
    
    private boolean isValidCount(List<Card> cardList) {
        return cardList.size() == SIZE_HAND;
    }
    
    private boolean isValidHand(List<Card> cardList) {
        Set<Card> cards = new HashSet<>();
        
        for (Card card : cardList) {
            
            if (cards.contains(card)) {
                return false;
            }
            cards.add(card);
        }
        
        
        return true;
    }
    
    @Override
    public String toString() {
        return "PokerHand{" + "cardList=" + cardList + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PokerHand pokerHand = (PokerHand) o;
        return Objects.equals(cardList, pokerHand.cardList);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(cardList);
    }
}
