package com.ben.smith.basics;

/**
 * Created by bensmith on 12/25/17.
 */
public class Task implements Runnable {
    private String name;
    Card[] final_hand = null;

    public Task(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Card[] getFinal_hand() {
        return final_hand;
    }

    @Override
    public void run() {
        Deck d = new Deck();
        Hand h = new Hand(d.get(0), d.get(1));
        Hand_Priority hp = new Hand_Priority(h);
        Card[] community_cards = d.get_random_cards(h.getCard1(), h.getCard2(), 5);
        hp.add_cards(community_cards);
        final_hand = hp.get_hand();
        Main.hand_list.add(final_hand);
    }
}
