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


    public static void main(String[] args) {


        List<Card[]> newList = Collections.synchronizedList(new ArrayList<Card[]>());

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        for (int i = 0; i < 5; i++) {
//            Deck d = new Deck();
//            Hand h = new Hand(d.get(0), d.get(1));
//            System.out.println(i);
//            Hand_Priority hp = new Hand_Priority(h);
//            Card[] community_cards = d.get_random_cards(h.getCard1(), h.getCard2(), 5);
//            hp.add_cards(community_cards);
//            newList.add(hp.get_hand());
//        }

        for (int i = 0; i <= 5; i++)
        {
            Task task = new Task("Task " + i);
            System.out.println("A new task has been added : " + task.getName());
            executor.execute(task);
        }
        executor.shutdown();


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

    }


}
