package tasks;

import db.FilesTasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TenLittleDrummers {
    private static Scanner input =
            new Scanner(System.in);

    public static void patternSynchronous()
            throws IOException {
        FilesTasks dataset = new FilesTasks();
        List<int[]> patterns = new ArrayList<>();

        BufferedReader data =
                new BufferedReader(new FileReader(
                        dataset.getTask10Dataset()));
        String line = "";

        while ((line = data.readLine()) != null) {
            String[] parts = line.split(",");
            int[] pattern = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                pattern[i] =
                        Integer.parseInt(parts[i].trim());
            }
            patterns.add(pattern);
        }
        int lenghtPattern = 0;
        int commonLength = patterns.get(0).length;

        for (int[] pattern : patterns) {
            for (int len = commonLength; len > 0; len--) {
              for (int start = 0; start <= commonLength - len; start++) {
                    int[] candidate =
                            Arrays.copyOfRange(pattern, start, start + len);

                    if (appearsInAll(patterns, candidate)) {
                        if (len > lenghtPattern) {
                            lenghtPattern = len;
                        }
                    }
                }
            }
        }

        System.out.printf("🔟 On the tenth day of " +
                "coding...%n... Ten little drummers.%n");
        System.out.printf("The longest pattern is: " +
                "%d%n", lenghtPattern);
        System.out.println("Press enter to continue...");
        input.nextLine();
        return;
    }

    private static boolean appearsInAll(List<int[]> patterns,
                                        int[] pattern) {
        for (int[] line : patterns) {
            if (!containsSubarray
                    (line, pattern)) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsSubarray(
            int[] line, int[] pattern) {
        int len = pattern.length;
        for (int i = 0; i <= line.length - len; i++) {
            boolean match = true;
            for (int j = 0; j < len; j++) {
                if (line[i + j] != pattern[j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }
}
