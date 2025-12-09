//Simaal B
//CPSC 39 - Final Project
//Fall 2025

import java.util.HashMap;
import java.util.Scanner;

// This class runs the entire blackjack game.
// It uses multiple data structures, several algorithms, and handles user interaction.
public class Game {

    // LinkedList deck (Data Structure #1)
    static LinkList deck = new LinkList();

    // Players store cards in an array (Data Structure #2)
    static Player player = new Player();
    static Player dealer = new Player();

    // HashMap to track wins and losses (Data Structure #3)
    static HashMap<String, Integer> stats = new HashMap<>();

    public static void main(String[] args) {

        // initialize win/loss stats
        stats.put("wins", 0);
        stats.put("losses", 0);

        // load card deck from text file
        loadDeck();

        Scanner in = new Scanner(System.in);

        // Feature #1: Ask for the player's name
        System.out.print("Enter your name: ");
        String name = in.nextLine().trim();

        // Feature #2: betting system — player starts with $100
        int balance = 100;

        String play = "y";

        // Main game loop — continues until user enters "n"
        while (play.equalsIgnoreCase("y")) {

            deck.shuffle();    // shuffle before each round
            player.reset();
            dealer.reset();

            System.out.println("\n-------------------------------------");
            System.out.println("Balance: $" + balance);
            System.out.print("Place your bet: $");
            int bet = Integer.parseInt(in.nextLine());
            System.out.println("-------------------------------------\n");

            // deal two cards each
            player.addCard(deck.getFirst());
            player.addCard(deck.getFirst());

            dealer.addCard(deck.getFirst());
            dealer.addCard(deck.getFirst());

            System.out.println("Your cards:");
            showPlayerCards();
            System.out.println("Total: " + getHandValue(player.hand, player.count));

            System.out.println("\nDealer shows:");
            System.out.println(dealer.hand[0]);

            // ---------------------------------------------
            // PLAYER TURN: choose hit or stand
            // ---------------------------------------------
            boolean stop = false;
            while (!stop && getHandValue(player.hand, player.count) < 21) {
                System.out.print("\nHit or stand? ");
                String c = in.nextLine().trim();
                if (c.equalsIgnoreCase("hit") || c.equalsIgnoreCase("h")) {
                    Card newCard = deck.getFirst();
                    System.out.println("You draw: " + newCard);
                    player.addCard(newCard);
                    System.out.println("Total: " + getHandValue(player.hand, player.count));
                } else {
                    stop = true;
                }
            }

            // Dealer hits until at least 16
            while (getHandValue(dealer.hand, dealer.count) < 16) {
                dealer.addCard(deck.getFirst());
            }

            System.out.println("\nDealer hand:");
            showDealerCards();

            int p = getHandValue(player.hand, player.count);
            int d = getHandValue(dealer.hand, dealer.count);

            System.out.println("\nYour total: " + p);
            System.out.println("Dealer total: " + d + "\n");

            // ---------------------------------------------
            // WINNER ALGORITHM (Algorithm #3)
            // ---------------------------------------------
            if (p > 21) {
                System.out.println(name + " busts! Dealer wins.");
                balance -= bet;
                stats.put("losses", stats.get("losses") + 1);
            }
            else if (d > 21) {
                System.out.println("Dealer busts! " + name + " wins!");
                balance += bet;
                stats.put("wins", stats.get("wins") + 1);
            }
            else if (p > d) {
                System.out.println(name + " wins!");
                balance += bet;
                stats.put("wins", stats.get("wins") + 1);
            }
            else if (d > p) {
                System.out.println("Dealer wins.");
                balance -= bet;
                stats.put("losses", stats.get("losses") + 1);
            }
            else {
                System.out.println("It's a tie.");
            }

            System.out.print("\nPlay again? (y/n): ");
            play = in.nextLine().trim().toLowerCase();
        }

        // ---------------------------------------------
        // Feature #3: Final scoreboard after player stops
        // ---------------------------------------------
        System.out.println("\n-------------------------------------");
        System.out.println("FINAL SCOREBOARD");
        System.out.println("-------------------------------------");

        int wins = stats.get("wins");
        int losses = stats.get("losses");
        int rounds = wins + losses;

        System.out.println("Player: " + name);
        System.out.println("Total Rounds: " + rounds);
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);

        if (rounds > 0) {
            double rate = (wins * 100.0) / rounds;
            System.out.println("Win Rate: " + String.format("%.1f", rate) + "%");
        }

        System.out.println("Final Balance: $" + balance);
        System.out.println("\nThanks for playing, " + name + "!");
    }


    // Loads cards from the text file into the LinkedList deck
    public static void loadDeck() {
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("cards.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(",");
                deck.insertFirst(new Card(p[0].trim(), p[1].trim(), Integer.parseInt(p[2].trim())));
            }
        } catch (Exception e) {
            System.out.println("Could not load deck. Make sure cards.txt is next to Game.java");
        }
    }


    // --------------------------------------------------------------
    // ACE HANDLING + TOTALING ALGORITHM (Algorithm #2)
    //
    // Counts Aces as 11, but converts them to 1 if the total busts.
    // Much more accurate blackjack logic.
    //
    // Big O: O(n)
    // --------------------------------------------------------------
    public static int getHandValue(Card[] hand, int size) {
        int total = 0;
        int aces = 0;

        for (int i = 0; i < size; i++) {
            int v = hand[i].getValue();
            if (v == 11) aces++;
            total += v;
        }

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }


    // Prints all cards in player's hand
    public static void showPlayerCards() {
        for (int i = 0; i < player.count; i++) {
            System.out.println("- " + player.hand[i]);
        }
    }

    // Prints all dealer cards
    public static void showDealerCards() {
        for (int i = 0; i < dealer.count; i++) {
            System.out.println("- " + dealer.hand[i]);
        }
    }
}
