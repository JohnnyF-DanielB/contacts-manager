import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ContactList {
	private List<Contact> contactList = new ArrayList<>();
	public ContactList(Path contactFilePath) {
		try (Scanner scanner = new Scanner(Paths.get(String.valueOf(contactFilePath)))) {
			while (scanner.hasNextLine()) {
				String row = scanner.nextLine();
				String[] parts = row.split(",");
				String name = parts[0];
				String number = parts[1];
				this.contactList.add(new Contact(name, number));
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	public List<Contact> getContactList() {
		return contactList;
	}
	public void printContacts() {
		System.out.printf("%-15s | %-15s%n", "Name", "Phone");
		System.out.println("---------------------------------");
		for (Contact contact : this.contactList) {
			System.out.printf("%-15s | %-15s%n", contact.getName(), contact.getNumber());
		}
		System.out.println("---------------------------------");
		System.out.println();
	}
	public void createNewContact() {
		Scanner scanner = new Scanner(System.in);
		label:
		while (true) {
			System.out.println("What is their name?");
			String newName = scanner.nextLine();
			System.out.println("What is their number?");
			String newNumber = scanner.nextLine();
			while (true) {
				System.out.printf("New contact: %s, %s%n", newName, newNumber);
				System.out.println("1. Confirm adding new contact.");
				System.out.println("2. Cancel adding new contact.");
				String confirm = scanner.nextLine();
				if (confirm.equals("1")) {
					this.contactList.add(new Contact(newName, newNumber));
					System.out.println("New Contact added!");
					System.out.println("Press enter to continue.");
					scanner.nextLine();
					break label;
				} else if (confirm.equals("2")) {
					System.out.println("Canceled adding new contact.");
					System.out.println("Press enter to continue.");
					scanner.nextLine();
					break label;
				} else {
					System.out.println("That is not a valid option. Please try again.");
					System.out.println("Press enter to continue.");
					scanner.nextLine();
				}
			}
		}
	}
	public void searchContacts() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the name to search.");
		String nameToSearch = scanner.nextLine();
		int numberOfContacts = 0;
		ArrayList<Contact> foundContacts = new ArrayList<>();
		for (Contact contact : this.contactList) {
			if (contact.getName().equalsIgnoreCase(nameToSearch)) {
				numberOfContacts++;
				foundContacts.add(contact);
			}
		}
		if (numberOfContacts == 0) {
			System.out.println("No contacts found.");
		} else {
			System.out.printf("%d contacts found.%n", numberOfContacts);
			for (Contact contact : foundContacts) {
				System.out.println("Name: " + contact.getName());
				System.out.println("Number: " + contact.getNumber());
				System.out.println("---");
			}
		}
		System.out.println("Press enter to continue.");
		scanner.nextLine();
	}
	public void deleteContact() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the name of the contact to delete:");
		String nameToDelete = scanner.nextLine();
		int numberOfContacts = 0;
		ArrayList<Contact> foundContacts = new ArrayList<>();
		for (Contact contact : this.contactList) {
			if (contact.getName().equalsIgnoreCase(nameToDelete)) {
				numberOfContacts++;
				foundContacts.add(contact);
			}
		}
		if (numberOfContacts == 0) {
			System.out.println("No contacts found.");
		} else {
			label:
			while (true) {
				System.out.printf("%d contacts were found with that name.%n", numberOfContacts);
				System.out.println("Please select which contact you wish to delete:");
				for (int i = 0; i < foundContacts.size(); i++) {
					System.out.println(i + ". Name: " + foundContacts.get(i).getName() + ", Number: " + foundContacts.get(i).getNumber());
					System.out.println("---");
				}
				while (true) {
					try {
						int selectDelete = Integer.parseInt(scanner.nextLine());
						if (selectDelete < 0 || selectDelete >= foundContacts.size()) {
							System.out.println("That is not a valid selection. Please try again.");
							System.out.println("Press enter to continue.");
							scanner.nextLine();
						} else {
							System.out.println("You have selected this contact: ");
							System.out.println(selectDelete + ". Name: " + foundContacts.get(selectDelete).getName() + ", Number: " + foundContacts.get(selectDelete).getNumber());
							System.out.println("Are you sure you wish to delete this contact?");
							System.out.println("1. Confirm delete.");
							System.out.println("2. Cancel delete.");
							String confirm = scanner.nextLine();
							if (confirm.equals("1")) {
								this.contactList.remove(foundContacts.get(selectDelete));
								System.out.println("Contact successfully deleted.");
								System.out.println("Press enter to continue.");
								scanner.nextLine();
								break label;
							} else if (confirm.equals("2")) {
								System.out.println("Canceled deleting contact.");
								System.out.println("Press enter to continue.");
								scanner.nextLine();
								break label;
							} else {
								System.out.println("That is not a valid selection. Please try again.");
								System.out.println("Press enter to continue.");
								scanner.nextLine();
								break;
							}
						}
					} catch (NumberFormatException e) {
						System.out.println("That is not a valid selection. Please try again.");
						System.out.println("Press enter to continue.");
						scanner.nextLine();
						break;
					}
				}
			}
		}
	}
	public List<String> makeStringList() {
		List<String> stringList = new ArrayList<>();
		for (Contact contact : this.contactList) {
			String name = contact.getName();
			String number = contact.getNumber();
			stringList.add(name + "," + number);
		}
		return stringList;
	}
}