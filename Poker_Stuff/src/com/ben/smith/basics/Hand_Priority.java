package com.ben.smith.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by bensmith on 12/16/17.
 */
public class Hand_Priority {

    // A list of every card (including hand) that we have seen
    private List<Card> all_cards = new ArrayList<Card>();

    // The numerical representation of the value of each card
    private int[] numbers = new int[15];
    // A count of the relative suits of all of the cards we have seen
    private int[] suits = new int[4];

    // Pass in your hand and any community cards that have been revealed
    public Hand_Priority(Hand hand, Card[] community) {
        Card[] hand_cards = hand.get_hand();

        add_cards(hand_cards);
        add_cards(community);
    }

    // Add more cards to our list of revealed cards
    public void add_cards(Card[] cards) {
        if(cards == null) {
            return;
        }
        for(Card c : cards) {
            all_cards.add(c);
            numbers[c.getNumber()]++;
            suits[c.getSuit()]++;
            if(c.getNumber() == 14) {
                all_cards.add(new Card(1, c.getSuit()));
                numbers[1]++;
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
                all_cards = original_cards;
                return straight_flush;
            }
        }

        if(four_of_a_kind_check()) {
            all_cards = original_cards;
            return get_pairs();
        } else if(flush != null) {
            all_cards = original_cards;
            return flush;
        } else if(straight != null) {
            all_cards = original_cards;
            return straight;
        } else {
            all_cards = original_cards;
            return get_pairs();
        }
    }

    public boolean four_of_a_kind_check() {
        for(int i = 0; i < numbers.length; i++) {
            if(numbers[i] == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean royal_flush_check(Card[] cards) {
        if(cards[0].getNumber() == 14) { return true; }
        return false;
    }


    public Card[] get_pairs() {

        Card[] final_hand = new Card[5];
        int hand_size = 0;

        Card[] biggest_pair = get_pair();
        Card[] smallest_pair = get_pair();

        if(biggest_pair != null) {
            for(int i = 0; i < biggest_pair.length; i++) {
                final_hand[i] = biggest_pair[i];
                hand_size++;
            }
        }

        if(smallest_pair != null) {
            int j = 0;
            for(int i = 0; i < final_hand.length; i++) {
                if(final_hand[i] == null) {
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

    // To check for royal flush see if cards[0] == 14 (ie the highest number in the straight-flush is an ace)
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
        for(int i = 3; i < numbers.length; i++) {
            if(numbers[i] >= numbers[max_instance_index]) {
                max_instance_index = i;
            }
        }

        // If the max instances of a number is 1 then there are, by definition, no pairs/triples/quads
        if(numbers[max_instance_index] == 1) {
            return null;
        }

        return_cards = new Card[numbers[max_instance_index]];
        numbers[max_instance_index] = 0;

        int index = 0;
        for(int i = all_cards.size() - 1; i >= 0; i--) {
            if(all_cards.get(i).getNumber() == max_instance_index) {
                return_cards[index] = all_cards.get(i);
                index++;
                all_cards.remove(i);
            }
        }

        return return_cards;
    }

    // Get the high card, pass in a number to skip that many cards, ie the second high card would skip 1 card
    public Card get_high_card(int number_of_cards_to_skip) {
        Card return_card = null;
        Card c = all_cards.get(all_cards.size() - 1 - number_of_cards_to_skip);
//        all_cards.remove(all_cards.size() - 1 - number_of_cards_to_skip);
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
            if(suited_card_list.get(i).getNumber() - 1 == suited_card_list.get(i - 1).getNumber()) {
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
        for(int i = 1; i < numbers.length; i++) {
            if(numbers[i] != 0) {
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
                    if(all_cards.get(j).getNumber() == highest_num - i) {
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
            if(c.getNumber() != 1) {
                c.print_card();
            }
        }
        System.out.println();
    }

    // Print the number values of cards from 1 (ace low) to 14 (ace high)
    public void print_numbers() {
        for(int i = 1; i < numbers.length; i++) {
            System.out.println(i + " : " + numbers[i]);
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
