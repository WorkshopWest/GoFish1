// Parent class for collections of cards


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CardCollection {

    //class variables
    //Instance variables
    private String label;
    public ArrayList<Card> cards;
    //Constructor
    public CardCollection (String label){
        this.label = label;
        this.cards = new ArrayList<Card>();
    }
    // Getters and setters

    public void addCard(Card card){

        cards.add(card);
    }

    public Card getCard(int index){

        return cards.get(index);
    }

    // An arraylist has built in get and set methods as well.

    //Methods top layer

     /* Remember that when removing an object from an array during iteration, the elements will shift.
       This method uses the choosen card --bait to find matching cards and move them to the fisher (current players hand)
       Uses moveCard method
       Returned cards can be used for printing. But what if there are more matches..
     */
    public CardCollection checkforMatch(CardCollection that, Card bait){
        CardCollection matchesFound = new CardCollection("Matches");
        for (int i = 0; i < cards.size(); i++) {
            if (getCard(i).rank == bait.rank){ // if a rank match is found we call moveCard. The returned card(s) is used for printing.
                matchesFound.addCard(moveCard(that, i));
                i--; // because elements have shifted.
            }

        }
        return matchesFound;

    }
    // When moving cards we want to call the method on one cardCollection with another collection as parameter.
    public Card moveCard (CardCollection that, int index){
        Card card = pullCard(index);
        that.addCard(card);
        return card;
    }
    // To move several cards, one at a time, for dealing cards.
    public void moveCards (CardCollection that, int index, int howMany){
        for (int i = 0; i < howMany; i++) {
            moveCard(that, index);
            index--;
        }

    }


    public Card getlastCard(){
        int i = cards.size() - 1;
        return this.getCard(i);

    }

    //Methods 2. layer

    public Card pulllastCard(){
        int i = cards.size() - 1;
        return pullCard(i);
    }

    public Card pullCard(int index){
        return cards.remove(index);
    }


    // Other methods


    // Maybe split this up in 2 methods, to enable moving several books.
    public CardCollection checkforBook(CardCollection that){ //checks if there are 4 of the same value using the integer array returned by CountRanks
                                // then moves the cards with that value from the hand to the bookCollection

        int[] talliedRanks = countRanks();
        for (int i = 0; i < talliedRanks.length; i++) {
            if(talliedRanks[i] == 4){
                // Move all cards with rank i+1 to bookCollection/another collection. Use checkforMatch.
                // First convert int back to a card object.
                Card card = new Card(0, i+1);
                checkforMatch(that, card); // are we failing to set that ? Seems to be ok.
                return that;
            }
        }
        return that;
    }


    // Iterates over collection and print outs cards.
    public void printCollection(){
        //System.out.println(label);
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(cards.get(i));

        }
    }
    // prints collection counted
    public void printCounted(){

    }

    // Tally cards in a collection according to rank.
    public int[] countRanks(){
        int[] ranks = {0,0,0,0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < cards.size(); i++) {
            ranks[cards.get(i).rank - 1] ++;
        }
        return ranks;
    }


}
