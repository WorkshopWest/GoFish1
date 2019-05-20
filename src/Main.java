// The game of Go Fish using inheritance as strategy.


public class Main {


    public static void main(String[] args) {


        System.out.println("Welcome to Go Fish ! You know the rules.");
        String choosegameType = "a";
        boolean shortGame = true;
        while(!choosegameType.equalsIgnoreCase("y") && !choosegameType.equalsIgnoreCase("n")){
            choosegameType = Tools.getString("Would you like a short game ? y/n");
        }
        switch (choosegameType){
            case "y":
                shortGame = true;
                break;
            case "n":
                shortGame = false;
                break;

        }
        gameEngine goFish = new gameEngine(shortGame);

        goFish.playGame();
        System.out.println("Thank you for playing Go Fish");

    }
}
