package com.ben.smith.basics;

/**
 * Created by bensmith on 12/15/17.
 */
public class Main {


    public static void main(String[] args) {
        Deck d = new Deck();

        Hand h = new Hand(d.get(0), d.get(1));

        Card[] cards = d.get(12, 13);

        Hand_Priority hp = new Hand_Priority(h, cards);

//        hp.add_cards(d.get(2,4));

        hp.add_cards(d.get(2,4));
        hp.add_cards(d.get(2,3));


        Card[] cc = hp.get_hand();


    }


}
