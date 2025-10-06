package Chapter_5;
import java.util.Scanner;

public class Payroll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many hours did you work this week? ");
        double hours = sc.nextDouble();

        System.out.print("What is your regular pay rate? ");
        double rate = sc.nextDouble();

        Employee emp = new Employee(rate);

        System.out.println("Regular pay is " + emp.getRegularPay(hours));
        System.out.println("Overtime pay is " + emp.getOvertimePay(hours));

        sc.close();
    }
}

