import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final int ATTRIBUTE_COUNT = 10;
    private static final int PERSON_COUNT = 10;
    static final int MAX_ATTRIBUTES_PER_PERSON = 3;

    private static final String[] NAMES = {"Jose", "Pedro", "Vicente", "Luis", "Juan", "Ana", "Dulce", "Natalia", "Julieta", "Paty"};
    private static final String[] ATTRIBUTES = {"Tall", "Strong", "Thin", "Blonde", "Smart", "Funny", "Serious", "Kind", "Honest", "Creative"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Player> playerProfiles = ProfileManager.loadProfiles();
        Player currentPlayer;

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Create a new profile");
            System.out.println("2. Load an existing profile");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); //newline character

            if (choice == 1) {
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                currentPlayer = new Player(name);
                playerProfiles.put(name, currentPlayer);
                ProfileManager.saveProfiles(playerProfiles);
                playGame(scanner, currentPlayer, playerProfiles);
            } else if (choice == 2) {
                System.out.print("Enter the name of the profile to load: ");
                String name = scanner.nextLine();
                currentPlayer = playerProfiles.get(name);
                if (currentPlayer == null) {
                    System.out.println("Profile not found.");
                } else {
                    System.out.println("Profile loaded: " + currentPlayer);
                    playGame(scanner, currentPlayer, playerProfiles);
                }
            } else if (choice == 3) {
                System.out.println("Exiting. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void playGame(Scanner scanner, Player currentPlayer, HashMap<String, Player> playerProfiles) {
        
        GuessWhoGame game = new GuessWhoGame(ATTRIBUTE_COUNT, PERSON_COUNT, NAMES, ATTRIBUTES);
        boolean won = game.play(scanner);
        currentPlayer.incrementGamesPlayed();
        if (won) {
            currentPlayer.incrementGamesWon();
        }
        ProfileManager.saveProfiles(playerProfiles);
        System.out.println("Updated profile: " + currentPlayer);
    }
}