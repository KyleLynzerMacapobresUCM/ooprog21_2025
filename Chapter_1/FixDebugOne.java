package Chapter_1;

public class FixDebugOne
{

   public static void main(String[] args)   
   {
      System.out.println("Hello World!");
   }
}
/*
Listed spotted bugs below:
Bugs:
1. Class name has a space → `FixDebug One` should be `FixDebugOne`.
2. Method name `Main` should be lowercase → `main`.
3. Method parameter should be `String[] args` not `String args`.
4. Typo in `Systm` → should be `System`.
5. Missing semicolon `;` after `System.out.println("Hello World!")`.
6. Missing closing brace `}` for the class.
*/
