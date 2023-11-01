import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Contacts obj = new Contacts();
        boolean exit = false;
        System.err.println("Please note that names must only consist of English letters, and your phone number must be Egyptian.");

        while (!exit) {
            System.out.println("To add Contact, press 1");
            System.out.println("To edit a specific contact, press 2");
            System.out.println("To print a specific contact, press 3");
            System.out.println("To print all contacts, press 4");
            System.out.println("To print the number of contacts press 5");
            System.out.println("To exit, press 6");
            String input = in.next();

            switch (input) {
                case "1" -> {
                    System.out.println("Please Enter your name and your phone number");

                    boolean validName = false;
                    String name = "";

                    while (!validName) {
                        name = in.next();
                        if (Contact.validName(name)) {
                            System.out.println("Name has been added");
                            validName = true;
                        } else {
                            System.err.println("Invalid name. Please enter a valid name.");
                        }
                    }

                    boolean validPhone = false;
                    String phone = "";

                    while (!validPhone) {
                        phone = in.next();
                        if (Contact.validPhone(phone)) {
                            validPhone = true;

                            if (obj.isDuplicated(phone)) {
                                System.err.println("Duplicated Phone number. Please enter another one.");
                                validPhone = false; // Reset the validPhone flag to continue the while loop.
                            }
                        } else {
                            System.err.println("Invalid Phone Number. Please enter a valid one.");
                        }
                    }

                    obj.addContact(name, phone);
                }
                case "2" -> {
                    System.out.println("Please enter the index of the contact you want to edit");
                    boolean validIndex = false;
                    int index = 0;

                    while (!validIndex) {
                        index = in.nextInt();
                        if (obj.validIndex(index)) {
                            validIndex = true;
                        } else {
                            System.err.println("This contact doesn't exist. Please enter a valid index.");
                        }
                    }

                    boolean validName = false;
                    String name = "";

                    while (!validName) {
                        System.out.println("Please enter the new name");
                        name = in.next();
                        if (Contact.validName(name)) {
                            System.out.println("Name has been updated");
                            validName = true;
                        } else {
                            System.err.println("Invalid name. Please enter a valid name.");
                        }
                    }

                    boolean validPhone = false;
                    String phone = "";

                    while (!validPhone) {
                        phone = in.next();
                        if (Contact.validPhone(phone)) {
                            validPhone = true;

                            if (obj.isDuplicated(phone)) {
                                System.err.println("Duplicated Phone number. Please enter another one.");
                                validPhone = false; // Reset the validPhone flag to continue the while loop.
                            }
                        } else {
                            System.err.println("Invalid Phone Number. Please enter a valid one.");
                        }
                    }

                    obj.editContact(index, name, phone);
                }
                case "3" -> {
                    System.out.println("Please enter the index of the contact you want to print");
                    boolean validIndex = false;
                    int index = 0;

                    while (!validIndex) {
                        index = in.nextInt();
                        if (obj.validIndex(index)) {
                            validIndex = true;
                        } else {
                            System.out.println("This contact doesn't exist. Please enter a valid index.");
                        }
                    }

                    obj.printContact(index);
                }
                case "4" -> {
                    obj.printAll();
                }
                case "5"->  {
                    System.out.println("You have " + obj.getContacts().size() + " contact" + (obj.getContacts().size() > 1 ? "s" : ""));
                }
                case "6" -> {
                    exit = true;
                }
                default ->
                        System.out.println("Please enter a number between 1-5");
            }
        }
    }
}
