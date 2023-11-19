package Main;

import java.util.*;
import java.io.*;

class Methods extends User {
    static String getUserTypeInput(BufferedReader read, boolean isUser) {
        if (!isUser) {
            System.out.println("To enter the admin mode, please enter 1");
            System.out.println("To enter the User mode, please enter 2");
            return readLine(read);
        }
        return "";
    }

    static void handleAdminLogin(BufferedReader read, User admin) {
        boolean valid = false;
        while (!valid) {
            System.out.println("Please enter the admin username: ");
            String adminUsername = readLine(read);
            System.out.println("Please enter the admin password: ");
            String adminPassword = readLine(read);

            if (adminUsername.equals(admin.getUserName()) && adminPassword.equals(admin.getPass())) {
                System.out.println("Admin logged in");
                admin.setIsAdmin(true);
                valid = true;
            } else {
                System.out.println(
                        "Invalid username or password. Try again by entering any key or enter 1 to exit.");
                if (readLine(read).equals("1")) {
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

    static void handleUserMode(BufferedReader read, Contacts obj, User admin) {
        boolean again = true;
        while (again) {
            System.out.println("please Enter the name and phone number ");
            boolean validName = false;
            String name = "";

            while (!validName) {
                name = readLine(read);
                if (Contact.validName(name)) {
                    System.out.println("Name has been added");
                    validName = true;
                }
            }

            boolean validPhone = false;
            String phone = "";

            while (!validPhone) {
                phone = readLine(read);
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
                input = readLine(read).toLowerCase();
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

    static void displayAdminMenu(BufferedReader read, Contacts obj, User admin, boolean isAdmin) throws Exception {
        System.out.println();

        System.out.println("To add a contact, press 1");
        System.out.println("To edit a specific contact, press 2");
        System.out.println("To delete a contact press 3");
        System.out.println("To display a specific contact, press 4");
        System.out.println("To display all contacts, press 5");
        System.out.println("To print the number of contacts, press 6");
        System.out.println("to enter enter validation mode enter 7");
        System.out.println("To print the Contacts print 8 ");
        System.out.println("To exit admin mode, press 9");

        String adminInput = readLine(read);

        switch (adminInput) {

            case "1" -> {
                Methods.adminAdding( read,  obj,  admin) ;
            }
            case "2" -> {
                Methods.adminEditing(read, obj, admin);
            }
            case "3" -> {
                Methods.adminDeleting(read, obj);
            }
            case "4" -> {
                Methods.adminDisplay(read, obj, admin);
            }
            case "5" -> admin.displayAll();
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
                    String input = readLine(read);

                    switch (input) {
                        case "1" -> {
                            Methods.adminPrintingPrefixes(admin);
                        }
                        case "2" -> {
                            Methods.adminAddingPrefixes(read, admin);
                        }
                        case "3" -> {
                            Methods.adminRemovingPrefixes(read, admin);
                        }
                        case "4" -> {
                            Methods.adminPrintingLength(admin);
                        }
                        case "5" -> {
                            Methods.adminAddingLength(read, admin);
                        }
                        case "6" -> {
                            Methods.adminRemovingLength(read, admin);
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
                printFile();
            }
            case "9" -> {
                isAdmin = false;
                admin.setIsAdmin(isAdmin);
                System.out.println("User logged out");
            }
            default -> System.out.println("Please enter a number between 1-9");
        }
    }

    static void adminAdding(BufferedReader read, Contacts obj, User admin) {
        Boolean again = true;
        while (again) {
            System.out.println("Please enter the name and phone number ");
            boolean validName = false;
            String name = "";
    
            while (!validName) {
                name = readLine(read);
                if (Contact.validName(name)) {
                    System.out.println("Name has been added");
                    validName = true;
                }
            }
            boolean validPhone = false;
            String phone = "";
            while (!validPhone) {
                phone = readLine(read);
                if (admin.validPhone(phone)) {
                    validPhone = true;
    
                    if (obj.isDuplicated(phone)) {
                        validPhone = false;
                    }
                }
            }
            obj.addContact(new User(name, phone));
            System.out.println("Would you like to add another contact? Enter 'Yes' otherwise enter 'No'");
            String input = "";
            boolean validInput = false;
            while (!validInput) {
                input = readLine(read).toLowerCase();
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
    

    static void adminEditing(BufferedReader read, Contacts obj, User admin) {
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
                index = Integer.parseInt(readLine(read))- 1;
                if (obj.validIndex(index)) {
                    validIndex = true;
                }
            }

            boolean validName = false;
            String name = "";

            while (!validName) {
                System.out.println("Please enter the new name or your existing name");
                name = readLine(read);
                if (Contact.validName(name)) {
                    System.out.println("Name has been updated");
                    validName = true;
                }
            }

            boolean validPhone = false;
            String phone = "";

            while (!validPhone) {
                phone = readLine(read);
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
                input = readLine(read).toLowerCase();
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

    static void adminDeleting(BufferedReader read, Contacts obj) {
        System.out.println("Would you like to delete all contacts at once? enter yes otherwise no");
        String isAll = "";
        boolean yes = false;
        boolean validInput2 = false;
        while (!validInput2) {
            isAll = readLine(read).toLowerCase();
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
                    index = Integer.parseInt(readLine(read)) - 1;
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
                    input = readLine(read).toLowerCase();
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

    static void adminDisplay(BufferedReader read, Contacts obj, User admin) {
        boolean again = true;

        while (again) {
            if (obj.getContacts().isEmpty()) {
                System.out.println("Nothing to Display, Exiting...");
                break; // exit the loop when the array is empty
            }

            System.out.println("Please enter the Contact ID of the contact you want to Display");
            boolean validIndex = false;
            int index = 0;

            while (!validIndex) {
                index = Integer.parseInt(readLine(read)) - 1;
                if (obj.validIndex(index)) {
                    validIndex = true;
                }
            }

            admin.printContact(index);

            System.out.println("Would you like to Display other Contacts? Enter 'Yes' otherwise enter 'No'");
            String input = "";
            boolean validInput = false;

            while (!validInput) {
                input = readLine(read).toLowerCase();
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

    static void adminAddingPrefixes(BufferedReader read, User admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the prefixes you want add .to exit enter q");
        String input = readLine(read);
        while (!input.equals("q")) {
            inp.add(input);
            input = readLine(read);
        }
        admin.addPerefixes(inp);
    }

    static void adminRemovingPrefixes(BufferedReader read, User admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the prefixes you want to delete. to exit enter q");
        String input = readLine(read);
        while (!input.equals("q")) {
            inp.add(input);
            input = readLine(read);
        }
        admin.removePrefixes(inp);
    }

    static void adminAddingLength(BufferedReader read, User admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the lengths you want to add . to exit enter q");
        String input = readLine(read);
        while (!input.equals("q")) {

            if (Integer.parseInt(input) > 10) {
                inp.add(String.valueOf(input));
            }
            input = readLine(read);
        }
        admin.addLength(inp);
    }

    static void adminRemovingLength(BufferedReader read,User admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the lengths you want to delete . to exit enter q");
        String input = readLine(read);
        while (!input.equals("q")) {
            inp.add(input);
            input = readLine(read);
        }
        admin.removeLength(inp);
    }

    static void adminPrintingLength(User admin) {
        admin.printLengths();
    }

    static void adminPrintingPrefixes(User admin) {
        admin.printPrefixes();
    }

    static void printFile() throws Exception {
        if (!Contacts.contacts.isEmpty()) {
            File file = new File("src/Contacts/Contacts.txt");
            if (file.exists()) {
                file.delete();
            }
            PrintWriter write = new PrintWriter(file);
            for (int i = 0; i < Contacts.contacts.size(); i++) {
                write.println("Contact ID: " + (i + 1));
                write.println("Contact Name: " + Contacts.contacts.get(i).getName());
                write.println("Contact Number: " + Contacts.contacts.get(i).getPhone()
                        + (validPhoneForPrint(Contacts.contacts.get(i).getPhone()) ? " (valid) "
                                : " \"Invalid phone number please Contact the user\""));
                write.println();
            }
            System.out.println("Contacts printed to file: " + file.getAbsolutePath());
            write.close();
        } else {
            System.out.println("No Contacts to print");
        }
    }
    private static String readLine(BufferedReader reader) {
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
