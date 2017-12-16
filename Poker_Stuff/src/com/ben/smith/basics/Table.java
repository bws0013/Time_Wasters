package com.ben.smith.basics;

/**
 * Created by bensmith on 12/16/17.
 */
public class Table {

    private int player_count;

    private Hand my_hand;

    public Table(int player_count, Hand my_hand) {
        this.player_count = player_count;
        this.my_hand = my_hand;
    }

    public int getPlayer_count() {
        return player_count;
    }

    public void setPlayer_count(int player_count) {
        this.player_count = player_count;
    }

    public Hand getMy_hand() {
        return my_hand;
    }

    public void setMy_hand(Hand my_hand) {
        this.my_hand = my_hand;
    }
}
