package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataLoader {

    public String[] loadData(String pathToFile) {
        File file = new File(pathToFile);
        List<String> dataHolder = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();

                if (!line.isEmpty()) {
                    dataHolder.add(line);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + pathToFile);
        }

        String[] data = new String[dataHolder.size()];
        return dataHolder.toArray(data);
    }

}
