import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        String directory = "data";
        String filename = "contacts.txt";

        Path filePath = createDirectoryAndFile(directory, filename);
        ContactList contacts = new ContactList(filePath);

        Scanner scanner = new Scanner(System.in);

        label:
        while (true) {
            if (contacts.getContactList().isEmpty()) {
                while (true) {
                    printDefault();
                    String input = scanner.nextLine();
                    if (input.equals("1")) {
                        contacts.createNewContact();
                        break;
                    } else if (input.equals("2")) {
                        System.out.println("Exiting Contacts Manager.");
                        break label;
                    } else {
                        System.out.println("That is not a valid option. Please try again.");
                        System.out.println("Press enter to continue.");
                        scanner.nextLine();
                    }
                }
            } else {
                label1:
                while (true) {
                    printMenu();
                    String input = scanner.nextLine();

                    switch (input) {
                        case "1":
                            contacts.printContacts();
                            System.out.println("Press enter to continue.");
                            scanner.nextLine();
                            break;
                        case "2":
                            contacts.createNewContact();
                            break;
                        case "3":
                            contacts.searchContacts();
                            break;
                        case "4":
                            contacts.deleteContact();
                            break label1;
                        case "5":
                            System.out.println("Saving your contacts.");
                            List<String> stringList = contacts.makeStringList();
                            saveContacts(filePath, stringList);
                            System.out.println("Exiting Contacts Manager.");
                            break label;
                        default:
                            System.out.println("That is not a valid option. Please try again.");
                            System.out.println("Press enter to continue.");
                            scanner.nextLine();
                            break;
                    }
                }
            }
        }
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

    public static void saveContacts(Path filepath, List<String> stringList) throws IOException {
        Files.write(filepath, stringList);
    }
}
