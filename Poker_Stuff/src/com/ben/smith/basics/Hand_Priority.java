package com.ben.smith.basics;

/**
 * Created by bensmith on 12/16/17.
 */
public class Hand_Priority {

    private int score;

    private int[] numbers = new int[15];
    private int[] suits = new int[4];

    public Hand_Priority(Hand hand, Card[] community) {
        Card[] hand_cards = hand.get_hand();

        add_cards(hand_cards);
        add_cards(community);
    }

    public void add_cards(Card[] cards) {
        for(Card c : cards) {
            numbers[c.getNumber()]++;
            suits[c.getSuit()]++;
        }
        if(numbers[14] != 0) {
            numbers[1] = 1;
        }
    }

    public int straight() {
        int highest_num = 0;
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
        return highest_num;
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
