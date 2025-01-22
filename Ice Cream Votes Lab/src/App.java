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

        Scanner scanner = new Scanner(System.in);

        for (String flavor : flavors) {
            votes.put(flavor, 0);
        }

        System.out.println("Let's vote on ice cream flavors. Here are your options:");
        for (String flavor : flavors) {
            System.out.println(flavor);
        }

        boolean allVotesValid = false;

        while (!allVotesValid) {
            allVotesValid = true;

            for (String name : names) {
                String userVote = "";
                boolean validVote = false;

                while (!validVote) {
                    System.out.println("Which flavor do you want to vote for, " + name + "?");
                    userVote = scanner.nextLine();
                    userVote = userVote.trim();

                    boolean validFlavor = false;
                    for (String flavor : flavors) {
                        if (flavor.equalsIgnoreCase(userVote)) {
                            validFlavor = true;
                            break;
                        }
                    }

                    if (validFlavor) {
                        String normalizedVote = userVote.toLowerCase();
                        for (String flavor : flavors) {
                            if (flavor.equalsIgnoreCase(userVote)) {
                                votes.put(flavor, votes.getOrDefault(flavor, 0) + 1);
                                System.out.println("Thank you for your vote, " + name + "!");
                                validVote = true;
                                break;
                            }
                        }
                    } else {
                        System.out.println("That is not a valid flavor. Please try again.");
                        allVotesValid = false;
                    }
                }
            }
        }
        int maxVotes = 0;
        for (int voteCount : votes.values()) {
            if (voteCount > maxVotes) {
                maxVotes = voteCount;
            }
        }
        System.out.println("\nThe flavor(s) with the most votes are:");
        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
            if (entry.getValue() == maxVotes) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
            }
        }
        scanner.close();
    }
}
