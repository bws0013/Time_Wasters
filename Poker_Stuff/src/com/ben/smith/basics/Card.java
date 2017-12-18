package com.ben.smith.basics;

/**
 * Created by bensmith on 12/15/17.
 */
public class Card implements Comparable<Card> {

    // 2 3 4 5 6 7 8 9 10 j  q  k  a
    // 2 3 4 5 6 7 8 9 10 11 12 13 14
    // Card value 2-14
    private int number;

    // Suit is 0-3, Heart, Spade, Diamond, Club
    private int suit;

    public Card(int number, int suit) {
        this.number = number;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card c) {
        if(this.getNumber() > c.getNumber()) {
            return 1;
        } else if(this.getNumber() == c.getNumber()) {
            if(c.getSuit() > this.getNumber()) {
                return 1;
            }
            return 0;
        } else {
            return -1;
        }
    }

    public void print_card() {
        System.out.printf("[Number: %s, Suit: %s]\n", number, suit);
    }

    public String get_card_string() {
        return String.format("[Number: %s, Suit: %s]", number, suit);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }
}
