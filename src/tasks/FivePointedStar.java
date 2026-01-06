package tasks;

import db.FilesTasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class FivePointedStar {
    private static Scanner input = new Scanner(System.in);

    record Point(float x, float y){}

    public static void calculateStarArea() throws IOException {

        FilesTasks dataset = new FilesTasks();

        List<Point> readPoints = new ArrayList<>();
        try (BufferedReader data =
                new BufferedReader(new FileReader(dataset.getTask5Dataset()))) {

           String line = "";

           while ((line = data.readLine()) != null) {
               String[] parts = line.split(",");
               Point point =
                       new Point(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]));
               readPoints.add(point);

           }
       }

        double area = 0.0;

        for (int i = 0; i < readPoints.size(); i++) {
            Point p1 = readPoints.get(i);
            Point p2 = readPoints.get((i + 1) % readPoints.size());

            area += (p1.x * p2.y) - (p2.x * p1.y);
        }

        area = (Math.abs(area) / 2.0);

        System.out.printf("%nOn the fifth day of " +
                "coding...%n Five-Pointed Star%n");

        System.out.printf(Locale.US,"The area of the " +
                "star is: " +
                "%.2f%n", area);

        System.out.println("Press enter to continue...");
        input.nextLine();
        return;
    }
}
