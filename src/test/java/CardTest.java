import org.example.Card;
import org.example.Pair;
import org.example.ValidateionException;
import org.example.valueobject.CardDenominationValue;
import org.example.valueobject.SuitValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {
    private static Stream<Pair<String, String>> cardsShouldCreated() {
        List<String> listDenomination = Arrays.stream(CardDenominationValue.values())
                .map(CardDenominationValue::getValue)
                .collect(Collectors.toList());
        
        List<String> listSuits = Arrays.stream(SuitValue.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        
        return listDenomination.stream()
                .flatMap(denomination -> listSuits.stream()
                        .map(suit -> new Pair<>(denomination, suit)));
    }
    
    
    @ParameterizedTest
    @MethodSource
    public void cardsShouldCreated(Pair<String, String> pairSuitsDenominations) throws ValidateionException {
        // given
        String denomination = pairSuitsDenominations.getKey();
        String suit = pairSuitsDenominations.getValue();
        
        // when
        Card card = new Card(denomination, suit);
        
        // then
        assertEquals(denomination,
                     card.getCardDenomination()
                             .getValue()
        );
        assertEquals(suit,
                     card.getSuit()
                             .getValue()
        );
    }
    
    
    @Test
    public void cardsShouldBeEquals() throws ValidateionException {
        // given
        String denomination = "2";
        String suit = "S";
        
        boolean expect = true;
        
        // when
        Card cardFirst = new Card(denomination, suit);
        Card cardSecond = new Card(denomination, suit);
        
        // then
        assertEquals(cardFirst.equals(cardSecond), expect);
    }
    
    
    @Test
    public void cardsShouldNotBeEquals() throws ValidateionException {
        // given
        String denominationFirst = "2";
        String suitFirst = "S";
        
        
        String denominationSecond = "3";
        String suitSecond = "S";
        
        boolean expect = false;
        
        // when
        Card cardFirst = new Card(denominationFirst, suitFirst);
        Card cardSecond = new Card(denominationSecond, suitSecond);
        
        // then
        assertEquals(cardFirst.equals(cardSecond), expect);
    }
}
