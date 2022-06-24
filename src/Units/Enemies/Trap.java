package Units.Enemies;

import Units.Players.Player;
import jdk.jshell.spi.ExecutionControl;

public class Trap extends Enemy {

    private int visibility_time;
    private int invisibility_time;
    private int ticks_count;
    private boolean visible;

    public Trap(char tile, String name, int healthCapacity, int attack, int defense, int experience,int visibility, int invisibility, int ticks, boolean vis) {
        super(tile, name, healthCapacity, attack, defense, experience);
        this.invisibility_time=invisibility;
        this.visibility_time=visibility;
        this.ticks_count=ticks;
        this.visible=vis;
    }
    public void changeStatus()
    {
        if(visible)
            if(ticks_count==visibility_time)
            {
                ticks_count=0;
                visible=false;
            }
        else
            if(ticks_count==invisibility_time)
            {
                ticks_count=0;
                visible=true;
            }
    }
    public void attack(Player player)
    {
            //need to implement attack
    }

    //listen to end of turn
    public void endTurn (Player player)
    {
        if(ticks_count< visibility_time)
            visible=true;
        else
            visible=false;

        if (ticks_count == (visibility_time + invisibility_time) )
                ticks_count=0;
        else
            ticks_count++;
        if(this.position.Range((player.getPosition()))<2)
            this.attack(player);
    }

    
}
