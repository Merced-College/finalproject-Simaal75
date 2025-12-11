//Simaal B
//CPSC 39 - Final Project
//Fall 2025

// Stores each player's cards in an array.
public class Player {

    // --------------------------------------------------------------
    // DATA STRUCTURE #2 — ARRAY (Player Hand)
    // Blackjack hands are small, so arrays work well.
    // --------------------------------------------------------------
    public Card[] hand = new Card[10];
    public int count = 0;

    public void addCard(Card c) {
        hand[count] = c;
        count++;
    }

    public void reset() {
        count = 0;
    }
}
