package Chapter_7;

import java.util.Scanner;

public class CompareStrings {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name > ");
        String name = input.nextLine();

        if (name.equals("Carmen")) {
            System.out.println("Carmen equals " + name);
        } else {
            System.out.println("Carmen does not equal " + name);
        }
    }
}
