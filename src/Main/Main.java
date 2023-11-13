package Main;

import java.util.*;

import User.User;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Contacts obj = new Contacts();
        Admin admin = new Admin();
        boolean exit = false;
        boolean isAdmin = false;

        System.err.println(
                "Please note that names must only consist of English letters, and your phone number must be Egyptian.");

        while (!exit) {
            switch (getUserTypeInput(in, isAdmin)) {
                case "1" -> {
                    boolean valid = false;
                    while (!valid) {
                        System.out.println("Please enter the admin username: ");
                        String adminUsername = in.next();
                        System.out.println("Please enter the admin password: ");
                        String adminPassword = in.next();

                        if (adminUsername.equals(new Admin().getuserName())
                                && adminPassword.equals(new Admin().getPass())) {
                            System.out.println("Admin logged in");
                            isAdmin = true;
                            valid = true;
                        } else {
                            System.out.println(
                                    "Invalid username or password. Try again by entering any key or enter 1 to exit.");
                            if (in.next().equals("1")) {
                                isAdmin = false;
                                valid = true;
                            }
                        }
                    }
                }
                case "2" -> {
                    handleUserMode(in, obj);
                }
                default -> System.out.println("Invalid input. Please enter 1 for admin or 2 for user.");
            }
            if (isAdmin) {
                System.out.println("To add a contact, press 1");
                System.out.println("To edit a specific contact, press 2");
                System.out.println("To delete a contact press 3");
                System.out.println("To print a specific contact, press 4");
                System.out.println("To print all contacts, press 5");
                System.out.println("To print the number of contacts, press 6");
                System.out.println("to enter edit more for validation enter 7"); // WIP
                System.out.println("To exit admin mode, press 8");

                String adminInput = in.next();

                switch (adminInput) {
                    case "1" -> {
                        adminAdding(in, obj);
                    }
                    case "2" -> {
                        AdminEditing(in, obj);
                    }
                    case "3" -> {
                        AdminDeleting(in, obj);
                    }
                    case "4" -> {
                        AdminPrinting(in, obj);
                    }
                    case "5" -> obj.printAll();
                    case "6" -> AdminPrintingAmountOfContacts(obj);
                    case "7" -> {
                        System.out.println("To check phone prefixes enter 1");
                        System.out.println("To add phone prefixes enter 2");
                        System.out.println("To remove phone prefixes enter 3");
                        String input = in.next();
                        // WIP
                        switch (input) {
                            case "1" -> {
                                admin.printPrefixes();
                            }
                            case "2" -> {
                                adminAddingPrefixes(in, admin);
                            }
                            case "3" -> {
                                adminRemovingPrefixes(in, admin);
                            }
                        }
                    }
                    case "8" -> {
                        isAdmin = false;
                        System.out.println("Admin logged out");
                    }
                    default -> System.out.println("Please enter a number between 1-6");
                }
            }
        }
    }

    private static String getUserTypeInput(Scanner in, boolean isAdmin) {
        if (!isAdmin) {
            System.out.println("To enter the admin mode, please enter 1");
            System.out.println("To enter the User mode, please enter 2");
            return in.next();
        }
        return "";
    }

    private static void handleUserMode(Scanner in, Contacts obj) {
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
                }
            }

            boolean validPhone = false;
            String phone = "";

            while (!validPhone) {
                phone = in.next();
                if (new User().validPhone(phone)) {
                    validPhone = true;

                    if (obj.isDuplicated(phone)) {
                        validPhone = false;
                    }
                }
            }

            obj.addContact(new User(name, phone));

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

    private static void adminAdding(Scanner in, Contacts obj) {
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
                }
            }

            boolean validPhone = false;
            String phone = "";

            while (!validPhone) {
                phone = in.next();
                if (new Admin().validPhone(phone)) {
                    validPhone = true;

                    if (obj.isDuplicated(phone)) {
                        validPhone = false;
                    }
                }
            }

            obj.addContact(new Admin(name, phone));
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

    private static void AdminEditing(Scanner in, Contacts obj) {
        boolean again = true;
        while (again) {
            System.out.println("Please enter the Contact ID of the contact you want to edit");
            boolean validIndex = false;
            int index = 0;

            while (!validIndex) {
                index = in.nextInt() - 1;
                if (obj.validIndex(index)) {
                    validIndex = true;
                }
            }

            boolean validName = false;
            String name = "";

            while (!validName) {
                System.out.println("Please enter the new name or your existing name");
                name = in.next();
                if (Contact.validName(name)) {
                    System.out.println("Name has been updated");
                    validName = true;
                }
            }

            boolean validPhone = false;
            String phone = "";

            while (!validPhone) {
                phone = in.next();
                if (new Admin().validPhone(phone)) {
                    validPhone = true;

                    if (obj.isDuplicated(index, phone)) {
                        validPhone = false;
                    } else {
                        System.out.println("phone number has been updated");
                    }

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

    private static void AdminDeleting(Scanner in, Contacts obj) {
        System.out.println("Would you like to delete all contacts? enter yes otherwise no");
        String isAll = "";
        boolean validInput2 = false;
        while (!validInput2) {
            isAll = in.next().toLowerCase();
            if (isAll.length() != 2 && !isAll.equals("no") && !isAll.equals("yes")) {
                System.out.println("invalid input, please Enter Yes or No");
            } else if (isAll.equals("no")) {
                validInput2 = true;
                System.out.println("Exiting");
            } else if (isAll.equals("yes")) {
                validInput2 = true;
            }
        }
        boolean again = true;
        while (again) {
            if (obj.getContacts().isEmpty()) {
                System.out.println("No contacts to delete exiting");
                again = false;
            } else {
                System.out.println("Please enter the Contact ID of the contact you want to delete");
                boolean validIndex = false;
                int index = 0;

                while (!validIndex) {
                    index = in.nextInt() - 1;
                    if (obj.validIndex(index)) {
                        validIndex = true;
                    }
                }
                obj.deleteContacts(index, isAll);
                System.out
                        .println(
                                "would you like to delete other Contacts ? enter Yes otherwise enter No");
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
    }

    private static void AdminPrinting(Scanner in, Contacts obj) {
        boolean again = true;
        while (again) {
            System.out.println("Please enter the Contact ID of the contact you want to print");
            boolean validIndex = false;
            int index = 0;

            while (!validIndex) {
                index = in.nextInt() - 1;
                if (obj.validIndex(index)) {
                    validIndex = true;
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

    private static void AdminPrintingAmountOfContacts(Contacts obj) {
        System.out.println("You have " + obj.getContacts().size() + " contact" +
                (obj.getContacts().size() > 1 ? "s" : ""));
    }

    private static void adminAddingPrefixes(Scanner in, Admin admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the prefixes you want add .to exit enter q");
        String input = in.next();
        while (!input.equals("q")) {
            inp.add(input);
            input = in.next();
        }
        admin.addPrefixes(inp);
    }

    private static void adminRemovingPrefixes(Scanner in, Admin admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the prefixes you want to delete. to exit enter q");
        String input = in.next();
        while (!input.equals("q")) {
            inp.add(input);
            input = in.next();
        }
        admin.removePrefixes(inp);
    }
}
