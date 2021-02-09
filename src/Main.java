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

	public static void printDefault() {
		System.out.println("Your contact list is currently empty. Add some contacts!");
		System.out.println("Choose an option by entering a number:");
		System.out.println("1. Add a new contact.");
		System.out.println("2. Exit.");
	}

	public static void printMenu() {
		System.out.println("Choose an option by entering a number:");
		System.out.println("1. View contacts.");
		System.out.println("2. Add a new contact.");
		System.out.println("3. Search a contact by name.");
		System.out.println("4. Delete an existing contact.");
		System.out.println("5. Exit.");
	}
}
