//Simaal B
//CPSC 39 - Final Project
//Fall 2025

import java.util.ArrayList;
import java.util.Random;

public class LinkList {

    private Link first;  

    public LinkList() {
        first = null;
    }

    public void insertFirst(Card c) {
        Link newLink = new Link(c);
        newLink.next = first;
        first = newLink;
    }

    public Card getFirst() {
        if (first == null) return null;
        Card c = first.data;
        first = first.next;
        return c;
    }

    // *** MY CODE STARTS HERE - SHUFFLE I ADDED ***
    public void shuffle() {
        ArrayList<Card> list = new ArrayList<>();
        Link cur = first;

        while (cur != null) {
            list.add(cur.data);
            cur = cur.next;
        }

        Random r = new Random();
        for (int i = 0; i < list.size(); i++) {
            int j = r.nextInt(list.size());
            Card temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

        first = null;
        for (Card c : list) {
            insertFirst(c);
        }
    }
    // *** MY CODE ENDS HERE ***
}
