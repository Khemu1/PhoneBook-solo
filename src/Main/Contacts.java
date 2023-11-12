package Main;
import java.util.ArrayList;
public class Contacts {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public Contacts() {
    }
    public Contacts(Contact obj) {
        contacts.add(obj);
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
    public void addContact(String name , String phone) {
        if (contacts.isEmpty()) {
            if (Contact.validName(name) && new Contact().validPhone(phone))    {
                contacts.add(new Contact(name,phone));
                System.out.println("Contact added");
            }
        }
        else    {
            if (Contact.validName(name) && new Contact().validPhone(phone) && !isDuplicated(phone))    {
                contacts.add(new Contact(name,phone));
                System.out.println("Contact added");
            }
            else    {
                System.err.println("Duplicated phone number , please Enter another one");
            }
        }

    }
    public void editContact(int index, String name, String phone)  {
        if ((index-1)>=0 && index-1< contacts.size() )  {
            Contact contact= contacts.get(index);
            contact.setName(name);
            contact.setPhone(phone);
            System.out.println("Contact has been edit");
        }
        else {
            System.err.println("this Contact doesn't exist");
        }
    }
    public void deleteContact(int index)  {
        if ((index-1)>=0 && index-1< contacts.size() )  {
            contacts.remove(index-1);
            System.out.println("Contact has been deleted");
        }
        else    {
            System.err.println("this Contact doesn't exist");
        }
    }
    public boolean validIndex(int index) {  // this method can't be static because you're trying to access contacts which is static
        return (index - 1) >= 0 && index - 1 < contacts.size();
    }
    public boolean isDuplicated(String phone) {
        if (new Contact().validPhone(phone)) {
            for (Contact contact : contacts) {
                if (contact.getPhone().equals(phone)) {
                    return true;
                }
            }
        }
        return false;
    }



    public void printAll()  {
        if (contacts.isEmpty()) {
            System.err.println("No Contacts to print");
        }
        else    {
            for (int i=0;i<contacts.size();i++) {
                System.out.println("contact : " +(i+1));
                System.out.println("Contact Name : "+contacts.get(i).getName());
                System.out.println("Contact Number : " + contacts.get(i).getPhone());
                System.out.println();
            }
        }

    }
    public void printContact(int index)  {
        if ((index-1)>=0 && index-1< contacts.size() )  {
            System.out.println("Contact Name :"+contacts.get(index-1).getName());
            System.out.println(" Contact Number : " +contacts.get(index-1).getPhone());
        }
        else {
            System.err.println("this Contact doesn't exist");
        }
    }
}
