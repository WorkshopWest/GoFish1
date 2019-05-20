// For trying out things.

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class Test {



    public static void main(String[] args) {

        // Test form
        /*Form1 form = new Form1();
        JFrame frame = new JFrame("window");
        frame.setContentPane(form.getPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);*/



        Deck deck = new Deck("Deck");
        deck.shuffle();
        Player player1 = new Player("Pokerface");
        Integer handsize = 7;

        // Deal cards to player1
        deck.moveCards(player1.hand, deck.cards.size() - 1, handsize);
        player1.hand.printCollection();
        System.out.println(player1);
        System.out.println();

        // Move another card to players hand and print it out again
        deck.moveCard(player1.hand, deck.cards.size() - 1);
        player1.hand.printCollection();

        // Choose a bait card ?
        Card bait = player1.chooseBait();

        // Test counting
        int[] talliedHand =player1.hand.countRanks();
        for (int i = 0; i < talliedHand.length; i++) {
            System.out.println(talliedHand[i]);
        }

        // checkforBook
        player1.handlebookCheck();

        // call checkforBook from gameEngine, not supposed to do that.
        CardCollection abook = deck.checkforBook(new CardCollection("book"));
        abook.printCollection();





    }



}
