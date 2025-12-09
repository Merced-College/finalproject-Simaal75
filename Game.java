//Simaal B
//CPSC 39 - Final Project
//Fall 2025

import java.util.HashMap;
import java.util.Scanner;

public class Game {

    static LinkList deck = new LinkList();
    static Player player = new Player();
    static Player dealer = new Player();
    static HashMap<String, Integer> stats = new HashMap<>();

    public static void main(String[] args) {

        stats.put("wins", 0);
        stats.put("losses", 0);

        loadDeck();

        Scanner in = new Scanner(System.in);
        String play = "y";

        while (play.equalsIgnoreCase("y")) {

            deck.shuffle();
            player.reset();
            dealer.reset();

            player.addCard(deck.getFirst());
            player.addCard(deck.getFirst());

            dealer.addCard(deck.getFirst());
            dealer.addCard(deck.getFirst());

            System.out.println("\nYour cards:");
            showPlayerCards();

            System.out.println("\nDealer shows:");
            System.out.println(dealer.hand[0]);

            boolean stop = false;
            while (!stop && getPlayerValue() < 21) {
                System.out.print("Hit or stand? ");
                String c = in.nextLine().trim();
                if (c.equalsIgnoreCase("hit") || c.equalsIgnoreCase("h")) {
                    Card newCard = deck.getFirst();
                    System.out.println("You draw: " + newCard);
                    player.addCard(newCard);
                } else {
                    stop = true;
                }
            }

            while (getDealerValue() < 16) {
                dealer.addCard(deck.getFirst());
            }

            System.out.println("\nDealer hand:");
            showDealerCards();

            int p = getPlayerValue();
            int d = getDealerValue();

            if (p > 21) {
                System.out.println("You bust.");
                stats.put("losses", stats.get("losses") + 1);
            } else if (d > 21 || p > d) {
                System.out.println("You win!");
                stats.put("wins", stats.get("wins") + 1);
            } else if (d > p) {
                System.out.println("Dealer wins.");
                stats.put("losses", stats.get("losses") + 1);
            } else {
                System.out.println("Tie.");
            }

            System.out.println("\nWins: " + stats.get("wins") + "   Losses: " + stats.get("losses"));

            System.out.print("\nPlay again? (y/n): ");
            play = in.nextLine().trim().toLowerCase();

            if (!play.equals("y")) {
                System.out.println("\nThanks for playing!");
                break;
            }
        }
    }

    public static void loadDeck() {
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("cards.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                deck.insertFirst(new Card(p[0].trim(), p[1].trim(), Integer.parseInt(p[2].trim())));
            }
        } catch (Exception e) {
            System.out.println("Error loading deck.");
        }
    }

    public static int getPlayerValue() {
        return getValueRecursive(player.hand, player.count, 0);
    }

    public static int getDealerValue() {
        return getValueRecursive(dealer.hand, dealer.count, 0);
    }

    public static int getValueRecursive(Card[] arr, int size, int index) {
        if (index == size) return 0;
        return arr[index].getValue() + getValueRecursive(arr, size, index + 1);
    }

    public static void showPlayerCards() {
        for (int i = 0; i < player.count; i++) {
            System.out.println(player.hand[i]);
        }
    }

    public static void showDealerCards() {
        for (int i = 0; i < dealer.count; i++) {
            System.out.println(dealer.hand[i]);
        }
    }
}
