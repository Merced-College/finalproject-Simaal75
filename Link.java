//Simaal B
//CPSC 39 - Final Project
//Fall 2025

public class Link {
    public Card data;
    public Link next;

    public Link(Card c) {
        data = c;
    }

    public void display() {
        System.out.println(data);
    }
}
