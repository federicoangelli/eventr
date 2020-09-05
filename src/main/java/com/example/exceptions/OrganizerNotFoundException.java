package com.example.exceptions;

public class OrganizerNotFoundException extends RuntimeException {

    public OrganizerNotFoundException(Long id) {
        super("Could not find organizer " + id);
    }
}
