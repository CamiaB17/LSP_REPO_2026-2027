package org.howard.edu.lsp.assignment3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Owns the Load stage: writes a list of transformed Employee records to
 * the output CSV file, including the required header row.
 */
public class EmployeeCsvWriter {

    private static final String HEADER =
            "EmployeeID,Name,Department,HoursWorked,HourlyRate,GrossPay,PayLevel,EmploymentStatus";

    private final String outputPath;

    public EmployeeCsvWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    public void write(List<Employee> employees) throws IOException {
        StringBuilder output = new StringBuilder();
        output.append(HEADER).append("\n");

        for (Employee employee : employees) {
            output.append(employee.toCsvRow()).append("\n");
        }

        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write(output.toString());
        }
    }

    public String getOutputPath() {
        return outputPath;
    }
}