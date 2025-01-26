import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Set<String> flavors = new HashSet<>();
        flavors.add("Chocolate");
        flavors.add("Vanilla");
        flavors.add("Strawberry");
        flavors.add("Mint Chip");
        flavors.add("Cookie Dough");
        flavors.add("Superman");

        List<String> names = new ArrayList<>();
        names.add("Rebeccah");
        names.add("Austin");
        names.add("Oscar");
        names.add("Sue");
        names.add("Phil");

        Map<String, Integer> votes = new HashMap<>();

        Map<String, String> displayFlavors = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        initializeVotes(flavors, votes, displayFlavors);

        displayFlavorOptions(flavors);

        boolean allVotesValid = false;

        while (!allVotesValid) {
            allVotesValid = true;

            for (String name : names) {
                String userVote = collectVote(name, flavors, scanner);

                if (userVote != null) {
                    String lowercaseVote = userVote.toLowerCase();
                    votes.put(lowercaseVote, votes.getOrDefault(lowercaseVote, 0) + 1);
                    System.out.println("Thank you for your vote, " + name + "!");
                } else {
                    allVotesValid = false;
                }
            }
        }
        displayWinner(votes, displayFlavors);
        scanner.close();
    }

    private static void initializeVotes(Set<String> flavors, Map<String, Integer> votes,
            Map<String, String> displayFlavors) {
        for (String flavor : flavors) {
            String lowercaseFlavor = flavor.toLowerCase();
            votes.put(lowercaseFlavor, 0);
            displayFlavors.put(lowercaseFlavor, flavor); // store the original flavor name for display purposes
        }
    }

    private static void displayFlavorOptions(Set<String> flavors) {
        System.out.println("Let's vote on ice cream flavors. Here are your options:");
        for (String flavor : flavors) {
            System.out.println(flavor);
        }
    }

    private static String collectVote(String name, Set<String> flavors, Scanner scanner) {
        String userVote = "";
        boolean validVote = false;

        while (!validVote) {
            System.out.println("Which flavor do you want to vote for, " + name + "?");
            userVote = scanner.nextLine().trim();

            if (isValidFlavor(userVote, flavors)) {
                validVote = true;
            } else {
                System.out.println("That is not a valid flavor. Please try again.");
            }
        }
        return userVote;
    }

    private static boolean isValidFlavor(String userVote, Set<String> flavors) {
        for (String flavor : flavors) {
            if (flavor.equalsIgnoreCase(userVote)) { // string class method that compares two strings ignoring case
                                                     // differences
                return true;
            }
        }
        return false;
    }

    private static void displayWinner(Map<String, Integer> votes, Map<String, String> displayFlavors) {
        int maxVotes = 0;
        for (int voteCount : votes.values()) {
            if (voteCount > maxVotes) {
                maxVotes = voteCount;
            }
        }
        System.out.println("\nThe flavor(s) with the most votes are:");
        for (Map.Entry<String, Integer> entry : votes.entrySet()) { // entry set method returns a set of all the entires
                                                                    // in the map
            if (entry.getValue() == maxVotes) {
                String originalFlavorName = displayFlavors.get(entry.getKey()); // get the original flavor name
                System.out.println(originalFlavorName + ": " + entry.getValue() + " votes");
            }
        }
    }
}

// One of the biggest differences I noticed between Java and C# is the built-in
// string formatting methods. In C#, string formatting can be done using methods
// like Console.WriteLine()
// and interpolation, whereas in Java, you use System.out.println() with
// concatenation. Java also has built-in string formatting methods that I was
// unfamilar with that C# does not
// have, such as "string.equalsIgnoreCase".
