package Dungeons_and_Dragons;

import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;

public class GameTickListener {

    private static ArrayList<GameTicker> ListenerList = new ArrayList<>();

    public static void RegisterGameTick(GameTicker newListener)
    {
        ListenerList.add(newListener);
    }


}
