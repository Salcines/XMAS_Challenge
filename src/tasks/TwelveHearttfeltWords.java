package tasks;

import db.FilesTasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TwelveHearttfeltWords {
    private static Scanner input =
            new Scanner(System.in);

    public static void showFarewallMessage() throws IOException {
        FilesTasks dataset = new FilesTasks();

        BufferedReader data = new BufferedReader(new FileReader(dataset.getTask12Dataset()));

        StringBuilder message = new StringBuilder();

        String line = "";

        while ((line = data.readLine()) != null) {
            message.append(line.trim());
        }

        System.out.printf("On the twelfth day of " +
                "coding...%nTwelve heartfelt words%n ");
        System.out.println(message);
        System.out.print("Press enter to continue...");
        input.nextLine();
        return;

    }
}
