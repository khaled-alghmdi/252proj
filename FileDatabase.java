import java.io.*;
import java.util.*;

public class FileDatabase {
    private static final String STUDENT_FILE_PATH = "students.txt";
    private static final String COURSE_FILE_PATH = "courses.txt";
    private static final String REGISTRATION_FILE_PATH = "registrations.txt";

    // Singleton instance
    private static FileDatabase instance;

    // Private constructor to prevent instantiation
    private FileDatabase() {}

    // Method to get the single instance
    public static synchronized FileDatabase getInstance() {
        if (instance == null) {
            instance = new FileDatabase();
        }
        return instance;
    }

    public void writeToFile(String filePath, String content, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public List<String> readFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return lines;
    }

    public void overwriteFile(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error overwriting file: " + e.getMessage());
        }
    }
}
