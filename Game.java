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

        System.out.print("Enter your name: ");
        String name = in.nextLine().trim();

        int balance = 100;
        String play = "y";

        while (play.equalsIgnoreCase("y")) {

            deck.shuffle();
            player.reset();
            dealer.reset();

            System.out.println("\n-------------------------------------");
            System.out.println("Balance: $" + balance);
            System.out.print("Place your bet: $");
            int bet = Integer.parseInt(in.nextLine());
            System.out.println("-------------------------------------\n");

            player.addCard(deck.getFirst());
            player.addCard(deck.getFirst());

            dealer.addCard(deck.getFirst());
            dealer.addCard(deck.getFirst());

            System.out.println("Your cards:");
            showPlayerCards();
            System.out.println("Total: " + getHandValue(player.hand, player.count));

            System.out.println("\nDealer shows:");
            System.out.println(dealer.hand[0]);

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

            while (getHandValue(dealer.hand, dealer.count) < 16) {
                dealer.addCard(deck.getFirst());
            }

            System.out.println("\nDealer hand:");
            showDealerCards();
            int p = getHandValue(player.hand, player.count);
            int d = getHandValue(dealer.hand, dealer.count);

            System.out.println("\nYour total: " + p);
            System.out.println("Dealer total: " + d + "\n");

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

            if (!play.equals("y")) break;
        }

        System.out.println("\n-------------------------------------");
        System.out.println("FINAL SCOREBOARD");
        System.out.println("-------------------------------------");
        System.out.println("Player: " + name);
        int wins = stats.get("wins");
        int losses = stats.get("losses");
        int total = wins + losses;
        System.out.println("Total Rounds: " + total);
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);

        if (total > 0) {
            double rate = (wins * 100.0) / total;
            System.out.println("Win Rate: " + String.format("%.1f", rate) + "%");
        }

        System.out.println("Final Balance: $" + balance);
        System.out.println("\nThanks for playing, " + name + "!");
    }

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

    public static void showPlayerCards() {
        for (int i = 0; i < player.count; i++) {
            System.out.println("- " + player.hand[i]);
        }
    }

    public static void showDealerCards() {
        for (int i = 0; i < dealer.count; i++) {
            System.out.println("- " + dealer.hand[i]);
        }
    }
}
