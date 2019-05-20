import java.util.Random;

public class DeckSmall extends CardCollection {

    // Constructor
    public DeckSmall(String label){
        super(label);
        // Here we add 12 cards to the collection using a nested forloop.

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) { // we dont want 0 because its defined as null.
                addCard(new Card(i,j));

            }

        }

    }
    // shuffle method should shuffle the cards in a deck collection, in the gofish game only decks need to be shuffled
    public void shuffle(){
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
        swap(random.nextInt(cards.size() ), random.nextInt(cards.size() ));
        }
    }
    public void swap(int i, int j){
        Card card = getCard(i);
        cards.set(i, getCard(j));
        cards.set(j, card);

    }
}
