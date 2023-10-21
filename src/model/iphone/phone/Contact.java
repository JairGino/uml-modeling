package model.iphone.phone;

import model.iphone.Iphone;
import model.iphone.media.Image;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private Image photo;
    private String name;
    private List<String> phoneNumbers = new ArrayList<String>();
    private String email;
    private String address;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumbers.add(phoneNumber);
    }

    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
