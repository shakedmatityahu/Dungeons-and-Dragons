package Dungeons_and_Dragons;

import UI.UserInterface;

public class Main {

    public static void main(String[] args)
    {
        //For testing
        GameController gc=new GameController("levels_dir");
        gc.play();

        //real
        /*GameController gc=new GameController(args[0]);
        gc.play();*/
    }
}
