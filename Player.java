//Simaal B
//CPSC 39 - Final Project
//Fall 2025

public class Player {

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
