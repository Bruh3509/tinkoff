package edu.hw3;

import java.util.Arrays;

public class Task5 {
    private static final String DESC_ORDER = "DESC";

    public static String[] parseContacts(String[] contacts, String order) {
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

        return contacts;
    }

    private Task5() {
    }
}
