package com.ben.smith.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bensmith on 12/30/17.
 */
public class Compare_Task implements Runnable {

    private String name;

    public Compare_Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        Hand h1 = null;
        Hand h2 = null;

        Hand_Priority hp1 = null;
        Hand_Priority hp2 = null;

        Card[] community_cards = null;
        try {

        } catch(ArrayIndexOutOfBoundsException aioobe) {
            return;
        }
    }
}
