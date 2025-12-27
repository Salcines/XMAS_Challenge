package tasks;

import db.FilesTasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OneNastyBug {
    record LogEntry(LocalTime time, String error){}
    private static final Scanner input = new Scanner(System.in);
    private static final LocalTime timeFrameFrom = LocalTime.of(15, 00);
    private static final LocalTime timeFrameTo = LocalTime.of(15, 30);
    public static void filterSecondError() throws IOException {
        FilesTasks dataset = new FilesTasks();
        String log = dataset.getTask1Dataset();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        List<LogEntry> entries = Files.lines(Path.of(log))
                        .map(line -> line.split("\\s+", 2))
                        .map(parts -> new LogEntry(
                                LocalTime.parse(parts[0], formatter),
                                parts[1]
                        ))
                        .toList();

        Map<String, Long> errorCounts = entries.stream()
                        .collect(Collectors.groupingBy(
                                LogEntry::error,
                                Collectors.counting()
                        ));

        String backgroundNoise = errorCounts.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse(null);

        Map<String, Long> intervalCounts = entries.stream()
                        .filter(e -> !e.time().isBefore(timeFrameFrom) && !e.time().isAfter(timeFrameTo))
                        .filter(e -> !e.error().equals(backgroundNoise))
                        .collect(Collectors.groupingBy(
                                LogEntry::error,
                                Collectors.counting()
                        ));

        String mostRepeatError = intervalCounts.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse(null);

        System.out.println("%nTask 1️⃣ On The First Day Of Coding...\n");
        System.out.printf("\n The most repeat error excluded background noise is: %s%n%n", mostRepeatError);
        System.out.print("Press enter to continue...");
        input.nextLine();
        return;
    }
}
