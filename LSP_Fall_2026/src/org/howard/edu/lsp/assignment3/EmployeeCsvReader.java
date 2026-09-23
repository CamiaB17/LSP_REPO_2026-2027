package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Owns the Extract stage: reads the raw input CSV file, delegates each
 * non-header line to an EmployeeParser, and reports how many rows were
 * read versus skipped.
 */
public class EmployeeCsvReader {

    private final String inputPath;
    private final EmployeeParser parser;

    private int rowsRead = 0;
    private int rowsSkipped = 0;

    public EmployeeCsvReader(String inputPath, EmployeeParser parser) {
        this.inputPath = inputPath;
        this.parser = parser;
    }

    /**
     * Reads all valid employee records from the input file. Invalid rows
     * are counted as skipped but do not stop processing.
     */
    public List<Employee> readEmployees() throws IOException {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            reader.readLine(); // header line: not counted, not transformed

            String line;
            while ((line = reader.readLine()) != null) {
                rowsRead++;
                try {
                    employees.add(parser.parse(line));
                } catch (InvalidRowException e) {
                    rowsSkipped++;
                }
            }
        }

        return employees;
    }

    public int getRowsRead() {
        return rowsRead;
    }

    public int getRowsSkipped() {
        return rowsSkipped;
    }
}