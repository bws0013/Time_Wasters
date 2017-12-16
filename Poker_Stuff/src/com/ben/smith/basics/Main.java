package com.ben.smith.basics;

/**
 * Created by bensmith on 12/15/17.
 */
public class Main {


    public static void main(String[] args) {
        Deck d = new Deck();
        System.out.println(d.size());
        d.print_deck();
        System.out.println(d.size());
        d.shuffle();
        d.print_deck();
        System.out.println(d.size());
        d.shuffle();
        d.print_deck();
    }


}
