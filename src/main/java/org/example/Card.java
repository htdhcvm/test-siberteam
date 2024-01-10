package org.example;

import org.example.valueobject.CardDenomination;
import org.example.valueobject.Suit;

import java.util.Objects;

public class Card {
    
    private final CardDenomination cardDenomination;
    
    private final Suit suit;
    
    public Card(
            String cardDenomination,
            String suit
    ) throws ValidateionException {
        this.cardDenomination = new CardDenomination(cardDenomination);
        this.suit = new Suit(suit);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        
        return cardDenomination.equals(card.cardDenomination) && suit.equals(card.suit);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(cardDenomination, suit);
    }
    
    
    @Override
    public String toString() {
        return String.format("{%s, %s}", cardDenomination.getValue(), suit.getValue());
    }
    
    public CardDenomination getCardDenomination() {
        return cardDenomination;
    }
    
    public Suit getSuit() {
        return suit;
    }
}
