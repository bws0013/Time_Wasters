package com.ben.smith.basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bensmith on 12/16/17.
 */
public class Hand_Priority {

    private int score;
    private List<Card> all_cards = new ArrayList<Card>();

    private int[] numbers = new int[15];
    private int[] suits = new int[4];

    public Hand_Priority(Hand hand, Card[] community) {
        Card[] hand_cards = hand.get_hand();

        add_cards(hand_cards);
        add_cards(community);
    }


    // TODO this and see if we can combine pairs and 3ok into one method.
    public Card[] get_pair(int number_of_pairs_to_skip) {
        Card[] return_cards = null;

        int number_to_get;
        for(int i = numbers.length - 1; i <= 2; i--) {
            if(numbers[i] > 1) {
                number_of_pairs_to_skip--;
            }
            if(number_of_pairs_to_skip == 0) {
                number_to_get = i;
            }
        }

        System.out.println(i);

        return return_cards;
    }

    public Card get_high_card(int number_of_cards_to_skip) {
        Card return_card = null;
        Collections.sort(all_cards);
        return all_cards.get(all_cards.size() - 1 - number_of_cards_to_skip);
    }

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

    public int getScore() {
        return score;
    }

    public void print_numbers() {
        for(int i = 1; i < numbers.length; i++) {
            System.out.println(i + " : " + numbers[i]);
        }
    }

    public void print_suits() {
        for(int i = 0; i < suits.length; i++) {
            System.out.println(i + " : " + suits[i]);
        }
    }
}
