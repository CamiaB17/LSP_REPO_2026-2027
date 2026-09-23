package org.howard.edu.lsp.assignment3;

/**
 * Parses a single raw CSV line into an Employee object.
 *
 * Owns the Extract-stage responsibilities: splitting the line into
 * fields, trimming whitespace, and validating that the row is
 * well-formed and numerically valid (Assignment #2, Transform Step 1-2).
 * Rows that fail these checks are rejected via InvalidRowException so the
 * pipeline can count them as skipped without needing to know the reason.
 */
public class EmployeeParser {

    private static final int EXPECTED_FIELD_COUNT = 5;

    /**
     * Parses one raw (unsplit) CSV line into an Employee.
     *
     * @throws InvalidRowException if the row is blank, malformed, or
     *         contains invalid numeric values (per the skip rules).
     */
    public Employee parse(String rawLine) throws InvalidRowException {
        if (rawLine == null || rawLine.trim().isEmpty()) {
            throw new InvalidRowException("Blank row");
        }

        String[] fields = rawLine.split(",", -1);
        if (fields.length != EXPECTED_FIELD_COUNT) {
            throw new InvalidRowException("Row does not contain exactly five fields");
        }

        String idStr = fields[0].trim();
        String name = fields[1].trim();
        String department = fields[2].trim();
        String hoursStr = fields[3].trim();
        String rateStr = fields[4].trim();

        int employeeId;
        double hoursWorked;
        double hourlyRate;

        try {
            employeeId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            throw new InvalidRowException("EmployeeID is not a valid integer: " + idStr);
        }

        try {
            hoursWorked = Double.parseDouble(hoursStr);
        } catch (NumberFormatException e) {
            throw new InvalidRowException("HoursWorked is not a valid decimal: " + hoursStr);
        }

        try {
            hourlyRate = Double.parseDouble(rateStr);
        } catch (NumberFormatException e) {
            throw new InvalidRowException("HourlyRate is not a valid decimal: " + rateStr);
        }

        if (hoursWorked < 0) {
            throw new InvalidRowException("HoursWorked is negative: " + hoursWorked);
        }
        if (hourlyRate < 0) {
            throw new InvalidRowException("HourlyRate is negative: " + hourlyRate);
        }

        // Normalize name to uppercase (Transform Step 1)
        name = name.toUpperCase();

        return new Employee(employeeId, name, department, hoursWorked, hourlyRate);
    }
}
