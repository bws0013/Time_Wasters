package com.ben.smith.basics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bensmith on 12/26/17.
 */
public class Hands_Compare {

    public static void main(String[] args) {

        // For this we will use the new hand_value variable in Hand_Priority
        // To compare the hands first compare the hand value
        // If they are equal then iterate over the hand for an instance where card value at index i in hand a
        // is greater than card value at index i in hand b
        // If they are both always equal than the hands are of the same value

    }

    public int compare_n_hands(Hand my_hand, Hand[] other_hands, Card[] community) {

        Hand_Priority my_hand_priority = new Hand_Priority(my_hand, community);
        int my_hand_priority_value = my_hand_priority.getHand_value();

        List<Hand_Priority> hp_list = new ArrayList<>();

        for(int i = 0; i < other_hands.length; i++) {
            Hand_Priority hp = new Hand_Priority(other_hands[i], community);
            if(hp.getHand_value() > my_hand_priority_value) {
                return -1;
            } else if(hp.getHand_value() == my_hand_priority_value) {
                hp_list.add(hp);
            }
        }


        Card[] my_final_hand = my_hand_priority.get_hand();

        // TODO Figure out how tieing is going to work here, currently it just assumes you either lose or win
        for(int i = 0; i < hp_list.size(); i++) {
            Card[] their_final_hand = hp_list.get(i).get_hand();
            for(int j = 0; j < my_final_hand.length; j++) {
                if(my_final_hand[j].getNumber() < their_final_hand[j].getNumber()) {
                    return -1;
                }
            }
        }

        return 1;
    }

    public int compare_two_hands(Hand a, Hand b, Card[] community) {
        Hand_Priority ha = new Hand_Priority(a, community);
        Hand_Priority hb = new Hand_Priority(a, community);

        int ha_value = ha.getHand_value();
        int hb_value = hb.getHand_value();


        // TODO address the fact that it is possible that the return hand is null
        if(ha_value > hb_value) {
            return 1;
        } else if(ha_value < hb_value) {
            return -1;
        } else {
            Card[] hand_a = ha.get_hand();
            Card[] hand_b = hb.get_hand();
            return(compare_final_hand(hand_a, hand_b));
        }

    }

    public int compare_final_hand(Card[] final_hand_a, Card[] final_hand_b) {
        if(final_hand_a == null) {
            return 0;
        }

        for(int i = 0; i < final_hand_a.length; i++) {
            if(final_hand_a[i].getNumber() > final_hand_b[i].getNumber()) {
                return 1;
            } else if(final_hand_a[i].getNumber() > final_hand_b[i].getNumber()) {
                return -1;
            }
        }
        return 0;
    }

    public boolean royal_flush_check() {
        return false;
    }

    public boolean straight_flush_check() {
        return false;
    }

    public boolean four_of_a_kind_check() {
        return false;
    }

    public boolean full_house_check() {
        return false;
    }

    public boolean flush_check() {
        return false;
    }

    public boolean straight_check() {
        return false;
    }

    public boolean three_of_a_kind_check() {
        return false;
    }

    public boolean two_pair_check() {
        return false;
    }

    public boolean pair() {
        return false;
    }

    public boolean high_card_check() {
        return false;
    }

}
