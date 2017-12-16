package com.ben.smith.basics;

/**
 * Created by bensmith on 12/15/17.
 */
public class Deck {

    private Card[] deck;

    public Deck() {
        Card[] deck = new Card[52];

        for(int i = 0; i < 4; i++) {
            for(int j = 2; j < 15; j++) {
                deck[(i * 13) + (j - 2)] = new Card(j, i);
            }
        }

        this.deck = deck;
    }

    public void print_deck() {
        for(Card c : deck) {
            c.print_card();
        }
    }

    public int size() {
        return deck.length;
    }


}
