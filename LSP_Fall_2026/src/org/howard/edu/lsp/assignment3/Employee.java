package org.howard.edu.lsp.assignment3;

/**
 * Represents a single employee payroll record as it moves through the
 * ETL pipeline. Holds the normalized input fields plus the computed
 * output fields (GrossPay, PayLevel, EmploymentStatus) once transformed.
 *
 * This class is a plain data holder: it does not decide validation rules
 * or payroll math itself. Those responsibilities belong to
 * EmployeeValidator and PayrollCalculator, respectively.
 */
public class Employee {

    private final int employeeId;
    private String name;
    private final String department;
    private final double hoursWorked;
    private final double hourlyRate;

    private String grossPay;      // formatted, e.g. "1000.00"
    private String payLevel;
    private String employmentStatus;

    public Employee(int employeeId, String name, String department,
                     double hoursWorked, double hourlyRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public String getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(String grossPay) {
        this.grossPay = grossPay;
    }

    public String getPayLevel() {
        return payLevel;
    }

    public void setPayLevel(String payLevel) {
        this.payLevel = payLevel;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    /**
     * Formats this employee's record as a single output CSV line, in the
     * required column order:
     * EmployeeID,Name,Department,HoursWorked,HourlyRate,GrossPay,PayLevel,EmploymentStatus
     */
    public String toCsvRow() {
        return String.format("%d,%s,%s,%.2f,%.2f,%s,%s,%s",
                employeeId, name, department, hoursWorked, hourlyRate,
                grossPay, payLevel, employmentStatus);
    }
}