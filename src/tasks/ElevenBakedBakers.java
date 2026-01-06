package tasks;

import db.FilesTasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ElevenBakedBakers {
    private static Scanner input =
            new Scanner(System.in);

    public static void calculateTimeActions() throws IOException {

        FilesTasks dataset = new FilesTasks();

        Map<Integer, Integer> duration = new HashMap<>();
        Map<Integer, List<Integer>> dependents =
                new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        List<String[]> lines = new ArrayList<>();

        BufferedReader data =
                new BufferedReader(new FileReader(dataset.getTask11Dataset()));
        String line = "";

        while ((line = data.readLine()) != null) {
            String[] parts = line.split(",");
            int action_Id =
                    Integer.parseInt(parts[0].trim());
            int durationValue =
                    Integer.parseInt(parts[1].trim());

            duration.put(action_Id, durationValue);
            dependents.put(action_Id, new ArrayList<>());
            inDegree.put(action_Id, 0);
            lines.add(parts);
        }
        data.close();

        for (String[] parts : lines) {
            int action_Id =
                    Integer.parseInt(parts[0].trim());
            String dependentsString = parts[2].trim();

            if (!dependentsString.equals("none")) {
                for (String dependent :
                        dependentsString.split(":")) {
                    int dependentId =
                            Integer.parseInt(dependent.trim());
                    dependents.get(dependentId).add(action_Id);
                    inDegree.put(action_Id, inDegree.get(action_Id) + 1);
                }
            }
        }

        Map<Integer, Integer> critical = new HashMap<>();

        for (int action : duration.keySet()) {
            criticalPath(action, duration, dependents,
                    critical);
        }

        PriorityQueue<Integer> availabe =
                new PriorityQueue<>((a, b) -> critical.get(b) - critical.get(a));

        for (int action : duration.keySet()) {
            if (inDegree.get(action) == 0) {
                availabe.add(action);
            }
        }

        PriorityQueue<int[]> running =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        int minutes = 0;
        int freeWorkers = 11;

        while (!availabe.isEmpty() || !running.isEmpty()) {
            while (!availabe.isEmpty() && freeWorkers > 0) {
                int action = availabe.poll();
                freeWorkers--;
                running.add(new int[]{minutes + duration.get(action), action});
            }

            int[] next = running.poll();
            minutes = next[0];
            freeWorkers++;

            for (int dependent :
                    dependents.getOrDefault(next[1], List.of())) {
                inDegree.put(dependent, inDegree.get(dependent) - 1);
                if (inDegree.get(dependent) == 0) {
                    availabe.add(dependent);
                }
            }
        }

        System.out.printf("%n On the eleventh day of " +
                "coding...%n Eleven baked bakers%n");
        System.out.printf("The total time spent on " +
                "actions is: %d minutes%n", minutes);
        System.out.print("Press enter to continue...");
        input.nextLine();
        return;
    }

    private static int criticalPath(int action,
                                  Map<Integer, Integer> duration, Map<Integer, List<Integer>> dependents, Map<Integer, Integer> critical) {
        if (critical.containsKey(action)) {
            return critical.get(action);
        }
        int max = 0;
        for (int dependent : dependents.get(action)) {
            max = Math.max(max, criticalPath(dependent, duration, dependents, critical));
        }

        critical.put(action, max + duration.get(action));
        return critical.get(action) + max;
    }
}
