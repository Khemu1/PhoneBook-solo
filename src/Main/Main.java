package Main;

import java.util.*;

public class Main extends Methods {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Contacts obj = new Contacts();
        Admin admin = new Admin();
        boolean exit = false;
        boolean isAdmin = false;

        System.err.println(
                "Please note that the system is currently running on the default parameters which works for Egyptian phone numbers");
        System.out.println();

        while (!exit) {
            switch (Methods.getUserTypeInput(in, isAdmin)) {
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
                    Methods.handleUserMode(in, obj, admin);
                }
                default -> {
                    if (isAdmin) {
                        System.out.println("Exiting admin mode.");
                        isAdmin = false;
                    } else {
                        System.out.println("Invalid input. Please enter 1 for admin or 2 for user.");
                    }
                }

            }
            while (isAdmin) {
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
                        Methods.AdminEditing(in, obj, admin);
                    }
                    case "3" -> {
                        Methods.AdminDeleting(in, obj);
                    }
                    case "4" -> {
                        Methods.AdminPrinting(in, obj);
                    }
                    case "5" -> obj.printAll();
                    case "6" -> Methods.AdminPrintingAmountOfContacts(obj);
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
                            // WIP
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
                        System.out.println("Admin logged out");
                    }
                    default -> System.out.println("Please enter a number between 1-6");
                }
            }

        }
    }
}
