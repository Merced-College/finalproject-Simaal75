
# 🃏 Blackjack Terminal Game — Final Project Report

### By: Simaal B — CPSC 39 — Fall 2025

---

# 1. Project Abstract

This project is a fully functional, terminal-based Blackjack game written in Java. The game simulates a complete blackjack round between the player and the dealer, including betting, card drawing, hand value calculations, and a final scoreboard. The user can play multiple rounds, track wins and losses, and manage a virtual balance that updates after each game.

This project satisfies all Final Project requirements by using multiple data structures, designing original algorithms, following object-oriented programming, and demonstrating clear program structure and abstraction. The game uses a Linked List to store the deck of cards, Arrays to store player hands, and a HashMap to store the running statistics of the game. I also implemented three different algorithms: a shuffle algorithm, a hand-value algorithm with correct ace handling, and a winner-decision algorithm.

I built this game off concepts taught in class and also expanded it with several new features such as player naming, betting, balance tracking, and a detailed scoreboard. The goal of this project was to make a useful and entertaining game that demonstrates real programming techniques while keeping the gameplay simple and fun.

---

# 2. Data Structures Used

This project uses three data structures, each chosen because it fits its purpose in the game.

---

## 2.1 Linked List — Deck of Cards

**Why I used it:**
A deck naturally fits a singly linked list structure because the game always draws the “top” card. Removing from the front of a Linked List is O(1), which is perfect for gameplay.

**Where it appears:**
`LinkList deck`

**Operations:**

* Insert cards
* Shuffle deck
* Draw top card

**Big-O:**

* Insert: O(1)
* Draw: O(1)
* Shuffle: O(n)

---

## 2.2 Array — Player Hands

**Why I used it:**
Blackjack hands are small and fixed-size, making arrays ideal.

**Where it appears:**
`Card[] hand = new Card[10];`

**Big-O:**

* Add card: O(1)
* Calculate hand value: O(n)

---

## 2.3 HashMap — Wins & Losses

**Why I used it:**
A HashMap makes updating and retrieving stats simple and fast.

**Where it appears:**
`HashMap<String, Integer> stats`

**Big-O:**

* Update: O(1)
* Lookup: O(1)

---

# 3. Algorithms

The project includes three custom algorithms used during gameplay.

---

## 3.1 Shuffle Algorithm (Fisher–Yates Style)

**Purpose:** Randomizes the deck each round.

**Pseudocode:**

```
Move cards from LinkedList → ArrayList
For each index i:
    choose random index j
    swap list[i] and list[j]
Rebuild LinkedList in new order
```

**Big-O:** O(n)

---

## 3.2 Hand Value + Ace Logic

**Purpose:** Calculates blackjack totals accurately.

**Pseudocode:**

```
total = 0
aces = 0

for each card:
    if Ace:
        aces++
    total += card.value

while total > 21 and aces > 0:
    total -= 10
    aces--
```

**Big-O:** O(n)

---

## 3.3 Winner Decision Algorithm

**Purpose:** Determines who wins the round.

**Pseudocode:**

```
if player > 21: dealer wins
else if dealer > 21: player wins
else if player > dealer: player wins
else if dealer > player: dealer wins
else: tie
```

**Big-O:** O(1)

---

# 4. How the Game Works

1. Player enters their name
2. Player starts with $100
3. Deck shuffles
4. Player and dealer both get two cards
5. Player chooses hit or stand
6. Dealer hits until at least 16
7. Winner is determined
8. Balance updates
9. Player may continue or stop
10. Final scoreboard appears

---

# 5. How the Program Meets Requirements

- Uses 3+ data structures
- Includes 3 custom algorithms
- Explains Big-O times
- Includes object-oriented programming
- Contains abstraction and reusable components
- Game works correctly
- Includes original additions (betting, scoreboard, name input)
- Code is well-commented and readable

---

# 6. Issues and Fixes

**Issue 1 — Ace values were wrong**
Aces always counted as 11.
**Fix:** Added logic to convert them to 1 when total > 21.

**Issue 2 — Shuffle was weak**
Deck wasn’t random enough.
**Fix:** Replaced with proper Fisher–Yates style shuffle.

**Issue 3 — Play again loop only accepted “yes”**
Any other input would quit.
**Fix:** Changed to use "y/n" format.

---

# 7. Future Improvements

* Splitting pairs
* Double down
* Better dealer AI
* JavaFX GUI
* Save game stats
* Improved terminal graphics/colors

---

# 8. Conclusion

This Blackjack game demonstrates understanding of Java programming, algorithms, and data structures. I implemented multiple data structures, designed original algorithms, and added extra gameplay features to make the project more polished. The game works reliably, supports multiple rounds, includes a betting system, and uses accurate blackjack logic. This project helped me better understand how to connect data structures with real gameplay and how algorithms impact user experience.

---

