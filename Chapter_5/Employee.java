package Chapter_5;

public class Employee {
    double payRate;

    public Employee(double payRate) {
<<<<<<< HEAD
        this.payRate = payRate; 
    }

    public double getRegularPay(double hours) {
        double regularHours = Math.min(hours, 40); 
=======
        this.payRate = payRate;
    }

    public double getRegularPay(double hours) {
        double regularHours = Math.min(hours, 40);
>>>>>>> 5b2faac01456aa8a13fab96c093f22f34a424190
        return regularHours * payRate;
    }

    public double getOvertimePay(double hours) {
        double overtimeHours = Math.max(0, hours - 40);
        return overtimeHours * payRate * 1.5;
    }
}


    

