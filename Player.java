//Simaal B
//CPSC 39 - Final Project
//Fall 2025

// This class represents a blackjack player.
// Their hand is stored in an ARRAY (my 2nd data structure).
public class Player {

    public Card[] hand = new Card[10]; // max cards any hand will ever need
    public int count = 0;

    // Adds a card to the player's hand at the next open position
    public void addCard(Card c) {
        hand[count] = c;
        count++;
    }

    // Resets hand for a new round
    public void reset() {
        count = 0;
    }
}
