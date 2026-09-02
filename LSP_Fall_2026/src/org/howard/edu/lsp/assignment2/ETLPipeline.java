package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    private static final String INPUT_PATH = "data/employees.csv";
    private static final String OUTPUT_PATH = "data/transformed_employees.csv";

    public static void main(String[] args) {
        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        StringBuilder output = new StringBuilder();
        output.append("EmployeeID,Name,Department,HoursWorked,HourlyRate,GrossPay,PayLevel,EmploymentStatus\n");

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_PATH))) {
            String line = reader.readLine(); 

            while ((line = reader.readLine()) != null) {
                rowsRead++;

                String[] fields = splitRow(line);

                // Blank line or wrong number of fields
                if (fields == null || fields.length != 5) {
                    rowsSkipped++;
                    continue;
                }

                // Step 1: Normalize - trim every field
                String idStr = fields[0].trim();
                String name = fields[1].trim();
                String department = fields[2].trim();
                String hoursStr = fields[3].trim();
                String rateStr = fields[4].trim();

                // Step 2: Validate numeric values
                int employeeId;
                double hoursWorked;
                double hourlyRate;

                try {
                    employeeId = Integer.parseInt(idStr);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    hoursWorked = Double.parseDouble(hoursStr);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    hourlyRate = Double.parseDouble(rateStr);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                if (hoursWorked < 0 || hourlyRate < 0) {
                    rowsSkipped++;
                    continue;
                }

                
                name = name.toUpperCase();

                // Step 3: Calculate base/overtime pay
                double grossPayRaw;
                if (hoursWorked <= 40.00) {
                    grossPayRaw = hoursWorked * hourlyRate;
                } else {
                    double overtimeHours = hoursWorked - 40.00;
                    grossPayRaw = (40.00 * hourlyRate) + (overtimeHours * hourlyRate * 1.5);
                }

                // Step 4: Apply IT bonus (5%), case-sensitive exact match, after overtime
                if (department.equals("IT")) {
                    grossPayRaw = grossPayRaw * 1.05;
                }

                // Step 5: Round GrossPay to 2 decimal places, round-half-up
                BigDecimal grossPay = BigDecimal.valueOf(grossPayRaw).setScale(2, RoundingMode.HALF_UP);

                // Step 6: Determine PayLevel using rounded GrossPay
                String payLevel;
                double grossPayValue = grossPay.doubleValue();
                if (grossPayValue < 500.00) {
                    payLevel = "Low";
                } else if (grossPayValue < 1000.00) {
                    payLevel = "Standard";
                } else if (grossPayValue < 2000.00) {
                    payLevel = "High";
                } else {
                    payLevel = "Executive";
                }

                // Step 7: Determine EmploymentStatus
                String employmentStatus = (hoursWorked < 30.00) ? "Part-Time" : "Full-Time";

                // Format output fields
                String hoursOut = String.format("%.2f", hoursWorked);
                String rateOut = String.format("%.2f", hourlyRate);
                String grossPayOut = grossPay.toPlainString();

                output.append(employeeId).append(",")
                      .append(name).append(",")
                      .append(department).append(",")
                      .append(hoursOut).append(",")
                      .append(rateOut).append(",")
                      .append(grossPayOut).append(",")
                      .append(payLevel).append(",")
                      .append(employmentStatus).append("\n");

                rowsTransformed++;
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        try (FileWriter writer = new FileWriter(OUTPUT_PATH)) {
            writer.write(output.toString());
        } catch (IOException e) {
            System.out.println("Error writing output file: " + e.getMessage());
            return;
        }

        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file: " + OUTPUT_PATH);
    }

    
    private static String[] splitRow(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        
        return line.split(",", -1);
    }
}
