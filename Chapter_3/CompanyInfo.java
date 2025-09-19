package Chapter_3;

public class CompanyInfo {
    public static void main(String[] args) {
        // Print the company name
        System.out.println("ABC Corporation");

        // Call the method to display business hours
        displayHours();
    }

    // This method prints the business hours for each day
    public static void displayHours() {
        System.out.println("Business Hours:");
        System.out.println("Monday:    9:00 AM - 5:00 PM");
        System.out.println("Tuesday:   9:00 AM - 5:00 PM");
        System.out.println("Wednesday: 9:00 AM - 5:00 PM");
        System.out.println("Thursday:  9:00 AM - 5:00 PM");
        System.out.println("Friday:    9:00 AM - 5:00 PM");
        System.out.println("Saturday:  10:00 AM - 2:00 PM");
        System.out.println("Sunday:    Closed");
    }
}