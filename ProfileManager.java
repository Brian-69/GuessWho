import java.io.*;
import java.util.HashMap;

public class ProfileManager {
    private static final String FILE_NAME = "player_profiles.ser";

    public static HashMap<String, Player> loadProfiles() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (HashMap<String, Player>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing profiles found. A new file will be created.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading profiles: " + e.getMessage());
        }
        return new HashMap<>();
    }

    public static void saveProfiles(HashMap<String, Player> profiles) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(profiles);
        } catch (IOException e) {
            System.err.println("Error saving profiles: " + e.getMessage());
        }
    }
}