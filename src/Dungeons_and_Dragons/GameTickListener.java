package Dungeons_and_Dragons;

import java.util.ArrayList;
import java.util.Scanner;

public class GameTickListener {

    private static ArrayList<GameTicker> ListenerList = new ArrayList<>();

    public static void RegisterGameTick(GameTicker newListener)
    {
        ListenerList.add(newListener);
    }

    public void notiifyListeners ()
    {
        for (GameTicker gameTicker:ListenerList)
            gameTicker.OnGameTick();
    }

    public void getUserInput()
    {
        Scanner s= new Scanner(System.in);
        while (s.hasNextLine()) //לא בטוחה
            notiifyListeners();
    }

}
