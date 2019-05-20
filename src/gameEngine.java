// game logic

import java.util.ArrayList;

public class gameEngine {

    //setup, we should really create a player array and define a fisher and an opponent to be used in each round.
    CardCollection playDeck;
    ArrayList<Player> playerList;
    int numberofPlayers;
    int cardsperPlayer;
    int requirementtoWin;


    // Constructor
    public gameEngine(boolean shortGame){
        if(shortGame){
            playDeck = new DeckSmall("DeckSmall");
            ((DeckSmall)playDeck).shuffle(); // casting
            requirementtoWin = 1;
        }
        else {
            playDeck = new Deck("Deck");
            ((Deck) playDeck).shuffle();
            requirementtoWin = 6;
        }

        numberofPlayers = Tools.getInt("How many players ?", 2, 4);

        playerList = new ArrayList<Player>();
        // Add players
        for (int i = 0; i < numberofPlayers; i++) {
            playerList.add(new Player(Tools.getString("Please enter name of player " + (i+1) + " :")));

        }
        System.out.println(playerList);
        cardsperPlayer = Tools.getInt("How many cards per player ?", 5,7);

        // Deal hands to all players
        for (int i = 0; i < playerList.size(); i++) {
            playDeck.moveCards(playerList.get(i).hand, playDeck.cards.size() - 1, cardsperPlayer);
        }


    }
    // Called from Main class
    public void playGame(){
        System.out.println("Welcome to Go Fish ! You know the rules.");
        System.out.println("Winner is :" + roundLoop());

    }

    // The game consists of round of turns, this method returns the winning player.
    public Player roundLoop(){
        boolean gameOver = false;
        while (true) {
            for (int i = 0; i < playerList.size(); i++) {

                gameOver = turnLoop(playerList.get(i)); // Get the fisher for each turn
                if (gameOver) {
                    Player winner = determineWinner();
                    return winner;
                }
            }

        }
    }

    public boolean turnLoop(Player fisher){ // returns true if game is over ( both hands and deck are empty or all cards distributed to books)
        boolean gameOver = false;

        // Inform fisher and display hand
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("Its your turn, " + fisher.name);
        // Need to wrap this in an if statement, because the its possible that the player has no cards in the hand.
        if(fisher.hand.cards.isEmpty()){
            System.out.println("You had no cards in your hand, so you drew following card from deck :");
            System.out.println(goFish(fisher));

        }
        else {
            presenthand(fisher);
            if (fisher.handlebookCheckQuiet()){
                presenthand(fisher);
            }


            //ChooseBait, returns a card
            Card bait = fisher.chooseBait();
            //Choose opponent, it should not be possible to choose yourself
            Player opponent = chooseOpponent(fisher);

            System.out.println("You have choosen a card with rank " + bait.rank + " and the opponent " + opponent);
            Tools.getString("Press a key to continue");
            // Using the choosen bait and choosen opponent, checkforMatch.
            CardCollection matches = opponent.hand.checkforMatch(fisher.hand, bait);

            //Depending on whether matches were found in opponents hand, print or GoFish/print.
            if (!matches.cards.isEmpty()) {
                System.out.println("You received following card(s) from opponent");
                System.out.println();
                matches.printCollection();
            } else {
                System.out.println("No cards received from opponent.");
                System.out.println("You drew following card from deck: ");
                System.out.println(goFish(fisher));
            }
        }


        // Check if any books have been collected -- uses handlebookCheck on player.
        fisher.handlebookCheck();
        //CheckforState, see if game is over.
        gameOver = checkforState();


        return gameOver;
    }

    private void presenthand(Player fisher) {
        System.out.println("This is your hand: ");
        System.out.println();
        fisher.hand.printCollection();
        System.out.println();
    }

    public Player determineWinner(){
        for (int i = 0; i < playerList.size(); i++) {
            if(playerList.get(i).bookCollection.size() > requirementtoWin){
                return playerList.get(i);
            }

        }
        System.out.println("Something went wrong, the game has ended, but no player has more than " + requirementtoWin + " books.");
        return new Player("Gus Hansen");
    }
    private Player chooseOpponent(Player fisher) {
        while (true) {
            String opponentName = Tools.getString("Who would you like to ask for cards ?");
            System.out.println(opponentName);
            if (opponentName.equalsIgnoreCase(fisher.name)) {
                System.out.println("You can't choose yourself as opponent");
            } else {
                for (int i = 0; i < playerList.size(); i++) {
                    if (opponentName.equalsIgnoreCase(playerList.get(i).name)) {
                        return playerList.get(i);
                    }

                }

            }
            System.out.println("No player by that name, try again");
        }

    }
    private Card goFish(Player fisher){
        Card cardCatch = playDeck.moveCard(fisher.hand, playDeck.cards.size() - 1 );
        return cardCatch;
    }
    private boolean checkforState(){
        boolean nocardsleft = false;
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).hand.cards.isEmpty()){
                nocardsleft = true;
            }
            else{
                nocardsleft = false;
            }

        }

        if(playDeck.cards.isEmpty() && nocardsleft){
            return true;
        }
        return false;
    }
}
