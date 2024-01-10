package org.example.valueobject;


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
    
    private final String value;
    
    // Конструктор для установки значения для каждой карты
    CardDenominationValue(String value) {
        this.value = value;
    }
    
    // Геттер для получения значения карты
    public String getValue() {
        return value;
    }
}
