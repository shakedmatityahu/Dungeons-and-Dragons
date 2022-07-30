package Dungeons_and_Dragons;

import UI.UserInterface;

public class Main {

    public static void main(String[] args)
    {
        System.out.println("Hello World");


        GameController gc=new GameController("levels_dir");
        gc.play();
    }
}
