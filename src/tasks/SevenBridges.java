package tasks;

import db.FilesTasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SevenBridges {
    private static Scanner input = new Scanner(System.in);
    public static void calculateMinimumBridge() throws IOException {
        Map<String, Integer> bridges = new HashMap<>();

        FilesTasks dataset = new FilesTasks();

        BufferedReader data = new BufferedReader(new FileReader(dataset.getTask7Dataset()));
        String line = "";

        while ((line = data.readLine()) != null) {
            String[] parts = line.trim().split(",");

            String landMass1 = parts[0];
            String landMass2 = parts[1];

            bridges.put(landMass1, bridges.getOrDefault(landMass1, 0) + 1);
            bridges.put(landMass2, bridges.getOrDefault(landMass2, 0) + 1);
        }

        data.close();

        int bridgesAditional = 0;
        int odds = 0;

        for (int d : bridges.values()) {
            if (d % 2 != 0) {
                odds++;
            }

            if (odds <= 2) {
                bridgesAditional = 0;
            } else {
                bridgesAditional = (odds /2) - 1;
            }
        }
        long odd = bridges.values().stream()
                .filter(d -> d % 2 != 0)
                .count();

//        System.out.println("\nVértices impares: " + odd);

        System.out.println("\n7️⃣ Seven Bridges of Konigsberg");
        System.out.printf("The number of minimum bridges crossed is: %d%n", bridgesAditional);
        System.out.print("Press enter to continue...");
        input.nextLine();
        return;
    }
}
