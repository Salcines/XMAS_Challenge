package tasks;

import db.FilesTasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SixHandSakes {
    private static Scanner input = new Scanner(System.in);
    public static void findFarthestBeing() throws IOException {
        FilesTasks dataset = new FilesTasks();

        BufferedReader content = new BufferedReader(new FileReader(dataset.getTask6Dataset()));

        String startName = content.readLine().trim();
        Map<String, List<String>> graph = new HashMap<>();

        String line = "";
        while ((line = content.readLine()) != null) {
            String[] parts = line.split(",");
            String firstName = parts[0].trim();
            String secondName = parts[1].trim();

            graph.computeIfAbsent(firstName, k -> new ArrayList<>()).add(secondName);
            graph.computeIfAbsent(secondName, k -> new ArrayList<>()).add(firstName);
        }

        Map<String, Integer> distances = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(startName);
        distances.put(startName, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDistance = distances.get(current);

            for (String neighbor : graph.getOrDefault(current, Collections.emptyList())) {
                if (!distances.containsKey(neighbor)) { //|| distances.get(neighbor) > currentDistance + 1) {
                    distances.put(neighbor, currentDistance + 1);
                    queue.add(neighbor);
                }
            }
        }

        int maximumDistance = -1;
        String farthest = "";

        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            String name = entry.getKey();
            int distance = entry.getValue();

            if (distance > maximumDistance ||
                    (distance == maximumDistance && name.compareTo(farthest) < 0)) {
                maximumDistance = distance;
                farthest = name;
            }
        }

        System.out.printf("6️⃣ On The Sixth Day Of Coding...%nSix handshakes%n");
        System.out.printf("The farthest being is: %s%n", farthest);
        System.out.println("Press enter to continue...");
        input.nextLine();
        return;
    }
}
