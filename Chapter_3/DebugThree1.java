package Chapter_3;

// This class calculates a waitperson's tip as 15% of the bill
import java.util.Scanner;

public class DebugThree1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Ask for your check amount
        System.out.print("Enter the amount of your check >> ");
        double check1 = input.nextDouble();

        // Ask for your friend's check amount
        System.out.print("Enter the amount of your friend's check >> ");
        double check2 = input.nextDouble();

        // Calculate and show tips
        calcTip(check1);
        calcTip(check2);
    }

    // This method calculates and prints the tip
    public static void calcTip(double bill) {
        final double RATE = 0.15;
        double tip = bill * RATE;
        System.out.println("The tip should be at least $" + tip);
    }
}