package com.ben.smith.basics;

import java.util.List;

/**
 * Created by bensmith on 12/16/17.
 */
public class Hand_Priority {

    private int score;
    private List<Card> all_cards;

    private int[] numbers = new int[15];
    private int[] suits = new int[4];

    public Hand_Priority(Hand hand, Card[] community) {
        Card[] hand_cards = hand.get_hand();

        add_cards(hand_cards);
        add_cards(community);
    }

    public void add_cards(Card[] cards) {
        for(Card c : cards) {
            all_cards.add(c);
            numbers[c.getNumber()]++;
            suits[c.getSuit()]++;
        }
        if(numbers[14] != 0) {
            numbers[1] = 1;
        }
    }

    public int straight() {
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
        if(longest_seq > 4) {
            highest_num = numbers.length - 1;
        }

        return highest_num;
    }

    public int flush() {

        int flush_index = -1;
        for(int i = 0; i < suits.length; i++) {
            if(suits[i] > 4) {
                flush_index = i;
            }
        }
        if(flush_index == -1) { return -1; }

        Card[] flush_cards = new Card[suits[flush_index]];

        int j = 0;
        for(int i = 0; i < all_cards.size(); i++) {
            if(all_cards.get(i).getSuit() == flush_index) {
                flush_cards[j] = all_cards.get(i);
                j++;
            }
        }

        return 0;
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
