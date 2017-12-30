package com.ben.smith.basics;

/**
 * Created by bensmith on 12/25/17.
 */
public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        Hand h = null;
        Hand_Priority hp = null;
        Card[] community_cards = null;
        try {
            Deck d = new Deck();
            h = new Hand(d.get(0), d.get(1));
            hp = new Hand_Priority(h);
            community_cards = d.get_random_cards(h.getCard1(), h.getCard2(), 5);
            hp.add_cards(community_cards);
            Main.hand_list.add(hp.get_hand());
        } catch(ArrayIndexOutOfBoundsException aioobe) {
            return;
        }
    }
}
