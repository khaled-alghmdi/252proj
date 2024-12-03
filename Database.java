
import java.util.List;


public interface Database {
    void writeToFile(String filePath, String content, boolean append);
    List<String> readFromFile(String filePath);
    void overwriteFile(String filePath, List<String> lines);
}
