public class Contact {
    private String name;
    private String phone;

    public Contact()  {

    }
    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (validName(name))    {
            this.name = name;
        }
        else    {
            System.err.println("name must contain only english letters");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (validPhone(phone))   {
            this.phone = phone;
        }
        else    {
            System.out.println("length must 11 numbers and it must be an Egyptian number ");
        }
    }
    public static boolean validPhone(String phone) {
        if (phone.matches("^(01[0-2]|015)\\d{8}$"))  {
            return true;
        }
        if (phone.length()!=11) {
            System.err.println("Phone number length must be 11");
        }
        else   {
            System.err.println("Invalid Phone Number Format");
        }
        return false;
    }
    public static boolean validName(String name)   {
        if (name.matches("[a-zA-Z ]*"))   {
            return true;
        }
        System.err.println("Invalid Name");
        return false;
    }
}
