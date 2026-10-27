
package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents a full-time employee who receives overtime pay
 * for hours worked beyond 40 hours per week.
 */
public class FullTimeEmployee extends Employee {

    /**
     * Creates a full-time employee.
     *
     * @param hoursWorked the number of hours worked
     * @param hourlyRate the employee's hourly pay rate
     */
    public FullTimeEmployee(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates regular pay and overtime pay.
     *
     * @return total pay, with overtime paid at 1.5 times the hourly rate
     */
    @Override
    public double calculatePay() {
        if (hoursWorked <= 40) {
            return hoursWorked * hourlyRate;
        }

        double regularPay = 40 * hourlyRate;
        double overtimeHours = hoursWorked - 40;
        double overtimePay = overtimeHours * hourlyRate * 1.5;

        return regularPay + overtimePay;
    }
}
