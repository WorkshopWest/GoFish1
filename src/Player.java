import java.util.ArrayList;

public class Player {

    // Variables
    String name;
    Hand hand = new Hand("Hand");
    // For containing books, i.e. collections of 4 cards of the same rank.
    ArrayList<CardCollection> bookCollection = new ArrayList<>();

    // Constructor
    public Player(String name){
        this.name = name;

    }

    // Player chooses card from hand to use as bait
    public Card chooseBait() {

        while (true) {
            // takes input from player and checks for presence of that card in players hand.
            Card card = getBait();
            for (int i = 0; i < hand.cards.size() ; i++) {
                if (card.rank == hand.cards.get(i).rank) {
                    return card;

                }
            }
            // Tell the player the card was not found in the hand
            System.out.println("You dont have that card in your hand");

        }
    }
    public Card getBait(){
        // Handles player input and parsing/validating, uses the encodeCard tool
        return Tools.encodeCard();

    }

    // Calls checkforBook on hand
    public void handlebookCheck(){
            if (!handlebookCheckQuiet()) {
                System.out.println();
                System.out.println("No books this time.");
            }
    }
    public boolean handlebookCheckQuiet(){
        CardCollection temp = hand.checkforBook(new CardCollection("book"));
        if(!temp.cards.isEmpty()){
            bookCollection.add(temp);
            System.out.println("You collected a book of cards with the rank :" + temp.cards.get(0));
            return true;
        }
        return false;
    }



    // For printing out a player
    public String toString(){
        return name;
    }
}
