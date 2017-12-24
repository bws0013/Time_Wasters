package com.ben.smith.basics;

/**
 * Created by bensmith on 12/23/17.
 * This class is just in case anything needs to be changed in hand priority, I want to be sure the results
 * can be compared to what we already know if correct.
 */
public class Hand_Tests {

    public static void main(String[] args) {
        boolean result;

        // There is no royal flush test as by definition a royal flush is a straight flush from 10 to 14
        result = straight_flush_test();
        System.out.println("Straight flush:\t" + result);
        result = four_of_a_kind_test();
        System.out.println("4 of a kind:\t" + result);
        result = flush_test();
        System.out.println("Full House:\t\t" + result);
        result = full_house_test();
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
                if(final_hand[i].getvalue() != 7 - i) {
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
                if(final_hand[i].getvalue() == 6) {
                    six_count++;
                }
            }
            if(six_count != 4 || final_hand[4].getvalue() != 7) {
                return false;
            }
            return true;
        }
    }

    // Testing if we get a full house back
    public static boolean full_house_test() {
        Deck d = new Deck();

        Hand h = new Hand(d.get(3), d.get(4));

        Card[] cards = d.get(15, 17);

        Hand_Priority hp = new Hand_Priority(h, cards);

        hp.add_cards(d.get(29,30));
        hp.add_cards(d.get(40,42));

        Card[] final_hand = hp.get_hand();

        if(final_hand == null) {
            return false;
        } else {
            int five_count = 0;
            int four_count = 0;
            for(int i = 0; i < 5; i++) {
                if(final_hand[i].getvalue() == 5) {
                    five_count++;
                } else if(final_hand[i].getvalue() == 4) {
                    four_count++;
                }
            }
            if(five_count != 3 || four_count != 2) {
                return false;
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
                if(!(final_hand[i].getvalue() == 9 - i)) {
                    return false;
                }
            }
            return true;
        }
    }

    // Testing that three of a kind works
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
                if(final_hand[i].getvalue() == 5) {
                    five_count++;
                }
            }
            if(five_count != 3) {
                return false;
            }
            return true;
        }
    }

    // Testing that we can get two pairs
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
                if(final_hand[i].getvalue() == 5) {
                    five_count++;
                } else if(final_hand[i].getvalue() == 12) {
                    twelve_count++;
                }
            }
            if(twelve_count != 2 || five_count != 2) {
                return false;
            }
            return true;
        }
    }

    // Testing that pair works
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
                if(final_hand[i].getvalue() == 4) {
                    four_count++;
                }
            }
            if(four_count != 2) {
                return false;
            }
            return true;
        }
    }

    // Testing that high card works
    public static boolean high_cards_test() {
        Deck d = new Deck();

        Hand h = new Hand(d.get(3), d.get(4));

        Card[] cards = d.get(18, 20);

        Hand_Priority hp = new Hand_Priority(h, cards);

        hp.add_cards(d.get(1,2));
        hp.add_cards(d.get(37,39));

        Card[] final_hand = hp.get_hand();

        if(final_hand[0].getvalue() != 14) {
            return false;
        }
        if(final_hand[1].getvalue() != 13) {
            return false;
        }
        if(final_hand[2].getvalue() != 8) {
            return false;
        }
        if(final_hand[3].getvalue() != 7) {
            return false;
        }
        if(final_hand[4].getvalue() != 6) {
            return false;
        }
        return true;
    }

}
