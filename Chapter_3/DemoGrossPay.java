package Chapter_3;

public class DemoGrossPay {
    public static void main(String[] args) {
        displayGrossPay(10.0);
        displayGrossPay(25.0);
        displayGrossPay(37.5);
    }

    public static double calculateGross(double hoursWorked) {
        final double HOURLY_RATE = 22.75;
        return hoursWorked * HOURLY_RATE;
    }

    public static void displayGrossPay(double hours) {
        double gross = calculateGross(hours);
        System.out.println(hours + " hours at $22.75 per hour is $" + gross);
    }
}
