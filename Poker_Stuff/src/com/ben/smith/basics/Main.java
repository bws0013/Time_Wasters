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

        hp.add_cards(d.get(2,4));


//        int val = hp.straight();
//
//        System.out.println(val);
//
        hp.add_cards(d.get(8,12));
//
//        val = hp.straight();
//
//        System.out.println(val);
//
//        hp.print_numbers();

        int c = hp.flush();
    }


}
