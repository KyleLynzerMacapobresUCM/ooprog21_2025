package Chapter_7;
import java.util.*;

public class DebugSeven_2
{
   public static void main(String[] args)
   {
      String str;
      int x;
      int length;
      int num;
      int lastSpace = -1;
      int sum = 0;
      String partStr;

      Scanner in = new Scanner(System.in);
      System.out.print("Enter a series of integers separated by spaces >> ");
      str = in.nextLine();

      length = str.length();   // FIX: .len() → .length()

      for(x = 0; x < length; ++x)   // FIX: len → length
      {
         if(str.charAt(x) == ' ')
         {
            partStr = str.substring(lastSpace + 1, x);
            num = Integer.parseInt(partStr);
            System.out.println(" " + num);
            sum += num;
            lastSpace = x;
         }
      }

      // Get last number after final space
      partStr = str.substring(lastSpace + 1, length);
      num = Integer.parseInt(partStr);  // FIX: parStr → partStr
      System.out.println(" " + num);
      sum += num;   // FIX: sum = num → sum += num

      System.out.println("-------------------");
      System.out.println("The sum of the integers is " + sum);
   }
}

