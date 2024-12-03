import java.util.*;

public class DatabaseProxy implements Database {
    private final FileDatabase fileDatabase;

    public DatabaseProxy() {
        this.fileDatabase = FileDatabase.getInstance();
    }

    @Override
    public void writeToFile(String filePath, String content, boolean append) {
        System.out.println("Proxy: Validating before writing to file...");
        fileDatabase.writeToFile(filePath, content, append);
    }

    @Override
    public List<String> readFromFile(String filePath) {
        System.out.println("Proxy: Checking access before reading file...");
        return fileDatabase.readFromFile(filePath);
    }

    @Override
    public void overwriteFile(String filePath, List<String> lines) {
        System.out.println("Proxy: Verifying before overwriting file...");
        fileDatabase.overwriteFile(filePath, lines);
    }
}
