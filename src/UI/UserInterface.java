package UI;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public void print (String msg)
    {
        System.out.println(msg);
    }
    public int readInt()
    {
        try {
            scanner = new Scanner(System.in);
            return scanner.nextInt();
        }
        catch (Exception e) {
            return readInt();
        }
    }
    public char readChar()
    {
        try {
            scanner = new Scanner(System.in);
            String input = scanner.next().toString();
            if(input.length() == 1 )
                return input.charAt(0);
            else
                throw new RuntimeException("");
        }
        catch (Exception e){
            return readChar();
        }

    }

}
