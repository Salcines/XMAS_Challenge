package tasks;

import db.FilesTasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EigthQueens {
    private record Position(int row, int column){}
    private static Scanner input = new Scanner(System.in);


    //This exercice deals with the problem of placing eight queens on a chessboard
    // such that no two queens threaten each other. It's a geometrical problem and not a chess simulator
    public static void AttackingQueens() throws IOException {

        FilesTasks dataset = new FilesTasks();
        List<Position> queens = new ArrayList<>();


        BufferedReader data = new BufferedReader(new FileReader(dataset.getTask8Dataset()));

        String line = "";

        while ((line = data.readLine()) != null) {
            String[] parts = line.split(",");
            int row = Integer.parseInt(parts[0]);
            int column = Integer.parseInt(parts[1]);
            queens.add(new Position(row, column));
        }

        data.close();

        int attacks = 0;

        for (int i = 0; i < queens.size(); i++) {
            for (int j = i + 1; j < queens.size(); j++) {
                Position position1 = queens.get(i);
                Position position2 = queens.get(j);

                if (position1.row() == position2.row() ||
                        position1.column() == position2.column() ||
                        Math.abs(position1.column() - position2.column()) ==
                        Math.abs(position1.row - position2.row)) {
                    attacks++;
                }
//                if (queens.get(i).row() == queens.get(j).row()
//                        || queens.get(i).column() == queens.get(j).column()
//                        || Math.abs(queens.get(i).column() - queens.get(j).column()) ==
//                        Math.abs(queens.get(i).row() - queens.get(j).row())) {
//                    attacks++;
//                }
            }
        }

        System.out.printf("8️⃣ On the eigthth day of coding... %nEighth queens%n");
        System.out.printf("The number of queens pair attacking is: %d%n", attacks);
        System.out.print("Press enter to continue...");
        input.nextLine();
        return;

    }
}
