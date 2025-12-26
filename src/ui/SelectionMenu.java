package ui;

import java.util.Scanner;

public class SelectionMenu {
    private static final Scanner input = new Scanner(System.in);
    public static void displayMainMenu() {
        while (true) {
            System.out.println("\n Twelve days of coding 🎄❄️\n");
            System.out.println(" 1️⃣ Task \"On the first day of coding\"");
            System.out.println(" 0️⃣ Exit program.");
            System.out.print(" Select an option: ");

            int option = input.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Option 1");
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
