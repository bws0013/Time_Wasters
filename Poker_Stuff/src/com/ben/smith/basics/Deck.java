package com.ben.smith.basics;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    // Print each card
    public void print_deck() {
        for(Card c : deck) {
            c.print_card();
        }
    }

    public Card get(int index) {
        return deck[index];
    }

    // Get cards inclusive start, exclusive end
    public Card[] get(int index_start, int index_end) {
        Card[] cards = new Card[index_end - index_start];
        for(int i = index_start; i < index_end; i++) {
            cards[i - index_start] = deck[i];
        }
        return cards;
    }

    public int size() {
        return deck.length;
    }

    // Shuffle a deck, this is currently used for randomly getting cards, later on I will just generate values
    public void shuffle() {
        List<Card> temp_deck = Arrays.asList(deck);
        Collections.shuffle(temp_deck);
        deck = temp_deck.toArray(deck);
    }

}
