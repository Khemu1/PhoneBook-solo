package User;

import Main.Admin;

public class User extends Admin {
    String name;
    String phone;

    public User() {
    }

    public User(String name, String phone) {
        super(name, phone);
    }

}
