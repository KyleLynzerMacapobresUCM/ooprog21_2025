package Chapter_5;

public class Employee {
    double payRate;

    public Employee(double payRate) {
        this.payRate = payRate; // no cap here so rate 155 stays 155
    }

    public double getRegularPay(double hours) {
        double regularHours = Math.min(hours, 40); // only first 40 hours count as regular
        return regularHours * payRate;
    }

    public double getOvertimePay(double hours) {
        double overtimeHours = Math.max(0, hours - 40);
        return overtimeHours * payRate * 1.5;
    }
}


    

