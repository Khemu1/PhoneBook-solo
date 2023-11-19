    package Main;

    import java.util.*;

    public class Main extends Methods {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            Contacts obj = new Contacts();
            User admin = new User();
            boolean exit = false;
            boolean isAdmin = admin.getIsAdmin();

            System.err.println(
                    "Please note that the system is currently running on the default parameters which works for Egyptian phone numbers");
            System.out.println();

            while (!exit) {
                switch (Methods.getUserTypeInput(in, isAdmin)) {
                    case "1" -> {
                        Methods.handleAdminLogin(in, admin);
                    }
                    case "2" -> {
                        Methods.handleUserMode(in, obj, admin);
                    }
                    default -> {
                        Methods.handleExitOrInvalidInput(admin);
                    }
                }
                while (admin.getIsAdmin()) {
                    Methods.displayAdminMenu(in,obj,admin,isAdmin);
                }

            }
        }
    }
