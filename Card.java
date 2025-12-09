//Simaal B
//CPSC 39 - Final Project
//Fall 2025

// This class represents a single playing card.
// Each card stores a suit, a name, and a blackjack value.
public class Card {

    private String suit;
    private String name;
    private int value;

    // Constructor for creating a new card
    public Card(String s, String n, int v) {
        suit = s;
        name = n;
        value = v;
    }

    // Returns the blackjack value of this card
    public int getValue() {
        return value;
    }

    // Makes the card print nicely when shown in the terminal
    public String toString() {
        return name + " of " + suit + " (" + value + ")";
    }
}
