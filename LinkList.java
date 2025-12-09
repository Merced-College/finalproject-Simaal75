//Simaal B
//CPSC 39 - Final Project
//Fall 2025

import java.util.ArrayList;
import java.util.Random;

// This LinkedList stores the entire blackjack deck.
// I use this because drawing the "top" card is very easy (O(1)).
public class LinkList {

    private Link first;  // first card in the list (top of deck)

    public LinkList() {
        first = null;
    }

    // Inserts a card at the front of the list
    public void insertFirst(Card c) {
        Link newLink = new Link(c);
        newLink.next = first;
        first = newLink;
    }

    // Removes the top card and returns it
    public Card getFirst() {
        if (first == null) return null;
        Card c = first.data;
        first = first.next;
        return c;
    }

    // ----------------------------------------------------------
    // SHUFFLE ALGORITHM (Algorithm #1)
    //
    // Steps:
    // 1. Move all cards from LinkedList → ArrayList
    // 2. Randomly shuffle using swaps (Fisher-Yates style)
    // 3. Rebuild the LinkedList using the new order
    //
    // Big O: O(n)
    // ----------------------------------------------------------
    public void shuffle() {

        ArrayList<Card> list = new ArrayList<>();
        Link cur = first;

        // Move all cards into ArrayList for easier shuffling
        while (cur != null) {
            list.add(cur.data);
            cur = cur.next;
        }

        Random r = new Random();

        // Shuffle by swapping random positions
        for (int i = 0; i < list.size(); i++) {
            int j = r.nextInt(list.size());
            Card temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

        // Rebuild linked list using the shuffled order
        first = null;
        for (Card c : list) {
            insertFirst(c);
        }
    }
}
