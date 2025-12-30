package tasks;

import db.FilesTasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class FourElvesDinner {
    private static Scanner user = new Scanner(System.in);
    public static void AnalyzeLogContentions() throws IOException {
        FilesTasks dataset = new FilesTasks();
        Map<Integer, String> forks = new HashMap<>();
        Map<Integer, Set<String>> forksNeigbors = new HashMap<>();
        forksNeigbors.put(1, Set.of("Jingle", "Sparkle"));
        forksNeigbors.put(2, Set.of("Sparkle", "Tinsel"));
        forksNeigbors.put(3, Set.of("Tinsel", "Holly"));
        forksNeigbors.put(4, Set.of("Holly", "Jingle"));

        for (int i = 0; i < 4; i++) {
            forks.put(i, null);
        }

        int contentions = 0;

        BufferedReader content = new BufferedReader(new FileReader(dataset.getTask4Dataset()));
        String line = "";

    while ((line = content.readLine()) != null) {
        line = line.trim();

        String[] parts = line.split(",");
        if (parts.length != 3) {
            continue;
        }

        String elf = parts[0];
        String action = parts[1];
        int forkId = Integer.parseInt(parts[2]);

        String currentOccupier = forks.get(forkId);
        Set<String> neighbors = forksNeigbors.get(forkId);

        if (action.equals("pick")) {
            if (currentOccupier == null) {
                forks.put(forkId, elf);
        } else if (neighbors.contains(elf) && neighbors.contains(currentOccupier)) {
                contentions++;
            }
        } else if (action.equals("release") && elf.equals(currentOccupier)) {
            forks.put(forkId, null);
        }
    }

    System.out.println("4️⃣ Four Elves Dinner\n");
    System.out.printf("%n The number of contentions is: %d%n", contentions);
    System.out.println("\nPress enter to continue...");
    user.nextLine();
    return;
    }
}
