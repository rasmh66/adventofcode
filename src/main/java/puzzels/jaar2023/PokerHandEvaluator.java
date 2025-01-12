package puzzels.jaar2023;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PokerHandEvaluator {

    public Puzzel7.CARDTYPE evaluatePokerHand(String hand) {

        Map<Character, Integer> kars = countCharacterFrequency(hand);
        int js = Optional.ofNullable(kars.get('J')).orElse(0);

        // Controleer op specifieke handtypes
        if (isFlush(kars, js)) {
            return Puzzel7.CARDTYPE.FIVE;
        } else if (isFourOfAKind(kars, js)) {
            return Puzzel7.CARDTYPE.FOUR;
        } else if (isFullHouse(kars, js)) {
            return Puzzel7.CARDTYPE.FULL;
        } else if (isThreeOfAKind(kars, js)) {
            return Puzzel7.CARDTYPE.THREE;
        } else if (isTwoPair(kars, js)) {
            return Puzzel7.CARDTYPE.TWO;
        } else if (isOnePair(kars, js)) {
            return Puzzel7.CARDTYPE.ONE;
        } else {
            if (js > 0) {
                return Puzzel7.CARDTYPE.ONE;
            }
            return Puzzel7.CARDTYPE.HIGH;
        }
    }

    protected Map<Character, Integer> countCharacterFrequency(String str) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char ch : str.toCharArray()) {
            // Als het karakter al in de map zit, verhoog het aantal, anders voeg het toe met aantal 1
//            if (ch != 'J') {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
//            }
        }

        return frequencyMap;
    }

    protected static boolean isFlush(Map<Character, Integer> hand, int j) {
        // Check for Flush (five cards of the same suit)
        return hand.containsValue(5 - j) || (hand.size() == 1 && j == 5);
    }

    protected static boolean isFourOfAKind(Map<Character, Integer> hand, int j) {
        // Controleer op Four of a Kind (vier kaarten van dezelfde rang)
        boolean pair = false;
        for (Character c :
                hand.keySet()) {
            if (hand.get(c) == 2 && c != 'J') {
                pair = true;
            }
        }
        return (hand.containsValue(4 - j) && j != 2) ||
                (pair && j == 2);
    }

    protected boolean isFullHouse(Map<Character, Integer> hand, int j) {
        // Controleer op Full House (Three of a Kind en One Pair)
        return (hand.containsValue(3) && hand.containsValue(2 - j)) ||
                (hand.size() == (3) && hand.containsValue(2) && hand.containsValue(1) && j == 1);
    }

    protected static boolean isThreeOfAKind(Map<Character, Integer> hand, int j) {
        // Check for Three of a Kind (three cards of the same rank)
        return hand.containsValue(3 - j);
    }

    private static boolean isTwoPair(Map<Character, Integer> hand, int j) {
        // Controleer op Two Pair (twee verschillende paren kaarten)
        return hand.size() == (3 - j) && hand.containsValue(2 - j) && hand.containsValue(1);
    }

    private static boolean isOnePair(Map<Character, Integer> hand, int j) {
        // Controleer op One Pair (één paar kaarten)
        return hand.size() == 4 && hand.containsValue(2 - j) && hand.containsValue(1);
    }
}
