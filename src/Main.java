import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        String directory = "data";
        String filename = "contacts.txt";

        Path filePath = createDirectoryAndFile(directory, filename);
        ContactList contacts = new ContactList(filePath);

        Scanner scanner = new Scanner(System.in);
    }

    public static Path createDirectoryAndFile(String dirName, String fileName) throws IOException {

        Path dataDirectoryPath = Paths.get(dirName);
        Path dataFilePath = Paths.get(dirName, fileName);

        if (Files.notExists(dataDirectoryPath)) {
            Files.createDirectories(dataDirectoryPath);
        }

        if (!Files.exists(dataFilePath)) {
            Files.createFile(dataFilePath);
        }

        return dataFilePath;
    }
}
