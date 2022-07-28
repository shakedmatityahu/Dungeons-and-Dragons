package Dungeons_and_Dragons;

public class Main {
    public static void main (String[] args)
    {
        GameController gc=new GameController ();
        gc.startGame(args[0]);
    }
}
