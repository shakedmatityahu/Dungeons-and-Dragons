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
        return scanner.nextInt();
    }
    public char readChar()
    {
        return scanner.next().charAt(0);
    }

}
