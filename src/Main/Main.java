package Main;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Contacts obj = new Contacts();
        boolean exit = false;
        boolean isAdmin = false;

        System.err.println(
                "Please note that names must only consist of English letters, and your phone number must be Egyptian.");

        while (!exit) {
            if (!isAdmin) {
                System.out.println("To enter the admin mode, please enter 1");
                System.out.println("To enter the User mode, please enter 2");
                String userTypeInput = in.next();

                if (userTypeInput.equals("1")) {
                    System.out.println("Please enter the admin username: ");
                    String adminUsername = in.next();
                    System.out.println("Please enter the admin password: ");
                    String adminPassword = in.next();

                    if (adminUsername.equals(new Admin().getName()) && adminPassword.equals(new Admin().getPass())) {
                        System.out.println("Admin logged in");
                        isAdmin = true;
                    } else {
                        System.out.println("Invalid username or password. Try again or enter any key to exit.");
                        if (!in.next().equals("1")) {
                            exit = true;
                        }
                    }
                } else if (userTypeInput.equals("2")) {
                    boolean again = true;
                    while (again) {
                        System.out.println("please Enter the name and phone number ");
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
                            if (new Contact().validPhone(phone)) {
                                validPhone = true;

                                if (obj.isDuplicated(phone)) {
                                    System.err.println("Duplicated Phone number. Please enter another one.");
                                    validPhone = false;
                                }
                            } else {
                                System.err.println("Invalid Phone Number. Please enter a valid one.");
                            }
                        }

                        obj.addContact(name, phone);
                        System.out.println("would you like to add another contacts ? enter Yes otherwise enter No");
                        String input = "";
                        boolean validInput = false;
                        while (!validInput) {
                            input = in.next().toLowerCase();
                            if (input.length() != 2 && !input.equals("no") && !input.equals("yes")) {
                                System.out.println("invalid input");
                            } else if (input.equals("no")) {
                                validInput = true;
                                again = false;
                                System.out.println("Exiting");
                            } else if (input.equals("yes")) {
                                validInput = true;
                            }
                        }
                    }
                } else {
                    System.out.println("Invalid input. Please enter 1 for admin or 2 for user.");
                }
            } else {
                System.out.println("To add a contact, press 1");
                System.out.println("To edit a specific contact, press 2");
                System.out.println("To delete a contact press 3");
                System.out.println("To print a specific contact, press 4");
                System.out.println("To print all contacts, press 5");
                System.out.println("To print the number of contacts, press 6");
                System.out.println("To exit admin mode, press 7");

                String adminInput = in.next();

                switch (adminInput) {
                    case "1" -> {
                        Boolean again = true;
                        while (again) {
                            System.out.println("please Enter the name and phone number ");
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
                                if (new Contact().validPhone(phone)) {
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
                            System.out.println("would you like to add another contacts ? enter Yes otherwise enter No");
                            String input = "";
                            boolean validInput = false;
                            while (!validInput) {
                                input = in.next().toLowerCase();
                                if (input.length() != 2 && !input.equals("no") && !input.equals("yes")) {
                                    System.out.println("invalid input");
                                } else if (input.equals("no")) {
                                    validInput = true;
                                    again = false;
                                    System.out.println("Exiting");
                                } else if (input.equals("yes")) {
                                    validInput = true;
                                }
                            }
                        }
                    }
                    case "2" -> {
                        boolean again = true;
                        while (again) {
                            System.out.println("Please enter the index of the contact you want to edit");
                            boolean validIndex = false;
                            int index = 0;

                            while (!validIndex) {
                                index = in.nextInt() - 1;
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
                                if (new Contact().validPhone(phone)) {
                                    validPhone = true;

                                    if (obj.isDuplicated(index, phone)) {
                                        System.err.println("Duplicated Phone number. Please enter another one.");
                                        validPhone = false;
                                    }

                                } else {
                                    System.err.println("Invalid Phone Number. Please enter a valid one.");
                                }
                            }

                            obj.editContact(index, name, phone);
                            System.out.println("would you like to edit other Contacts ? enter Yes otherwise enter No");
                            String input = "";
                            boolean validInput = false;
                            while (!validInput) {
                                input = in.next().toLowerCase();
                                if (input.length() != 2 && !input.equals("no") && !input.equals("yes")) {
                                    System.out.println("invalid input");
                                } else if (input.equals("no")) {
                                    validInput = true;
                                    again = false;
                                    System.out.println("Exiting");
                                } else if (input.equals("yes")) {
                                    validInput = true;
                                }
                            }
                        }
                    }
                    case "3" -> {
                        boolean again = true;
                        while (again) {
                            if (new Contacts().getContacts().isEmpty()) {
                                System.out.println("No contacts to delete exiting");
                                again = false;
                            }
                            System.out.println("Please enter the index of the contact you want to delete");
                            boolean validIndex = false;
                            int index = 0;

                            while (!validIndex) {
                                index = in.nextInt() - 1;
                                if (obj.validIndex(index)) {
                                    validIndex = true;
                                } else {
                                    System.out.println("This contact doesn't exist. Please enter a valid index.");
                                }
                            }
                            obj.deleteContact(index);
                            System.out.println("would you like to delete other Contacts ? enter Yes otherwise enter No");
                            String input = "";
                            boolean validInput = false;
                            while (!validInput) {
                                input = in.next().toLowerCase();
                                if (input.length() != 2 && !input.equals("no") && !input.equals("yes")) {
                                    System.out.println("invalid input");
                                } else if (input.equals("no")) {
                                    validInput = true;
                                    again = false;
                                    System.out.println("Exiting");
                                } else if (input.equals("yes")) {
                                    validInput = true;
                                }
                            }
                        }
                    }
                    case "4" -> {
                        boolean again = true;
                        while (again) {
                            System.out.println("Please enter the index of the contact you want to print");
                            boolean validIndex = false;
                            int index = 0;

                            while (!validIndex) {
                                index = in.nextInt() - 1;
                                if (obj.validIndex(index)) {
                                    validIndex = true;
                                } else {
                                    System.out.println("This contact doesn't exist. Please enter a valid index.");
                                }
                            }

                            obj.printContact(index);
                            System.out.println("would you like to print other Contacts ? enter Yes otherwise enter No");
                            String input = "";
                            boolean validInput = false;
                            while (!validInput) {
                                input = in.next().toLowerCase();
                                if (input.length() != 2 && !input.equals("no") && !input.equals("yes")) {
                                    System.out.println("invalid input");
                                } else if (input.equals("no")) {
                                    validInput = true;
                                    again = false;
                                    System.out.println("Exiting");
                                } else if (input.equals("yes")) {
                                    validInput = true;
                                }
                            }
                        }
                    }

                    case "5" -> obj.printAll();
                    case "6" -> System.out.println("You have " + obj.getContacts().size() + " contact" +
                            (obj.getContacts().size() > 1 ? "s" : ""));
                    case "7" -> {
                        isAdmin = false;
                        System.out.println("Admin logged out");
                    }
                    default -> System.out.println("Please enter a number between 1-6");
                }
            }
        }
    }
}
