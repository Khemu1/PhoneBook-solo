package Main;

import java.util.*;

class Methods extends User {
    static String getUserTypeInput(Scanner in, boolean isUser) {
        if (!isUser) {
            System.out.println("To enter the admin mode, please enter 1");
            System.out.println("To enter the User mode, please enter 2");
            return in.next();
        }
        return "";
    }

    static void handleAdminLogin(Scanner in, User admin) {
        boolean valid = false;
        while (!valid) {
            System.out.println("Please enter the admin username: ");
            String adminUsername = in.next();
            System.out.println("Please enter the admin password: ");
            String adminPassword = in.next();

            if (adminUsername.equals(admin.getUserName()) && adminPassword.equals(admin.getPass())) {
                System.out.println("Admin logged in");
                admin.setIsAdmin(true);
                valid = true;
            } else {
                System.out.println(
                        "Invalid username or password. Try again by entering any key or enter 1 to exit.");
                if (in.next().equals("1")) {
                    admin.setIsAdmin(false);
                    valid = true;
                }
            }
        }
    }

    static void handleExitOrInvalidInput(User admin) {
        if (admin.getIsAdmin()) {
            System.out.println("Exiting admin mode.");
            admin.setIsAdmin(false);
        } else {
            System.out.println("Invalid input. Please enter 1 for admin or 2 for user.");
        }
    }

