package com.ben.smith.basics;

/**
 * Created by bensmith on 12/23/17.
 */
public class Hand_Tests {

    public static void main(String[] args) {
        boolean result;

        result = straight_flush_test();
        System.out.println(result);
        result = straight_test();
        System.out.println(result);
        result = flush_test();
        System.out.println(result);
    }

    /*
     Check that any changes to straigh flush still result in this being true
     In this test we get the first 7 cards from suit 0. We test that the straight flush is from 3-7
    */
    public static boolean straight_flush_test() {
        Deck d = new Deck();

        Hand h = new Hand(d.get(0), d.get(1));

        Card[] cards = d.get(12, 13);

        Hand_Priority hp = new Hand_Priority(h, cards);

        hp.add_cards(d.get(2,6));

        Card[] final_hand = hp.get_hand();

        if(final_hand == null) {
            return false;
        } else {
            for(int i = 0; i < 5; i++) {
                if(final_hand[i].getNumber() != 7 - i) {
                    return false;
                }
                if(final_hand[i].getSuit() != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    // Straight test, 7 cards that form a straight but 4 are of 1 suit 3 are of another
    public static boolean straight_test() {
        Deck d = new Deck();

        Hand h = new Hand(d.get(3), d.get(4));

        Card[] cards = d.get(12, 13);

        Hand_Priority hp = new Hand_Priority(h, cards);

        hp.add_cards(d.get(17,21));

        Card[] final_hand = hp.get_hand();

        if(final_hand == null) {
            return false;
        } else {
            for(int i = 0; i < 5; i++) {
                if(!(final_hand[i].getNumber() == 9 - i)) {
                    return false;
                }
            }
            return true;
        }
    }

    // Flush test where the highest card is not one of the flush suit
    public static boolean flush_test() {
        Deck d = new Deck();

        Hand h = new Hand(d.get(42), d.get(44));

        Card[] cards = d.get(46, 48);

        Hand_Priority hp = new Hand_Priority(h, cards);

        hp.add_cards(d.get(38,41));

        Card[] final_hand = hp.get_hand();

        if(final_hand == null) {
            return false;
        } else {
            for(int i = 0; i < 5; i++) {
                if(final_hand[i].getSuit() != 3) {
                    return false;
                }
            }
            return true;
        }
    }


}
