package com.ben.smith.basics;

/**
 * Created by bensmith on 12/16/17.
 */
public class Hand {

    private Card card1;
    private Card card2;

    public Hand(Card card1, Card card2) {
        this.card1 = card1;
        this.card2 = card2;
    }

    public Card[] get_hand() {
        return new Card[]{card1, card2};
    }

    public void print_hand() {
        System.out.printf("%s, %s\n", card1.get_card_string(), card2.get_card_string());
    }

    public Card getCard1() {
        return card1;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public Card getCard2() {
        return card2;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }
}
