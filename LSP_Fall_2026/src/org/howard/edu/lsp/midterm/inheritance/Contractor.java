
package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents a contractor who receives a 15 percent premium
 * on all hours worked.
 */
public class Contractor extends Employee {

    /**
     * Creates a contractor.
     *
     * @param hoursWorked the number of hours worked
     * @param hourlyRate the contractor's hourly pay rate
     */
    public Contractor(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates pay with a 15 percent premium on all hours.
     *
     * @return total pay including the 15 percent premium
     */
    @Override
    public double calculatePay() {
        return hoursWorked * hourlyRate * 1.15;
    }
}
