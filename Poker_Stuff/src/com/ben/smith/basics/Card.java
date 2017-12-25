package com.ben.smith.basics;

/**
 * Created by bensmith on 12/15/17.
 */
public class Card implements Comparable<Card> {

    // Card number from 0-51
    private int number;

    // 2 3 4 5 6 7 8 9 10 j  q  k  a
    // 2 3 4 5 6 7 8 9 10 11 12 13 14
    // Card value 2-14
    private int value;

    // Suit is 0-3, Heart, Spade, Diamond, Club
    private int suit;

    public Card(int value, int suit, int number) {
        this.value = value;
        this.suit = suit;
        this.number = number;
    }

    @Override
    public int compareTo(Card c) {
        if(this.getValue() > c.getValue()) {
            return 1;
        } else if(this.getValue() == c.getValue()) {
            if(c.getSuit() > this.getValue()) {
                return 1;
            }
            return 0;
        } else {
            return -1;
        }
    }

    public void print_card() {
        System.out.printf("[value: %s, Suit: %s]\n", value, suit);
    }

    public String get_card_string() {
        return String.format("[value: %s, Suit: %s]", value, suit);
    }

    public int getNumber() {
        return number;
    }

    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }


}
