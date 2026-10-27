
package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents a part-time employee who receives straight hourly pay.
 */
public class PartTimeEmployee extends Employee {

    /**
     * Creates a part-time employee.
     *
     * @param hoursWorked the number of hours worked
     * @param hourlyRate the employee's hourly pay rate
     */
    public PartTimeEmployee(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates straight hourly pay.
     *
     * @return hours worked multiplied by the hourly rate
     */
    @Override
    public double calculatePay() {
        return hoursWorked * hourlyRate;
    }
}
