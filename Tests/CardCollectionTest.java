import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardCollectionTest {

    private Deck testDeck = new Deck("testdeck");

    @Test
    void checkforBook() {

        CardCollection book = testDeck.checkforBook(new CardCollection("book"));

        assertTrue(book.cards.size() == 4);
    }

    @Test
    void countRanks() {
        int[] rankresult = {4,4,4,4,4,4,4,4,4,4,4,4,4};
        assertEquals(rankresult, testDeck.countRanks());

    }
}