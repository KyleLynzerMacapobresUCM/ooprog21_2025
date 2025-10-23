package Chapter_6;

import java.util.Scanner;

public class DebugSix1 {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int days;
        double money = 0.01;
        int day = 1;

        System.out.print("Enter number of days >> "); //added missing semicolon
        days = keyboard.nextInt();

        //Fixed loop condition(should run while current day <= entered days)
        while (day <= days) {
            System.out.println("After day " + day +
                    " you have " + money);
            money = money * 2; //double the money each day
            ++day;
        }

        keyboard.close(); //good practice to close Scanner
    }
}
