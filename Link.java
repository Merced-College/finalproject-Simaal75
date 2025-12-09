//Simaal B
//CPSC 39 - Final Project
//Fall 2025

// A Link is one node inside the LinkedList deck.
// It stores a Card object and a pointer to the next Link.
public class Link {
    public Card data;
    public Link next;

    public Link(Card c) {
        data = c;
    }

    // Displays the card stored inside this Link
    public void display() {
        System.out.println(data);
    }
}
