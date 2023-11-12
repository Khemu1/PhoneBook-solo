package Main;
import java.util.*;
public class Admin extends Contact {
    private String name = "admin";
    private String pass = "9513572680";
    private ArrayList<String> defaultVali = new ArrayList<>(Arrays.asList("010", "011", "012", "015"));
    private ArrayList<String> changeableVali = new ArrayList<>();

    public String getName() {
        return this.name;
    }

    public String getPass() {
        return this.pass;
    }
    void addPrefixes(ArrayList<String> prefixes) {
        if (prefixes.isEmpty()) {
            return;
        }
        for (String prefix : prefixes) {
            if (!changeableVali.contains(prefix)) {
                changeableVali.add(prefix);
                printMessage("Added prefix: " + prefix);
            } else {
                printMessage("prefix : " + prefix + " is already added");
            }
        }
    }

    void removePrefixes(ArrayList<String> prefixes) {
        if (prefixes.isEmpty()) {
            printMessage("nothing in the array to remove");
            printMessage("Exiting");
            return;
        }
        for (String prefix : prefixes) {
            if (changeableVali.contains(prefix)) {
                printMessage(prefix + " has been removed");
                changeableVali.remove(prefix);
            }
        }
    }

    void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    protected final boolean validPhone(String phone) {
        if (!changeableVali.isEmpty()) {
            for (String prefix : changeableVali) {
                if (phone.matches("^(" + prefix + ")\\d{8}$")) {
                    return true;
                }
            }
            return false;
        } else {
            for (String prefix : defaultVali) {
                if (phone.matches("^(" + prefix + ")\\d{8}$")) {
                    return true;
                }
            }
            return false;
        }
    }
}
