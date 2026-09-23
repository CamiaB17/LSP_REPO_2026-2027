package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * Orchestrates the Employee Payroll ETL pipeline.
 *
 * This class no longer contains parsing, validation, or payroll
 * business logic itself (contrast with the Assignment #2 solution,
 * where all of that lived in one method here). Its only responsibility
 * is coordinating the Extract, Transform, and Load stages and reporting
 * the run summary.
 */
public class ETLPipeline {

    private static final String INPUT_PATH = "data/employees.csv";
    private static final String OUTPUT_PATH = "data/transformed_employees.csv";

    public static void main(String[] args) {
        EmployeeCsvReader reader = new EmployeeCsvReader(INPUT_PATH, new EmployeeParser());
        PayrollCalculator calculator = new PayrollCalculator();
        EmployeeCsvWriter writer = new EmployeeCsvWriter(OUTPUT_PATH);

        List<Employee> employees;
        try {
            employees = reader.readEmployees();
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        for (Employee employee : employees) {
            calculator.calculate(employee);
        }

        try {
            writer.write(employees);
        } catch (IOException e) {
            System.out.println("Error writing output file: " + e.getMessage());
            return;
        }

        printRunSummary(reader.getRowsRead(), employees.size(),
                reader.getRowsSkipped(), writer.getOutputPath());
    }

    private static void printRunSummary(int rowsRead, int rowsTransformed,
                                         int rowsSkipped, String outputPath) {
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file: " + outputPath);
    }
}