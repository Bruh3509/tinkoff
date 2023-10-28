package edu.hw3.Task5;

import java.util.Arrays;

public class Task5 {
    private static final String DESC_ORDER = "DESC";

    public static Contact[] parseContacts(String[] contacts, String order) {
        int orderNum;
        if (order.equals(DESC_ORDER)) {
            orderNum = -1;
        } else {
            orderNum = 1;
        }

        Arrays.sort(contacts, (String contact1, String contact2) -> {
            String[] contact1Split = contact1.split(" ");
            String[] contact2Split = contact2.split(" ");

            return orderNum
                * contact1Split[contact1Split.length - 1].compareTo(contact2Split[contact2Split.length - 1]);
        });

        Contact[] contactsObject = new Contact[contacts.length];
        for (int i = 0; i < contacts.length; ++i) {
            final var contact = contacts[i].split(" ");
            contactsObject[i] = new Contact(contact[0], contact.length == 1 ? "" : contact[1]);
        }

        return contactsObject;
    }

    private Task5() {
    }
}
