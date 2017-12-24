package com.ben.smith.basics;

/**
 * Created by bensmith on 12/15/17.
 */
public class Card implements Comparable<Card> {

    // 2 3 4 5 6 7 8 9 10 j  q  k  a
    // 2 3 4 5 6 7 8 9 10 11 12 13 14
    // Card value 2-14
    private int value;

    // Suit is 0-3, Heart, Spade, Diamond, Club
    private int suit;

    public Card(int value, int suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card c) {
        if(this.getvalue() > c.getvalue()) {
            return 1;
        } else if(this.getvalue() == c.getvalue()) {
            if(c.getSuit() > this.getvalue()) {
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

    public int getvalue() {
        return value;
    }

    public void setvalue(int value) {
        this.value = value;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }
}
