package Chapter_5;

public class Employee {
    // Instance variable to store the employee's hourly pay rate
    double payRate;

    // Constructor to initialize the pay rate when creating an Employee object
    public Employee(double payRate) {
        this.payRate = payRate;
    }

    // Method to calculate regular pay (for up to 40 hours)
    public double getRegularPay(double hours) {
        // Regular hours are capped at 40
        double regularHours = Math.min(hours, 40);
        // Return pay for regular hours
        return regularHours * payRate;
    }

    // Method to calculate overtime pay (for hours beyond 40)
    public double getOvertimePay(double hours) {
        // Overtime hours are any hours over 40
        double overtimeHours = Math.max(0, hours - 40);
        // Overtime pay is 1.5 times the regular rate
        return overtimeHours * payRate * 1.5;
    }
}
