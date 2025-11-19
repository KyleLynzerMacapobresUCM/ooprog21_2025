package Chapter_7;
import java.util.*;

public class DebugSeven2 {
    public static void main(String[] args) {
        String str;
        int x;
        int length;
        int lastSpace = -1;
        int sum = 0;
        String partStr;

        Scanner in = new Scanner(System.in);
        System.out.print("Enter a series of integers separated by spaces >> ");
        str = in.nextLine();
        length = str.length();  // Fixed .len() → .length()

        for (x = 0; x < length; ++x) {  // Fixed len → length
            if (str.charAt(x) == ' ') {
                partStr = str.substring(lastSpace + 1, x);
                int num = Integer.parseInt(partStr);
                System.out.println(" " + num);
                sum += num;  // Accumulate sum
                lastSpace = x;
            }
        }

        // Handle the last number after the final space
        partStr = str.substring(lastSpace + 1, length);
        int num = Integer.parseInt(partStr);  // Fixed typo parStr → partStr
        System.out.println(" " + num);
        sum += num;  // Add to sum instead of overwriting

        System.out.println("-------------------" +
                "\nThe sum of the integers is " + sum);
    }
}

