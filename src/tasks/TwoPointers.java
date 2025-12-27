package tasks;
import db.FilesTasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TwoPointers {
    private static final Scanner input = new Scanner(System.in);
    public static void findSweetness() throws IOException {
        FilesTasks dataset = new FilesTasks();
        BufferedReader data = new BufferedReader(new FileReader(dataset.getTask2Dataset()));

        int target = Integer.parseInt(data.readLine().trim());
        String[] parts = data.readLine().split(",");

        int[] sweetness = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            sweetness[i] = Integer.parseInt(parts[i].trim());
        }

        double betterAverage = 0;
        double betterDistance = Integer.MAX_VALUE;

        for (int i = 0; i < sweetness.length -1; i++) {

            int partialTarget = 2 * target - sweetness[i];

            int idx = Arrays.binarySearch(sweetness, i + 1, sweetness.length, partialTarget);

            if (idx < 0) {
                idx = -idx - 1;
            }

            for (int k = idx - 1; k <= idx; k++) {

//        for (int i = 0; i < sweetness.length - 1; i++) {
//            for (int j = i + 1; j < sweetness.length; j++) {

                if (k > i && k < sweetness.length) {
                    double averageExact = (sweetness[i] + sweetness[k]) / 2.0;
                    double distance = Math.abs(averageExact - target);

                    if (distance < betterDistance) {
                        betterDistance = distance;
                        betterAverage = averageExact;
                    }
                }
            }
        }

        int closest = (int) Math.ceil(betterAverage);

        System.out.println("2️⃣ On The Second Day Of Coding...\n");
        System.out.printf("%nThe sweetness average is: %d%n", closest);
        System.out.println("Press enter to continue...");
        input.nextLine();
        return;
    }
}
