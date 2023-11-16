package Main;

import User.User;
import java.util.*;

public class Methods extends Admin {
    static String getUserTypeInput(Scanner in, boolean isAdmin) {
        if (!isAdmin) {
            System.out.println("To enter the admin mode, please enter 1");
            System.out.println("To enter the User mode, please enter 2");
            return in.next();
        }
        return "";
    }

    static void handleUserMode(Scanner in, Contacts obj, Admin admin) {
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

    static void adminAdding(Scanner in, Contacts obj, Admin admin) {
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

    static void AdminEditing(Scanner in, Contacts obj, Admin admin) {
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

    static void AdminDeleting(Scanner in, Contacts obj) {
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

    static void AdminPrinting(Scanner in, Contacts obj) {
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

    static void AdminPrintingAmountOfContacts(Contacts obj) {
        System.out.println("You have " + obj.getContacts().size() + " contact" +
                (obj.getContacts().size() > 1 ? "s" : ""));
    }

    static void adminAddingPrefixes(Scanner in, Admin admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the prefixes you want add .to exit enter q");
        String input = in.next();
        while (!input.equals("q")) {
            inp.add(input);
            input = in.next();
        }
        admin.addPerefixes(inp);
    }

    static void adminRemovingPrefixes(Scanner in, Admin admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the prefixes you want to delete. to exit enter q");
        String input = in.next();
        while (!input.equals("q")) {
            inp.add(input);
            input = in.next();
        }
        admin.removePrefixes(inp);
    }

    static void adminAddingLength(Scanner in, Admin admin) {
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

    static void adminRemovingLength(Scanner in, Admin admin) {
        ArrayList<String> inp = new ArrayList<>();
        System.out.println("please enter the lengths you want to delete . to exit enter q");
        String input = in.next();
        while (!input.equals("q")) {
            inp.add(input);
            input = in.next();
        }
        admin.removeLength(inp);
    }

    static void adminPrintingLength(Admin admin) {
        admin.printLengths();
    }

    static void adminPrintingPrefixes(Admin admin) {
        admin.printPrefixes();
    }
}
