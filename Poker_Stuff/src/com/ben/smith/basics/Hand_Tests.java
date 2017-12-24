package com.ben.smith.basics;

/**
 * Created by bensmith on 12/23/17.
 */
public class Hand_Tests {

    public static void main(String[] args) {
        boolean result;

        result = straight_flush_test();
        System.out.println("Straight flush:\t" + result);
        result = four_of_a_kind_test();
        System.out.println("4 of a kind:\t" + result);
        result = flush_test();
        System.out.println("Flush:\t\t\t" + result);
        result = straight_test();
        System.out.println("Straight:\t\t" + result);
        result = three_of_a_kind_test();
        System.out.println("3 of a kind:\t" + result);
        result = two_pair_test();
        System.out.println("Two pair:\t\t" + result);
        result = one_pair_test();
        System.out.println("Pair:\t\t\t" + result);
        result = high_cards_test();
        System.out.println("High card:\t\t" + result);
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

    // There should be 4 sixes and 1 seven given the example.
    public static boolean four_of_a_kind_test() {

        Deck d = new Deck();

        Hand h = new Hand(d.get(3), d.get(4));

        Card[] cards = d.get(17, 18);

        Hand_Priority hp = new Hand_Priority(h, cards);

        hp.add_cards(d.get(30,31));

        hp.add_cards(d.get(42,45));

        Card[] final_hand = hp.get_hand();

        if(final_hand == null) {
            return false;
        } else {
            int six_count = 0;
            for(int i = 0; i < 5; i++) {
                if(final_hand[i].getNumber() == 6) {
                    six_count++;
                }
            }
            if(six_count != 4 || final_hand[4].getNumber() != 7) {
                return false;
            }
            return true;
        }
    }

    // TODO
    public static boolean full_house_test() {
        return false;
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

    public static boolean three_of_a_kind_test() {
        Deck d = new Deck();

        Hand h = new Hand(d.get(3), d.get(4));

        Card[] cards = d.get(15, 17);

        Hand_Priority hp = new Hand_Priority(h, cards);

        hp.add_cards(d.get(29,30));
        hp.add_cards(d.get(37,39));

        Card[] final_hand = hp.get_hand();

        if(final_hand == null) {
            return false;
        } else {
            int five_count = 0;
            for(int i = 0; i < 5; i++) {
                if(final_hand[i].getNumber() == 5) {
                    five_count++;
                }
            }
            if(five_count != 3) {
                return false;
            }
            return true;
        }
    }

    public static boolean two_pair_test() {
        Deck d = new Deck();

        Hand h = new Hand(d.get(3), d.get(4));

        Card[] cards = d.get(15, 17);

        Hand_Priority hp = new Hand_Priority(h, cards);

        hp.add_cards(d.get(28,29));
        hp.add_cards(d.get(36,37));
        hp.add_cards(d.get(49,50));

        Card[] final_hand = hp.get_hand();

        if(final_hand == null) {
            return false;
        } else {
            int twelve_count = 0;
            int five_count = 0;
            for(int i = 0; i < 5; i++) {
                if(final_hand[i].getNumber() == 5) {
                    five_count++;
                } else if(final_hand[i].getNumber() == 12) {
                    twelve_count++;
                }
            }
            if(twelve_count != 2 || five_count != 2) {
                return false;
            }
            return true;
        }
    }

    public static boolean one_pair_test() {
        Deck d = new Deck();

        Hand h = new Hand(d.get(3), d.get(4));

        Card[] cards = d.get(14, 16);

        Hand_Priority hp = new Hand_Priority(h, cards);

        hp.add_cards(d.get(28,29));
        hp.add_cards(d.get(36,37));
        hp.add_cards(d.get(47,48));

        Card[] final_hand = hp.get_hand();

        if(final_hand == null) {
            return false;
        } else {
            int four_count = 0;
            for(int i = 0; i < 5; i++) {
                if(final_hand[i].getNumber() == 4) {
                    four_count++;
                }
            }
            if(four_count != 2) {
                return false;
            }
            return true;
        }
    }

    public static boolean high_cards_test() {
        Deck d = new Deck();

        Hand h = new Hand(d.get(3), d.get(4));

        Card[] cards = d.get(18, 20);

        Hand_Priority hp = new Hand_Priority(h, cards);

        hp.add_cards(d.get(1,2));
        hp.add_cards(d.get(37,39));

        Card[] final_hand = hp.get_hand();

        if(final_hand[0].getNumber() != 14) {
            return false;
        }
        if(final_hand[1].getNumber() != 13) {
            return false;
        }
        if(final_hand[2].getNumber() != 8) {
            return false;
        }
        if(final_hand[3].getNumber() != 7) {
            return false;
        }
        if(final_hand[4].getNumber() != 6) {
            return false;
        }
        return true;
    }

}
