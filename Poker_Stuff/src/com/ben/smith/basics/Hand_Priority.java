package com.ben.smith.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by bensmith on 12/16/17.
 */
public class Hand_Priority {

    // The value we will use to compare hands
    public int hand_value = 0;

    // A list of every card (including hand) that we have seen
    private List<Card> all_cards = new ArrayList<Card>();

    // The numerical representation of the value of each card
    private int[] values = new int[15];
    // A count of the relative suits of all of the cards we have seen
    private int[] suits = new int[4];

    // Pass in your hand and any community cards that have been revealed
    public Hand_Priority(Hand hand, Card[] community) {
        Card[] hand_cards = hand.get_hand();

        add_cards(hand_cards);
        add_cards(community);
    }

    public Hand_Priority(Hand hand) {
        Card[] hand_cards = hand.get_hand();

        add_cards(hand_cards);
    }

    // Add more cards to our list of revealed cards
    public void add_cards(Card[] cards) {
        if(cards == null) {
            return;
        }
        for(Card c : cards) {
            all_cards.add(c);
            values[c.getValue()]++;
            suits[c.getSuit()]++;
            if(c.getValue() == 14) {
                all_cards.add(new Card(1, c.getSuit(), c.getNumber()));
                values[1]++;
            }
        }
    }

    public Card[] get_hand() {
        List<Card> original_cards = deep_copy();
        Collections.sort(all_cards);

        Card[] flush = flush();
        Card[] straight = straight();

        Card[] hand_to_return = null;

        if(flush != null && straight != null) {
            Card[] straight_flush = test_straight_flush();
            if(straight_flush != null) {
                hand_value = 9;
                all_cards = original_cards;
                return straight_flush;
            }
        }

        if(four_of_a_kind_check()) {
            hand_to_return = get_pairs();
            hand_value = 8;
        } else if(flush != null) {
            hand_value = 6;
            hand_to_return = flush;
        } else if(straight != null) {
            hand_value = 5;
            hand_to_return = straight;
        } else {
//            hand_value = 1;
            hand_to_return = get_pairs();
        }

        all_cards = original_cards;
        return hand_to_return;
    }

