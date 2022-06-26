package Dungeons_and_Dragons;

public interface GameTicker {

    public default void OnGameTick(){
        this.toString();
    }

    public default void RegisterGameTick()
    {
        GameTickListener.RegisterGameTick(this);
    }
}
