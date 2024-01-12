package org.example;

import org.example.valueobject.CardDenomination;
import org.example.valueobject.CardDenominationValue;
import org.example.valueobject.Suit;

import java.util.*;

import static java.util.stream.Collectors.joining;

public class PokerHand implements Comparable<PokerHand> {
    public final static String ERROR_MESSAGE_SIZE = "Invalid size hand:";
    public final static String ERROR_MESSAGE_REPLAYS = "Invalid poker hand have a replays";
    private final static Integer SIZE_HAND = 5;
    private final List<Card> cardList;
    private final List<Card> cardListSort;
    private final Map<CardDenomination, Integer> cardDenominationCountMap = new HashMap<>();
    private final Map<Suit, Integer> cardSuitCountMap = new HashMap<>();
    private boolean isIncreasingSequence = true;
    private RankPokerHand rank = RankPokerHand.KICKER;
    
    public PokerHand(List<Card> cardList) throws ValidateionException {
        if (!isValidCount(cardList)) {
            throw new ValidateionException(String.format("%s %o", ERROR_MESSAGE_SIZE, cardList.size()));
        }
        
        if (!isValidHand(cardList)) {
            throw new ValidateionException(ERROR_MESSAGE_REPLAYS);
        }
        
        this.cardList = cardList;
        
        List<Card> newListCards = new ArrayList<Card>(cardList);
        Collections.sort(newListCards);
        
        cardListSort = newListCards;
        
        fillMaps();
        checkSequenceOnIncrease();
        ranging();
    }
    
    private boolean isPokerHandHaveOnlyOneSuit() {
        return cardSuitCountMap.size() == 1;
    }
    
    private void ranging() {
        if (isPokerHandHaveOnlyOneSuit()) {
            if (isIncreasingSequence) {
                Card firstCard = cardListSort.get(0);
                
                if (firstCard.getCardDenomination()
                        .equalsValue(CardDenominationValue.TEN)) {
                    rank = RankPokerHand.ROYAL_FLUSH;
                    return;
                }
                
                rank = RankPokerHand.STRAIGHT_FLUSH;
                return;
            }
            
            rank = RankPokerHand.FLASH;
            return;
        }
        
        boolean isCare = cardDenominationCountMap.values()
                .stream()
                .anyMatch(v -> v == 4);
        
        if (isCare) {
            rank = RankPokerHand.KARE;
            return;
        }
        
        
        if (isIncreasingSequence) {
            rank = RankPokerHand.STRAIGHT;
            return;
        }
        
        if (cardSuitCountMap.size() == 1) {
            rank = RankPokerHand.FLASH;
            return;
        }
        
        if (cardDenominationCountMap.size() == 2) {
            rank = RankPokerHand.FULL_HOUSE;
            return;
        }
        
        
        if (cardDenominationCountMap.size() == 4) {
            rank = RankPokerHand.PAIR;
            return;
        }
        
        boolean isSet = cardDenominationCountMap.values()
                .stream()
                .anyMatch(v -> v == 3);
        
        if (isSet) {
            rank = RankPokerHand.SET;
            return;
        }
        
        if (cardDenominationCountMap.size() != 5) {
            rank = RankPokerHand.TWO_PAIRS;
        }
    }
    
    public RankPokerHand getRank() {
        return rank;
    }
    
    private void checkSequenceOnIncrease() {
        Card first = cardListSort.get(0);
        int firstWeight = first.getWeight();
        
        for (Card card : cardListSort) {
            int weight = card.getWeight() - firstWeight;
            
            if (weight != 0) {
                isIncreasingSequence = false;
            }
            firstWeight++;
        }
    }
    
    private void fillMaps() {
        for (Card card : cardList) {
            CardDenomination cardDenomination = card.getCardDenomination();
            Suit suit = card.getSuit();
            
            cardDenominationCountMap.merge(cardDenomination, 1, (oldValue, current) -> oldValue + 1);
            cardSuitCountMap.merge(suit, 1, (oldValue, current) -> oldValue + 1);
        }
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
        String cardListString = cardList.stream()
                .map(v -> String.format("%s", v))
                .collect(joining(" "));
        return String.format("PokerHand(%s) [ %s ]", getRank(), cardListString);
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
    
    @Override
    public int compareTo(PokerHand o) {
        Integer r1 = this.getRank()
                .getRank();
        Integer r2 = o.getRank()
                .getRank();
        
        if (Objects.equals(r1, r2)) {
            return 0;
        }
        
        if (r1 < r2) {
            return -1;
        }
        
        return 1;
    }
}
