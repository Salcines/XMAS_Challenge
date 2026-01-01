package ui;
import tasks.*;

import java.io.IOException;
import java.util.Scanner;

public class SelectionMenu {
    private static final Scanner input = new Scanner(System.in);
    public static void displayMainMenu() throws IOException {
        while (true) {
            System.out.println("\n Twelve days of coding 🎄❄️\n");
            System.out.println(" 1️⃣ Task \"On the first day of coding...\"");
            System.out.println(" 2️⃣ Task \"On the second day of coding...\"");
            System.out.println(" 3️⃣ Task \"On the third day of coding...\"");
            System.out.println(" 4️⃣ Task \"On the fourth day of coding...\"");
            System.out.println(" 6️⃣ Task \"On the sixth day of coding...\"");
            System.out.println(" 7️⃣ Task \"On the seventh day of codign..\"");
            System.out.println(" 0️⃣ Exit program.");
            System.out.print(" Select an option: ");

            int option = input.nextInt();

            switch (option) {
                case 1:
                    OneNastyBug.filterSecondError();
                    break;
                case 2:
                    TwoPointers.findSweetness();
                    break;
                case 3:
                    ThreeSecurityRules.scoreSecurityPassword();
                    break;
                case 4:
                    FourElvesDinner.AnalyzeLogContentions();
                    break;
                case 6:
                    SixHandSakes.findFarthestBeing();
                    break;
                case 7:
                    SevenBridges.calculateMinimumBridge();
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Unknow option!");
                    break;
            }
        }
    }
}
