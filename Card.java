//Simaal B
//CPSC 39 - Final Project
//Fall 2025

public class Card {

    private String suit;
    private String name;
    private int value;

    public Card(String s, String n, int v) {
        suit = s;
        name = n;
        value = v;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return name + " of " + suit + " (" + value + ")";
    }
}
