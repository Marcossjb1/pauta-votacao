package com.vote.schedule.random;

import fabricator.Contact;
import fabricator.Fabricator;

import static java.lang.String.format;

public class Random {
    private static final Contact contact = Fabricator.contact();

    public static String firstName() {
        return contact.firstName();
    }

    public static String lastName() {
        return contact.lastName();
    }

    public static String fullName() {
        return format("%s %s", firstName(), lastName());
    }

    public static String email() {
        return contact.eMail();
    }
}
