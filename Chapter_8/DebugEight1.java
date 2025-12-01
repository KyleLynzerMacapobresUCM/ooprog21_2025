package Chapter_8;  

import java.util.*;

public class DebugEight1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char userCode;
        String entry;
        boolean found = false;
        char[] okayCodes = {'A', 'C', 'T', 'H'};

        System.out.println("Enter shipping code for this delivery.");
        System.out.print("Valid codes are: ");
        for (int x = 0; x < okayCodes.length; ++x) {
            System.out.print(okayCodes[x]);
            if (x != (okayCodes.length - 1)) System.out.print(", ");
        }
        System.out.print("\n>> ");

        entry = input.nextLine();

        // Guard: empty input
        if (entry == null || entry.trim().isEmpty()) {
            System.out.println("No code entered. Sorry, code not found.");
            input.close();
            return;
        }

        // use first non-space character and normalize to uppercase
        entry = entry.trim();
        userCode = Character.toUpperCase(entry.charAt(0));

        // search for code
        for (int i = 0; i < okayCodes.length; ++i) {
            if (userCode == okayCodes[i]) {
                found = true;
                break;
            }
        }

        if (found)
            System.out.println("Good code");
        else
            System.out.println("Sorry, code not found");

        input.close();
    }
}
