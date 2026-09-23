package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Applies the payroll business rules to an Employee (Assignment #2,
 * Transform Steps 3-7): overtime pay, the IT department bonus, GrossPay
 * rounding, PayLevel classification, and EmploymentStatus classification.
 *
 * Centralizing this logic here (rather than in Employee or the pipeline)
 * means the payroll rules can change without touching how records are
 * read, written, or orchestrated.
 */
public class PayrollCalculator {

    private static final double OVERTIME_THRESHOLD_HOURS = 40.00;
    private static final double OVERTIME_MULTIPLIER = 1.5;
    private static final double IT_BONUS_MULTIPLIER = 1.05;
    private static final String IT_DEPARTMENT = "IT";

    private static final double PART_TIME_THRESHOLD_HOURS = 30.00;

    private static final double LOW_MAX = 500.00;
    private static final double STANDARD_MAX = 1000.00;
    private static final double HIGH_MAX = 2000.00;

    /**
     * Calculates and sets GrossPay, PayLevel, and EmploymentStatus on the
     * given Employee, in place.
     */
    public void calculate(Employee employee) {
        double grossPayRaw = calculateBaseAndOvertimePay(
                employee.getHoursWorked(), employee.getHourlyRate());

        if (employee.getDepartment().equals(IT_DEPARTMENT)) {
            grossPayRaw = applyItBonus(grossPayRaw);
        }

        BigDecimal grossPay = roundToCurrency(grossPayRaw);
        employee.setGrossPay(grossPay.toPlainString());
        employee.setPayLevel(determinePayLevel(grossPay.doubleValue()));
        employee.setEmploymentStatus(determineEmploymentStatus(employee.getHoursWorked()));
    }

    private double calculateBaseAndOvertimePay(double hoursWorked, double hourlyRate) {
        if (hoursWorked <= OVERTIME_THRESHOLD_HOURS) {
            return hoursWorked * hourlyRate;
        }
        double overtimeHours = hoursWorked - OVERTIME_THRESHOLD_HOURS;
        return (OVERTIME_THRESHOLD_HOURS * hourlyRate)
                + (overtimeHours * hourlyRate * OVERTIME_MULTIPLIER);
    }

    private double applyItBonus(double grossPay) {
        return grossPay * IT_BONUS_MULTIPLIER;
    }

    private BigDecimal roundToCurrency(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }

    private String determinePayLevel(double grossPay) {
        if (grossPay < LOW_MAX) {
            return "Low";
        } else if (grossPay < STANDARD_MAX) {
            return "Standard";
        } else if (grossPay < HIGH_MAX) {
            return "High";
        } else {
            return "Executive";
        }
    }

    private String determineEmploymentStatus(double hoursWorked) {
        return (hoursWorked < PART_TIME_THRESHOLD_HOURS) ? "Part-Time" : "Full-Time";
    }
}