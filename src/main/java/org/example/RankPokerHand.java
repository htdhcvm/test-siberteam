package org.example;

public enum RankPokerHand {
    KICKER(1),
    PAIR(2),
    TWO_PAIRS(3),
    SET(4),
    STRAIGHT(5),
    FLASH(6),
    FULL_HOUSE(7),
    KARE(8),
    STRAIGHT_FLUSH(9),
    ROYAL_FLUSH(10);
    
    private final Integer rank;
    
    RankPokerHand(Integer rank) {
        this.rank = rank;
    }
    
    public Integer getRank() {
        return rank;
    }
}