    public boolean four_of_a_kind_check() {
        for(int i = 0; i < values.length; i++) {
            if(values[i] == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean royal_flush_check(Card[] cards) {
        if(cards[0].getValue() == 14) { return true; }
        return false;
    }


    public Card[] get_pairs() {

        Card[] final_hand = new Card[5];
        int hand_size = 0;

        Card[] biggest_pair = get_pair();
        Card[] smallest_pair = get_pair();

        if(biggest_pair == null) {
            hand_value = 1;
        } else if(biggest_pair.length == 2) {
            hand_value = 2;
        } else if(biggest_pair.length == 3) {
            hand_value = 4;
        }

        if(biggest_pair != null && smallest_pair != null) {
            if (biggest_pair.length == 2 && smallest_pair.length == 2) {
                hand_value = 3;
            } else if (biggest_pair.length > 2 && smallest_pair.length >= 2) {
                hand_value = 7;
            }
        }



        if(biggest_pair != null) {
            for(int i = 0; i < biggest_pair.length; i++) {
                final_hand[i] = biggest_pair[i];
                hand_size++;
            }
        }

        if(biggest_pair != null && biggest_pair.length == 4) {
            smallest_pair = null;
        }

        if(smallest_pair != null) {
            int j = 0;
            for(int i = 0; i < final_hand.length; i++) {
                if(final_hand[i] == null && j < smallest_pair.length) {
                    final_hand[i] = smallest_pair[j];
                    j++;
                    hand_size++;
                }
            }
        }

        for(int i = hand_size; i < 5; i++) {
            final_hand[i] = get_high_card(i - hand_size);
        }

        return final_hand;
    }

    // To check for royal flush see if cards[0] == 14 (ie the highest value in the straight-flush is an ace)
    public Card[] test_straight_flush() {
        Card[] cards = null;
        if(straight() != null && flush() != null) {
            cards = straight(straight());
        }
        return cards;
    }

    // Get at least a pair, at most 4 of a kind
    public Card[] get_pair() {
        Card[] return_cards;

        int max_instance_index = 2;
        for(int i = 3; i < values.length; i++) {
            if(values[i] >= values[max_instance_index]) {
                max_instance_index = i;
            }
        }

        // If the max instances of a value is 1 then there are, by definition, no pairs/triples/quads
        if(values[max_instance_index] == 1) {
            return null;
        }

        return_cards = new Card[values[max_instance_index]];
        values[max_instance_index] = 0;

        int index = 0;
        for(int i = all_cards.size() - 1; i >= 0; i--) {
            if(all_cards.get(i).getValue() == max_instance_index) {
                return_cards[index] = all_cards.get(i);
                index++;
                all_cards.remove(i);
            }
        }

        return return_cards;
    }

    // Get the high card, pass in a value to skip that many cards, ie the second high card would skip 1 card
    public Card get_high_card(int value_of_cards_to_skip) {
        Card return_card = null;
//        Collections.sort(all_cards);
        Card c = all_cards.get(all_cards.size() - 1 - value_of_cards_to_skip);
        return c;
    }

    // Accepts a subset of cards, this is mainly used for detecting straight flushes
    // Returns the 5 largest cards that make a straight if there is a possible straight
    public Card[] straight(Card[] suited_cards) {
        List<Card> suited_card_list = Arrays.asList(suited_cards);
        Collections.sort(suited_card_list);

        Card[] straight_cards = null;

        int longest_seq = 1;
        int highest_index = 0;
        for(int i = 1; i < suited_card_list.size(); i++) {
            if(suited_card_list.get(i).getValue() - 1 == suited_card_list.get(i - 1).getValue()) {
                longest_seq++;
            } else {
                longest_seq = 1;
            }
            if(longest_seq > 4) {
                highest_index = i;
            }
        }

        if(highest_index != 0) {
            straight_cards = new Card[5];
            for (int i = 0; i < 5; i++) {
                straight_cards[i] = suited_card_list.get(highest_index - i);
            }
        }

        return straight_cards;
    }

    // Returns the 5 largest cards that make a straight if there is a possible straight
    public Card[] straight() {
        Card[] straight_cards = null;
        int highest_num = -1;
        int longest_seq = 0;
        for(int i = 1; i < values.length; i++) {
            if(values[i] != 0) {
                longest_seq++;
            } else {
                if(longest_seq > 4) {
                    highest_num = i - 1;
                }
                longest_seq = 0;
            }
        }
        if(highest_num > -1) {
            straight_cards = new Card[5];
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < all_cards.size(); j++) {
                    if(all_cards.get(j).getValue() == highest_num - i) {
                        straight_cards[i] = all_cards.get(j);
                        break;
                    }
                }
            }

        }

        return straight_cards;
    }

    // Returns the 5 largest cards that make a flush if there is a possible flush
    public Card[] flush() {

        int flush_index = -1;
        for(int i = 0; i < suits.length; i++) {
            if(suits[i] > 4) {
                flush_index = i;
            }
        }
        if(flush_index == -1) { return null; }

        List<Card> suit_cards = new ArrayList<Card>();

        for(int i = 0; i < all_cards.size(); i++) {
            if(all_cards.get(i).getSuit() == flush_index) {
                suit_cards.add(all_cards.get(i));
            }
        }

        Collections.sort(suit_cards);

        Card[] flush_cards = new Card[5];

        for(int i = 0; i < 5; i++) {
            flush_cards[i] = suit_cards.get(suit_cards.size() - 1 - i);
        }

        return flush_cards;
    }

    // Print all of the cards we can currently see
    public void print_all_cards() {
        System.out.println("\nAll Cards\n-------------------------");
        for(Card c : all_cards) {
            if(c.getValue() != 1) {
                c.print_card();
            }
        }
        System.out.println();
    }

    // Get the numerical value of the hand for comparison
    public int getHand_value() {
        return hand_value;
    }

    // Print the value values of cards from 1 (ace low) to 14 (ace high)
    public void print_values() {
        for(int i = 1; i < values.length; i++) {
            System.out.println(i + " : " + values[i]);
        }
    }

    // Print the suits of all of the cards that have been seen
    public void print_suits() {
        for(int i = 0; i < suits.length; i++) {
            System.out.println(i + " : " + suits[i]);
        }
    }

    // Make a deep copy of all_cards
    public List<Card> deep_copy() {
        List<Card> cards = new ArrayList<Card>();
        for(Card c : all_cards) {
            cards.add(c);
        }
        return cards;
    }
}
