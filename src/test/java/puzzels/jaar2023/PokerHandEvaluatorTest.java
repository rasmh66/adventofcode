package puzzels.jaar2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandEvaluatorTest {

    PokerHandEvaluator pe = new PokerHandEvaluator();

    @Test
    void evaluatePokerHand() {
        String hand = "KKKKJ";
        Puzzel7.CARDTYPE ct = pe.evaluatePokerHand(hand);

        assertEquals(Puzzel7.CARDTYPE.FIVE, ct);
    }

    @Test
    void evaluateFIVE2() {
        String hand = "JJJJJ";
        Puzzel7.CARDTYPE ct = pe.evaluatePokerHand(hand);

        assertEquals(Puzzel7.CARDTYPE.FIVE, ct);
    }

    @Test
    void isNotFullHouse() {
        String hand = "K34KJ";
        Puzzel7.CARDTYPE ct = pe.evaluatePokerHand(hand);

        assertNotEquals(Puzzel7.CARDTYPE.FULL, ct);
    }

    @Test
    void isFullHouse() {
        String hand = "K44KJ";
        Puzzel7.CARDTYPE ct = pe.evaluatePokerHand(hand);

        assertEquals(Puzzel7.CARDTYPE.FULL, ct);
    }

    @Test
    void isTwoPair() {
        String hand = "1A515";
        Puzzel7.CARDTYPE ct = pe.evaluatePokerHand(hand);

        assertEquals(Puzzel7.CARDTYPE.TWO, ct);
    }

    @Test
    void isThreePair() {
        String hand = "1A5J5";
        Puzzel7.CARDTYPE ct = pe.evaluatePokerHand(hand);

        assertEquals(Puzzel7.CARDTYPE.THREE, ct);
    }

    @Test
    void isOnePair() {
        String hand = "1A5B5";
        Puzzel7.CARDTYPE ct = pe.evaluatePokerHand(hand);

        assertEquals(Puzzel7.CARDTYPE.ONE, ct);
    }

    @Test
    void isThreePair2() {
        String hand = "AQ9JJ";
        Puzzel7.CARDTYPE ct = pe.evaluatePokerHand(hand);

        assertEquals(Puzzel7.CARDTYPE.THREE, ct);
    }

    @Test
    void testFour() {
        String hand = "J322J";
        Puzzel7.CARDTYPE ct = pe.evaluatePokerHand(hand);

        assertEquals(Puzzel7.CARDTYPE.FOUR, ct);
    }

}