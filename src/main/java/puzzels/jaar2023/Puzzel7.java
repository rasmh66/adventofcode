package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Puzzel7 extends abstractPuzzel {

    public enum CARDTYPE {
        HIGH,
        ONE,
        TWO,
        THREE,
        FULL,
        FOUR,
        FIVE
    }

    public class CamelCard implements Comparable<CamelCard> {
        private String card;
        private int bid;
        private CARDTYPE type;
        private int rank;

        public CamelCard(String cardInput, int bidInput) {
            this.card = cardInput;
            this.bid = bidInput;
        }

        public String getCard() {
            return card;
        }

        public int getBid() {
            return bid;
        }

        public CARDTYPE getType() {
            return type;
        }

        public void setType(CARDTYPE type) {
            this.type = type;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getRank() {
            return rank;
        }

        @Override
        public int compareTo(CamelCard simpson) {
            final String ORDER = "AKQT98765432J";
            int pos1 = 0;
            int pos2 = 0;
            for (int i = 0; i < getCard().length() && pos1 == pos2; i++) {
                pos1 = ORDER.indexOf(simpson.getCard().charAt(i));
                pos2 = ORDER.indexOf(getCard().charAt(i));
            }
            if (pos1 == pos2) {
                return 0;
            }
            return pos1 - pos2;
        }

        @Override
        public String toString() {
            return "CamelCard{" +
                    "card='" + card +
                    ", bid=" + bid +
                    ", type=" + type +
                    ", rank=" + rank +
                    '}' + System.lineSeparator();
        }
    }

    public Puzzel7(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        List<CamelCard> cards = new ArrayList<CamelCard>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] deelStr = line.split(" ");

                int bid = Integer.parseInt(deelStr[1]);
                cards.add(new CamelCard(deelStr[0], bid));
            }
        }
        bepaalTypes(cards);
    }

    private void bepaalTypes(List<CamelCard> camelCardList) {
        for (CamelCard card :
                camelCardList) {
            CARDTYPE ct = bepaalType(card.card);
            card.setType(ct);
        }
        camelCardList.sort(Comparator.comparing(CamelCard::getType));

        List<CamelCard> camelCardSort = new ArrayList<CamelCard>();
        for (CARDTYPE cardType : CARDTYPE.values()) {
            List<CamelCard> camelCardListtype =
                    new ArrayList<>(camelCardList.stream()
                            .filter(card -> card.getType() == cardType)
                            .sorted(Comparator.comparing(CamelCard::getCard).reversed())
                            .toList());
            // Sorteer de array volgens de individuele kaarten in de opgegeven volgorde
            Collections.sort(camelCardListtype);

            camelCardSort.addAll(camelCardListtype);
        }
        System.out.println("camelCardList1 = " + camelCardSort);

        for (int i = 0; i < camelCardSort.size(); i++) {
            camelCardSort.get(i).setRank(i + 1);
        }

//        System.out.println("camelCardList = " + camelCardSort);
        long uitkomst = 0;
        for (CamelCard card :
                camelCardSort) {
            uitkomst += (long) card.getRank() * card.getBid();
        }
        System.out.println("uitkomst = " + uitkomst);
    }

    private CARDTYPE bepaalType(String card) {
        PokerHandEvaluator pe = new PokerHandEvaluator();
        CARDTYPE ct = pe.evaluatePokerHand(card);
        return ct;
    }
}

