// In order to represent a card, we use ints for suit and rank, but decode it when printing out.

public class Card {

    // Class variables, used to decode card objects
    public static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    public static final String[] RANKS = { null, "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    // Instance variables
    int suit;
    int rank;

    // Constructor
    public Card(int suit, int rank){
        this.suit = suit;
        this.rank = rank;
    }

    // toString method is called by print out method.
    public String toString(){
        return RANKS[this.rank];
    }




}
