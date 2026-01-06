package tasks;

import db.FilesTasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class NineCatLives {
    private static Scanner input = new Scanner(System.in);
    private static final int SIZE = 20;
    private static final int LIVES = 9;
    private static final int INFINITE = Integer.MAX_VALUE;


    public static void countCatLives() throws IOException {

        FilesTasks dataset = new FilesTasks();

        int [][] warehouseDistrict = readGrid(dataset.getTask9Dataset());
        int livesLost = findPath (warehouseDistrict);



        int livesRemaining = 9;

        System.out.printf("9️⃣ On the ninth day of coding...%n Nine cat lives%n");
        System.out.printf("The number of lives the cat losses are: %d%n", livesLost);
        System.out.println("Press enter to continue...");
        input.nextLine();
        return;
    }

    private static int[][] readGrid(String dataset) throws IOException {
        int [][] district = new int[SIZE][SIZE];

        BufferedReader data = new BufferedReader(new FileReader(dataset));

        for (int i = 0; i < SIZE; i++) {
            String[] values = data.readLine().split(",");
            for (int j = 0; j < SIZE; j++) {
                district[i][j] = Integer.parseInt(values[j]);
            }
        }

        data.close();
        return district;
    }

    private static int findPath(int[][] warehouseDistrict) {
        int [][] path = intializateDistance();

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[0], b[0])
        );

        path[0][0] = 0;
        queue.add(new int[]{0, 0, 0});

        int [][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[1];
            int column = current[2];
            int livesLost = current[0];

            if (livesLost > path [row][column]) { continue; }

            if (row == SIZE - 1 && column == SIZE - 1) {
                return livesLost;
            }

            for (int[] direction : directions) {
                int rowNeighbor = row + direction[0];
                int columnNeighbor= column + direction[1];

                if (insideBounds (rowNeighbor, columnNeighbor)) {
                    int cost = livesLost + warehouseDistrict[rowNeighbor][columnNeighbor];

                    if (cost < path[rowNeighbor][columnNeighbor]) {
                        path[rowNeighbor][columnNeighbor] = cost;
                        queue.add(new int[]{cost, rowNeighbor, columnNeighbor});
                    }
                }
            }
        }

        return -1;
    }

    private static boolean insideBounds(int rowNeighbor, int columnNeighbor) {
        return (rowNeighbor >= 0 && rowNeighbor < SIZE) &&
                (columnNeighbor >= 0 && columnNeighbor < SIZE);
    }

    private static int[][] intializateDistance() {
        int [][] distance = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(distance[i], INFINITE);}

        return distance;
    }
}
