package org.howard.edu.lsp.assignment3;

/**
 * Signals that a raw CSV row failed validation and must be skipped.
 * Used by EmployeeValidator to communicate a skip decision back to the
 * pipeline without relying on null returns or magic values.
 */
public class InvalidRowException extends Exception {
    public InvalidRowException(String message) {
        super(message);
    }
}