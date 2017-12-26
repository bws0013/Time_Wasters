package com.ben.smith.basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Created by bensmith on 12/15/17.
 */
public class Main {

    public static List<Card[]> hand_list = Collections.synchronizedList(new ArrayList<Card[]>());

    public static void main(String[] args) {


//        List<Card[]> newList = Collections.synchronizedList(new ArrayList<Card[]>());

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(16);

//        for (int i = 0; i < 5; i++) {
//            Deck d = new Deck();
//            Hand h = new Hand(d.get(0), d.get(1));
//            System.out.println(i);
//            Hand_Priority hp = new Hand_Priority(h);
//            Card[] community_cards = d.get_random_cards(h.getCard1(), h.getCard2(), 5);
//            hp.add_cards(community_cards);
//            newList.add(hp.get_hand());
//        }

        int number_of_hands_we_get = 100000;

        Card[][] source = new Card[number_of_hands_we_get][];

        AtomicReferenceArray<Card[]> final_hands = new AtomicReferenceArray<Card[]>(source);

        long start = System.currentTimeMillis();

//        for (int i = 0; i < number_of_hands_we_get; i++) {
//            Task task = new Task("Task " + i);
//            executor.execute(task);
//        }

        Deck d = new Deck();
        Hand h = new Hand(d.get(0), d.get(1));

        for (int i = 0; i < number_of_hands_we_get; i++) {
            Task task = new Task("Task " + i);
            executor.execute(task);
        }

        executor.shutdown();

        try {
            while (!executor.awaitTermination(3, TimeUnit.SECONDS)) {}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(get_elapsed_time(start));

//        for(int i = 0; i < 10; i++) {
//            if(final_hands.get(i) == null) {
//                continue;
//            }
//            Card[] cc = final_hands.get(i);
//            for(Card c : cc) {
//                c.print_card();
//            }
//            System.out.println();
//        }

//        for(int i = 0; i < 10; i++) {
//            Future future = executor.submit(() -> {
//                Hand_Priority hp = new Hand_Priority(h);
//                Card[] community_cards = d.get_random_cards(h.getCard1(), h.getCard2(), 5);
//                hp.add_cards(community_cards);
//                newList.add(hp.get_hand());
//            });
//        }

//        while(future.isDone() != false) {}

//        for(int i = 0; i < 5; i++) {
//            future.get(1);
//            for(Card c : hand) {
//                c.print_card();
//            }
//            System.out.println();
//        }

//        for(Card[] hand : hand_list) {
//            for(Card c : hand) {
//                c.print_card();
//            }
//            System.out.println();
//        }
        System.out.println(hand_list.size());

    }


    public static double get_elapsed_time(long start) {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

}
