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
				String firstName = parts[0];
				String lastName = parts[1];
				String number = parts[2];
				this.contactList.add(new Contact(firstName, lastName, number));
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void printContacts() {
		System.out.printf("%-40s | %-40s%n", "Name", "Phone");
		System.out.println("--------------------------------------------------------------------------------");
		for (Contact contact : this.contactList) {
			System.out.printf("%-40s | %-40s%n", contact.getFullName(), contact.getNumber());
		}
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println();
	}

	public void createNewContact() {
		Scanner scanner = new Scanner(System.in);
		label:
		while (true) {
			System.out.println("What is their first name?");
			String newFirstName = scanner.nextLine();
			System.out.println("What is their last name?");
			String newLastName = scanner.nextLine();
			System.out.println("What is their number?");
			String newNumber = scanner.nextLine();
			while (true) {
				System.out.printf("New contact: %s %s, %s%n", newFirstName, newLastName, newNumber);
				System.out.println("1. Confirm adding new contact.");
				System.out.println("2. Cancel adding new contact.");
				String confirm = scanner.nextLine();
				if (confirm.equals("1")) {
					this.contactList.add(new Contact(newFirstName, newLastName, newNumber));
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

	public void searchSelection() {
		Scanner scanner = new Scanner(System.in);
		label:
		while (true) {
			System.out.println("How would you like to search?");
			System.out.println("1. Search by first name.");
			System.out.println("2. Search by last name.");
			System.out.println("3. Search by phone number.");
			System.out.println("4. Exit search menu.");
			String selection = scanner.nextLine();


			switch (selection) {
				case "1":
					firstNameSearch();
					break label;
				case "2":
					lastNameSearch();
					break label;
				case "3":
					numberSearch();
					break label;
				case "4":
					break label;
				default:
					System.out.println("That is not a valid option. Please try again.");
					System.out.println("Press enter to continue.");
					scanner.nextLine();
					break;
			}
		}

	}

	public void firstNameSearch() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the first name to search.");
		String nameToSearch = scanner.nextLine();
		int numberOfContacts = 0;
		ArrayList<Contact> foundContacts = new ArrayList<>();
		for (Contact contact : this.contactList) {
			if (contact.getFirstName().equalsIgnoreCase(nameToSearch)) {
				numberOfContacts++;
				foundContacts.add(contact);
			}
		}
		if (numberOfContacts == 0) {
			System.out.println("No contacts found with that name. Please try again.");
		} else {
			System.out.printf("%d contacts found.%n", numberOfContacts);
			for (Contact contact : foundContacts) {
				System.out.println("Name: " + contact.getFullName());
				System.out.println("Number: " + contact.getNumber());
				System.out.println("---");
			}
		}
		System.out.println("Press enter to continue.");
		scanner.nextLine();
	}

	public void lastNameSearch() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the last name to search.");
		String nameToSearch = scanner.nextLine();
		int numberOfContacts = 0;
		ArrayList<Contact> foundContacts = new ArrayList<>();
		for (Contact contact : this.contactList) {
			if (contact.getLastName().equalsIgnoreCase(nameToSearch)) {
				numberOfContacts++;
				foundContacts.add(contact);
			}
		}
		if (numberOfContacts == 0) {
			System.out.println("No contacts found with that name. Please try again.");
		} else {
			System.out.printf("%d contacts found.%n", numberOfContacts);
			for (Contact contact : foundContacts) {
				System.out.println("Name: " + contact.getFullName());
				System.out.println("Number: " + contact.getNumber());
				System.out.println("---");
			}
		}
		System.out.println("Press enter to continue.");
		scanner.nextLine();
	}

	public void numberSearch() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the number to search.");
		String numberToSearch = scanner.nextLine();
		int numberOfContacts = 0;
		ArrayList<Contact> foundContacts = new ArrayList<>();
		for (Contact contact : this.contactList) {
			if (contact.getNumber().equals(numberToSearch)) {
				numberOfContacts++;
				foundContacts.add(contact);
			}
		}
		if (numberOfContacts == 0) {
			System.out.println("No contacts found.");
		} else {
			System.out.printf("%d contacts found.%n", numberOfContacts);
			for (Contact contact : foundContacts) {
				System.out.println("Name: " + contact.getFullName());
				System.out.println("Number: " + contact.getNumber());
				System.out.println("---");
			}
		}
		System.out.println("Press enter to continue.");
		scanner.nextLine();
	}


	public void deleteContact() {
		Scanner scanner = new Scanner(System.in);
		label:
		while (true) {
			System.out.println("Please select which contact you wish to delete:");
			for (int i = 0; i < contactList.size(); i++) {
				System.out.println(i + ". Name: " + contactList.get(i).getFullName() + ", Number: " + contactList.get(i).getNumber());
				System.out.println("---");
			}
			while (true) {
				try {
					int selectDelete = Integer.parseInt(scanner.nextLine());
					if (selectDelete < 0 || selectDelete >= contactList.size()) {
						System.out.println("That is not a valid selection. Please try again.");
						System.out.println("Press enter to continue.");
						scanner.nextLine();
					} else {
						System.out.println("You have selected this contact: ");
						System.out.println(selectDelete + ". Name: " + contactList.get(selectDelete).getFullName() + ", Number: " + contactList.get(selectDelete).getNumber());
						System.out.println("Are you sure you wish to delete this contact?");
						System.out.println("1. Confirm delete.");
						System.out.println("2. Cancel delete.");
						String confirm = scanner.nextLine();
						if (confirm.equals("1")) {
							this.contactList.remove(contactList.get(selectDelete));
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

	public List<String> makeStringList() {
		List<String> stringList = new ArrayList<>();
		for (Contact contact : this.contactList) {
			String firstName = contact.getFirstName();
			String lastName = contact.getLastName();
			String number = contact.getNumber();
			stringList.add(firstName + "," + lastName + "," + number);
		}
		return stringList;
	}
}