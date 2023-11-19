package Main;

import java.util.*;

public class User extends Contact {
    private final String userName = "admin";
    private final String pass = "admin";
    private boolean isAdmin = false;
    private static ArrayList<String> defaultVali = new ArrayList<>(Arrays.asList("010", "011", "012", "015"));
    private static ArrayList<String> changeablePrefixes = new ArrayList<>();
    private static ArrayList<String> changeableLength = new ArrayList<>();

    public User() {
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    // public void checkValidity(String nambah) { Don't mind it
    // // boolean isValid = false;
    // System.out.println(changeablePrefixes);
    // ArrayList<String> lenghtList = new ArrayList<>();
    // ArrayList<String> prefixList = new ArrayList<>();

    // if (changeableLength.isEmpty()) {
    // lenghtList.add("8");
    // }
    // if (changeablePrefixes.isEmpty()) {
    // prefixList.addAll(defaultVali);
    // }

    // lenghtList.addAll(changeableLength);
    // prefixList.addAll(changeablePrefixes);

    // if (!lenghtList.contains(String.valueOf(nambah.length()))) {
    // System.out.println("INVALID");
    // return;
    // }

    // String sub = nambah.substring(0, 4);
    // if (prefixList.contains(sub)) {
    // System.out.println("INVALID");
    // }

    // System.out.println("VALID WOOO");

    // // return isValid;
    // }

    public User(String name, String phone) {
        super(name, phone);
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPass() {
        return this.pass;
    }

    void addPerefixes(ArrayList<String> prefixes) {
        if (prefixes.isEmpty()) {
            return;
        }
        for (String prefix : prefixes) {
            if (!changeablePrefixes.contains(prefix)) {
                changeablePrefixes.add(prefix);
                printMessage("Added prefix: " + prefix);
            } else {
                printMessage("prefix : " + prefix + " is already added");
            }
        }
        System.out.println("your valid prefixes are : " + changeablePrefixes);
    }

    void removePrefixes(ArrayList<String> prefixes) {
        if (prefixes.isEmpty()) {
            printMessage("nothing in the array to remove");
            printMessage("Exiting");
            return;
        }
        for (String prefix : prefixes) {
            if (changeablePrefixes.contains(prefix)) {
                printMessage(prefix + " has been removed");
                changeablePrefixes.remove(prefix);
            }
        }
        System.out.println("your valid prefixes are : " + changeablePrefixes);
    }

    void printPrefixes() {
        if (changeablePrefixes.isEmpty()) {
            System.out.println("No prefixes at the current time");
            System.out.println("System is working on the default mode");
            return;

        } else {
            System.out.println(changeablePrefixes);
        }
    }

    void addLength(ArrayList<String> length) {
        if (length.isEmpty()) {
            return;
        }
        for (String lengths : length) {
            if (!changeableLength.contains(lengths)) {
                changeableLength.add(lengths);
                printMessage("Added length: " + lengths);
            } else {
                printMessage("length : " + lengths + " is already added");
            }
        }
        System.out.println("your valid lengths are : " + changeablePrefixes);
    }

    void removeLength(ArrayList<String> length) {
        if (length.isEmpty()) {
            printMessage("nothing in the array to remove");
            printMessage("Exiting");
            return;
        }
        for (String lengths : length) {
            if (changeableLength.contains(lengths)) {
                printMessage(length + " has been removed");
                changeableLength.remove(lengths);
            }
        }
        System.out.println("your valid lengths are : " + changeablePrefixes);
    }

    void printLengths() {
        if (changeableLength.isEmpty()) {
            System.out.println("No lengths at the current time");
            System.out.println("System is working on the default mode");
            return;

        } else {
            System.out.println(changeableLength);
        }
    }

    void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    protected final boolean validPhone(String phone) {
        phone = phone.trim(); // Remove leading and trailing spaces
        String regex;
        if (!changeablePrefixes.isEmpty() && !changeableLength.isEmpty()) {
            regex = "^(" + getPrefixRegex() + ")\\d{" + getLengthRegex() + "}$";
        } else if (changeablePrefixes.isEmpty() && !changeableLength.isEmpty()) {
            regex = "^(" + getDefaultPrefixRegex() + ")\\d{" + getLengthRegex() + "}$";
        } else if (!changeablePrefixes.isEmpty() && changeableLength.isEmpty()) {
            regex = "^(" + getPrefixRegex() + "\\d{8})$";
        } else {
            regex = "^(" + getDefaultPrefixRegex() + ")\\d{8}$";
        }
        if (phone.matches(regex)) {
            return true;
        } else {
            if (!changeablePrefixes.isEmpty() && !changeableLength.isEmpty()) {
                System.out.println(
                        "Invalid phone number. Please ensure your phone number starts with the following prefixes: "
                                + changeablePrefixes + " and has a length from the following: " + changeableLength);
            } else if (changeablePrefixes.isEmpty() && !changeableLength.isEmpty()) {
                System.out.println(
                        "Invalid phone number. Please ensure your phone number starts with the following prefixes: "
                                + defaultVali + " and has a length from the following: " + changeableLength);
            } else if (!changeablePrefixes.isEmpty() && changeableLength.isEmpty()) {
                System.out.println(
                        "Invalid phone number. Please ensure your phone number starts with the following prefixes: "
                                + changeablePrefixes + " and has a length from the following: " + "[" + 8 + "]");
            } else {
                System.out.println(
                        "Invalid phone number. Please ensure your phone number starts with the following prefixes: "
                                + defaultVali + " and has a length from the following: " + "[" + 8 + "]");
            }
            return false;
        }
    }

    private String getLengthRegex() { // this method returns the length from their arraylist an put them into a string
                                      // which will be put in the regex in the validation method later
        StringBuilder lengthRegex = new StringBuilder();
        for (String length : changeableLength) {
            int length1 = Integer.parseInt(length) - 3;
            lengthRegex.append(String.valueOf(length1)).append(",");
        }
        // Remove the last "|" if it exists
        if (lengthRegex.length() > 0) {
            lengthRegex.setLength(lengthRegex.length() - 1);
        }
        return lengthRegex.toString();
    }

    private String getPrefixRegex() { // this method returns the prefixes from their arraylist an put them into a
                                      // string which will be put in the regex in the validation method later
        StringBuilder prefixRegex = new StringBuilder();
        for (String prefix : changeablePrefixes) {
            prefixRegex.append(prefix).append("|");
        }
        if (prefixRegex.length() > 0) {
            prefixRegex.setLength(prefixRegex.length() - 1);
        }
        return prefixRegex.toString();
    }

    private String getDefaultPrefixRegex() { // this method returns the default prefixes from their arraylist an put
                                             // them into a string which will be put in the regex in the validation
                                             // method later this method was made to avoid using loops for checking

        StringBuilder prefixRegex = new StringBuilder();
        for (String prefix : defaultVali) {
            prefixRegex.append(prefix).append("|");
        }
        prefixRegex.setLength(prefixRegex.length() - 1);
        return prefixRegex.toString().trim();
    }
}
