package Chapter_6;

import java.util.Scanner;

public class BankBalance2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double balance = 100;
        double rate = 0.03;
        int year = 1;
        int response;

        do {
            balance = balance + (balance * rate);
            System.out.printf("After year %d at %.2f interest rate, balance is $%.4f%n", 
            year, rate, balance);

            System.out.println();
            System.out.print("Do you want to see the balance at the end of another year?\n" +
                             "Enter 1 for yes\n" +
                             " or any other number for no >> ");
            response = input.nextInt();
            year++;
            System.out.println();

        } while (response == 1);

        input.close();
    }
}
