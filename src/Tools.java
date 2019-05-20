import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class Tools {

    // Who not make a common scanner
    public static Scanner scanner = new Scanner(System.in);

    // Get string input from players
    public static String getString(String init) {
        System.out.println(init);
        return scanner.nextLine();
    }

    // Get integer input from players
    public static int getInt(String init, int minNumber, int maxNumber) {
        System.out.println(init);
        int intinput = 0;
        int temp = 0;

        while (intinput == 0) {
            try {
                temp = parseInt(scanner.nextLine());
            } catch (Exception e){
                System.out.println("Please enter a numeric value");
            }
            if (temp >= minNumber && temp <= maxNumber) {
                intinput = temp;

            } else // look closer at this, might have to change it.
                System.out.println("The choosen number must be from " + minNumber + " to " + maxNumber + " please.");

        }
        return intinput;
    }

    // Method for getting a card equivalent
    public static Card encodeCard() {
        while (true) {
            String playerInput = getString("Please enter the rank of the card you want to ask for");
            // checks the input against the RANKS array, we just want to know if its a valid rank.
            for (int i = 1; i < Card.RANKS.length; i++) { // start at 1, because index 0 is null.
                // if its valid create a card with equivalent value to return
                if (playerInput.equalsIgnoreCase(Card.RANKS[i])) {
                    Card card = new Card(0, i); // Dont care about the suit in this case, so always 0.
                    return card;
                }

            }
            System.out.println("Its not a valid card rank");

        }
    }
}
