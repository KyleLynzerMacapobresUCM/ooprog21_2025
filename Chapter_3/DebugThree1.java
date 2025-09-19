package Chapter_3;


import java.util.Scanner;

public class DebugThree1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

       
        System.out.print("Enter the amount of your check >> ");
        double check1 = input.nextDouble();

       
        System.out.print("Enter the amount of your friend's check >> ");
        double check2 = input.nextDouble();

     
        calcTip(check1);
        calcTip(check2);
    }

    
    public static void calcTip(double bill) {
        final double RATE = 0.15;
        double tip = bill * RATE;
        System.out.println("The tip should be at least $" + tip);
    }
}