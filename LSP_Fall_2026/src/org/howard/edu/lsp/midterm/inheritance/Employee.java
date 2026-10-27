
package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents an employee with hours worked and an hourly rate.
 */
public abstract class Employee {
    protected double hoursWorked;
    protected double hourlyRate;

    /**
     * Creates an employee with the specified hours and hourly rate.
     *
     * @param hoursWorked the number of hours worked
     * @param hourlyRate the employee's hourly pay rate
     */
    public Employee(double hoursWorked, double hourlyRate) {
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    /**
     * Calculates the employee's pay.
     *
     * @return the calculated pay
     */
    public abstract double calculatePay();
}
