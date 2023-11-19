package Main;

import java.io.*;

public class Main extends Methods {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Contacts obj = new Contacts();
        User admin = new User();
        boolean exit = false;
        boolean isAdmin = admin.getIsAdmin();

        System.err.println(
                "Please note that the system is currently running on the default parameters which works for Egyptian phone numbers");
        System.out.println();

        while (!exit) {
            switch (Methods.getUserTypeInput(reader, isAdmin)) {
                case "1" -> {
                    Methods.handleAdminLogin(reader, admin);
                }
                case "2" -> {
                    Methods.handleUserMode(reader, obj, admin);
                }
                default -> {
                    Methods.handleExitOrInvalidInput(admin);
                }
            }
            while (admin.getIsAdmin()) {
                Methods.displayAdminMenu(reader, obj, admin, isAdmin);
            }
        }
    }

}
