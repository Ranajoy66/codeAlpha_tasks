import java.io.*;
import java.util.*;

public class ChatHistory {

    private static final String FILE = "chat_history.txt";

    public static void save(String sender, String message) {
        try (FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(sender + ": " + message + "\n");
        } catch (IOException ignored) {}
    }

    public static List<String> load() {
        List<String> history = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                history.add(line);
            }
        } catch (IOException ignored) {}
        return history;
    }
}
