package org.example.valueobject;


import java.util.HashMap;
import java.util.Map;

public enum CardDenominationValue {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("T"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");
    
    private static final Map<String, Integer> weights = new HashMap<>();
    
    static {
        weights.put("2", 1);
        weights.put("3", 2);
        weights.put("4", 3);
        weights.put("5", 4);
        weights.put("6", 5);
        weights.put("7", 6);
        weights.put("8", 7);
        weights.put("9", 8);
        weights.put("T", 9);
        weights.put("J", 10);
        weights.put("Q", 11);
        weights.put("K", 12);
        weights.put("A", 13);
    }
    
    private final String value;
    
    CardDenominationValue(String value) {
        this.value = value;
    }
    
    
    public static Integer getWeight(String value) {
        if (weights.containsKey(value)) {
            return weights.get(value);
        }
        
        throw new IllegalArgumentException("?");
    }
    
    public String getValue() {
        return value;
    }
}
