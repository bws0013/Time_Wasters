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


        hp.print_numbers();

        Card[] cc = hp.get_hand_2();

//        Card[] cc = hp.get_pair(0);
        if(cc == null) {
            System.out.println("Its null");
        } else {
            for(Card c : cc) {
                c.print_card();
            }
        }

//        cc = hp.straight(cc);
//
//        if(cc == null) {
//            System.out.println("Its null");
//        } else {
//            for(Card c : cc) {
//                c.print_card();
//            }
//        }
//
//        Card c = hp.get_high_card(0);
//        c.print_card();
//        c = hp.get_high_card(1);
//        c.print_card();
//

//        int val = hp.straight();
//
//        System.out.println(val);
//
        hp.add_cards(d.get(8,12));

        hp.print_all_cards();
//
//        val = hp.straight();
//
//        System.out.println(val);
//
//        hp.print_numbers();

//        int c = hp.flush();
    }


}