    static void handleUserMode(Scanner in, Contacts obj, User admin) {
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
                if (admin.validPhone(phone)) {
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

    static void displayAdminMenu(Scanner in, Contacts obj, User admin, boolean isAdmin) {
        System.out.println();

        System.out.println("To add a contact, press 1");
        System.out.println("To edit a specific contact, press 2");
        System.out.println("To delete a contact press 3");
        System.out.println("To print a specific contact, press 4");
        System.out.println("To print all contacts, press 5");
        System.out.println("To print the number of contacts, press 6");
        System.out.println("to enter enter validation mode enter 7");
        System.out.println("To exit admin mode, press 8");

        String adminInput = in.next();

        switch (adminInput) {

            case "1" -> {
                Methods.adminAdding(in, obj, admin);
            }
            case "2" -> {
                Methods.adminEditing(in, obj, admin);
            }
            case "3" -> {
                Methods.adminDeleting(in, obj);
            }
            case "4" -> {
                Methods.adminPrinting(in, obj,admin);
            }
            case "5" -> admin.printAll();
            case "6" -> Methods.adminPrintingAmountOfContacts(obj);
            case "7" -> {
                boolean exit1 = false;
                while (!exit1) {
                    System.out.println();
                    System.out.println("To check phone prefixes enter 1");
                    System.out.println("To add phone prefixes enter 2");
                    System.out.println("To remove phone prefixes enter 3");
                    System.out.println("To check phone lengths enter 4");
                    System.out.println("To add phone length enter 5");
                    System.out.println("To remove phone length enter 6");
                    System.out.println("To exit validation mode enter 7");
                    String input = in.next();

                    switch (input) {
                        case "1" -> {
                            Methods.adminPrintingPrefixes(admin);
                        }
                        case "2" -> {
                            Methods.adminAddingPrefixes(in, admin);
                        }
                        case "3" -> {
                            Methods.adminRemovingPrefixes(in, admin);
                        }
                        case "4" -> {
                            Methods.adminPrintingLength(admin);
                        }
                        case "5" -> {
                            Methods.adminAddingLength(in, admin);
                        }
                        case "6" -> {
                            Methods.adminRemovingLength(in, admin);
                        }
                        case "7" -> {
                            System.out.println("Exiting validation mode");
                            exit1 = true;
                        }
                        default -> {
                            System.out.println("please Enter a number between 1-7");
                        }
                    }
                }
            }
            case "8" -> {
                isAdmin = false;
                admin.setIsAdmin(isAdmin);
                System.out.println("User logged out");
            }
            default -> System.out.println("Please enter a number between 1-8");
        }
    }

    static void adminAdding(Scanner in, Contacts obj, User admin) {
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
                if (admin.validPhone(phone)) {
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

    static void adminEditing(Scanner in, Contacts obj, User admin) {
        boolean again = true;
        while (again) {
            if (obj.getContacts().isEmpty()) {
                System.out.println("Nothing here to edit, Exiting....");
                break;
            }
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
                if (admin.validPhone(phone)) {
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

    static void adminDeleting(Scanner in, Contacts obj) {
        System.out.println("Would you like to delete all contacts at once? enter yes otherwise no");
        String isAll = "";
        boolean yes = false;
        boolean validInput2 = false;
        while (!validInput2) {
            isAll = in.next().toLowerCase();
            if (isAll.length() != 2 && !isAll.equals("no") && !isAll.equals("yes")) {
                System.out.println("invalid input, please Enter Yes or No");
            } else if (isAll.equals("no")) {
                validInput2 = true;
                System.out.println("Switching to manual deleting");
            } else if (isAll.equals("yes")) {
                validInput2 = true;
                yes = true;
            }
        }
        boolean again = true;
        while (again) {
            if (!obj.getContacts().isEmpty() && yes) {
                obj.getContacts().clear();
                System.out.println("All contacts have been deleted, Exiting....");
                break;
            }
            if (obj.getContacts().isEmpty() && !yes) {
                System.out.println("No Contacts to delete, Exiting....");
                break;
            } else if (obj.getContacts().isEmpty() && yes) {
                System.out.println("No Contacts to delete, Exiting....");
                break;
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

    static void adminPrinting(Scanner in, Contacts obj, User admin) {
        boolean again = true;

        while (again) {
            if (obj.getContacts().isEmpty()) {
                System.out.println("Nothing to print, Exiting...");
                break; // exit the loop when the array is empty
            }

            System.out.println("Please enter the Contact ID of the contact you want to print");
            boolean validIndex = false;
            int index = 0;

            while (!validIndex) {
                index = in.nextInt() - 1;
                if (obj.validIndex(index)) {
                    validIndex = true;
                }
            }

            admin.printContact(index);

            System.out.println("Would you like to print other Contacts? Enter 'Yes' otherwise enter 'No'");
            String input = "";
            boolean validInput = false;

            while (!validInput) {
                input = in.next().toLowerCase();
                if (input.length() != 2 && !input.equals("no") && !input.equals("yes")) {
                    System.out.println("Invalid input");
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

    static void adminPrintingAmountOfContacts(Contacts obj) {
        System.out.println("You have " + obj.getContacts().size() + " contact" +
                (obj.getContacts().size() > 2 ? "s" : ""));
    }

    static void adminAddingPrefixes(Scanner in, User admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the prefixes you want add .to exit enter q");
        String input = in.next();
        while (!input.equals("q")) {
            inp.add(input);
            input = in.next();
        }
        admin.addPerefixes(inp);
    }

    static void adminRemovingPrefixes(Scanner in, User admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the prefixes you want to delete. to exit enter q");
        String input = in.next();
        while (!input.equals("q")) {
            inp.add(input);
            input = in.next();
        }
        admin.removePrefixes(inp);
    }

    static void adminAddingLength(Scanner in, User admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the lengths you want to add . to exit enter q");
        String input = in.next();
        while (!input.equals("q")) {

            if (Integer.parseInt(input) > 10) {
                inp.add(String.valueOf(input));
            }
            input = in.next();
        }
        admin.addLength(inp);
    }

    static void adminRemovingLength(Scanner in, User admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the lengths you want to delete . to exit enter q");
        String input = in.next();
        while (!input.equals("q")) {
            inp.add(input);
            input = in.next();
        }
        admin.removeLength(inp);
    }

    static void adminPrintingLength(User admin) {
        admin.printLengths();
    }

    static void adminPrintingPrefixes(User admin) {
        admin.printPrefixes();
    }
}
