package tasks;

import db.FilesTasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ThreeSecurityRules {
    private static final Scanner input = new Scanner(System.in);
    private static final Pattern SPECIAL_CHARACTERS = Pattern.compile(Pattern.quote("!@#$%^&*"));
    public static void scoreSecurityPassword() throws IOException {
        double betterScore = Double.MIN_VALUE;
        FilesTasks dataset = new FilesTasks();

        BufferedReader data = new BufferedReader(new FileReader(dataset.getTask3Dataset()));

        String betterPassword = "";
        List<String> passwords = data.lines().collect(Collectors.toList());

        passwords.sort(Comparator.comparingInt(String::length).reversed());

        for(String password : passwords) {
            double score = password.length();
            int ocurrences = checkCharacterOcurrences(password);

            score *= checkRules(password);
            score -= ocurrences;

            if (betterScore < score ) {
                betterPassword = password;
                betterScore = score;
            }

        }
        System.out.println("On the third day of coding...\n");
        System.out.printf("\nThe password with the highest security score is: %s%n", betterPassword);
        System.out.print("Press a key to continue...");
        input.nextLine();
        return;
    }

    private static int checkCharacterOcurrences(String password) {
        double limit = 30.0;
        int concurrences = 0;

        Map<Character, Integer> ocurrences = new HashMap<>();

        for (char c : password.toCharArray()) {
            ocurrences.put(c, ocurrences.getOrDefault(c, 0) + 1);
        }

        int repetitions = 0;

        for (Map.Entry<Character, Integer> entry : ocurrences.entrySet()) {
            if (entry.getValue() > repetitions) {
                repetitions = entry.getValue();
            }
        }
        double percentaje = (repetitions * 100.0) / password.length();

        if (percentaje >= limit) {
            concurrences = repetitions;
        }

        return concurrences;
    }

    private static double checkRules(String password) {
        double scoreCalculated = 1.0;

        boolean haveLowercase = password.matches(".*[a-z].*");
        boolean haveUppercase = password.matches(".*[A-Z].*");
        boolean haveDigit = password.matches(".*\\d.*");
        boolean haveCharacters = SPECIAL_CHARACTERS.matcher(password).find();

        if (!haveLowercase) {
            scoreCalculated *= 0.75;
        }
        if (!haveUppercase) {
            scoreCalculated *= 0.75;
        }
        if (!haveDigit) {
            scoreCalculated *= 0.75;
        }
        if (!haveCharacters) {
            scoreCalculated*=0.75;
        }
        return scoreCalculated;
    }
}
