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

    // To check for royal flush see if cards[0] == 14 (ie the highest number in the straight-flush is an ace)
    public Card[] test_straight_flush() {
        Card[] cards = null;
        if(straight() != null && flush() != null) {
            cards = straight(straight());
        }
        return cards;
    }

    // Get at least a pair, at most 4 of a kind
    public Card[] get_pair(int number_of_pairs_to_skip) {
        Card[] return_cards = null;


        int most_instances_index = 1;
        for(int i = 3; i < numbers.length; i++) {
            if(numbers[i] > numbers[most_instances_index]) {
                most_instances_index = i;
            }
        }

        if(number_of_pairs_to_skip == 0 && most_instances_index != 1) {
            return_cards = new Card[numbers[most_instances_index]];
            int i = 0;
            for(Card c : all_cards) {
                if(c.getNumber() == most_instances_index) {
                    return_cards[i] = c;
                    i++;
                }
            }
            return return_cards;
        }

        int number_to_get = -1; // Equal to the index of the number ie 2 -> 2, 7 -> 7
        for(int i = numbers.length - 1; i >= 2; i--) {
            if(numbers[i] > 1) {
                number_of_pairs_to_skip--;
                if(number_of_pairs_to_skip <= 0) {
                    number_to_get = i;
                }
            }
        }

        if(number_to_get > 1) {
            return_cards = new Card[numbers[number_to_get]];
            int i = 0;
            for(Card c : all_cards) {
                if(c.getNumber() == number_to_get) {
                    return_cards[i] = c;
                    i++;
                }
            }
        }

        return return_cards;
    }

    // Get the high card, pass in a number to skip that many cards, ie the second high card would skip 1 card
    public Card get_high_card(int number_of_cards_to_skip) {
        Card return_card = null;
        Collections.sort(all_cards);
        return all_cards.get(all_cards.size() - 1 - number_of_cards_to_skip);
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
}
