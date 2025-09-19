
package Chapter_3;

public class DemoGrossPay {
    public static void main(String[] args) {
        // Set hours worked
        double hoursWorked = 40;

        // Call the method to calculate and display gross pay
        calculateGross(hoursWorked);
    }

    // This method calculates gross pay and prints the result
    public static void calculateGross(double hours) {
        double hourlyRate = 22.75;
        double grossPay = hours * hourlyRate;

        System.out.println("Hours Worked: " + hours);
        System.out.println("Hourly Rate: " + hourlyRate);
        System.out.println("Gross Pay: " + grossPay);
    }
}