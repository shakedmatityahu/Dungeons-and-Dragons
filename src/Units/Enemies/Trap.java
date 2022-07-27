package Units.Enemies;

import Dungeons_and_Dragons.Position;
import GameTiles.Wall;
import Units.Health;
import Units.Players.Player;

public class Trap extends Enemy {


    private int visibility_time;
    private int invisibility_time;
    private int ticks_count=0;
    private boolean visible=true;
    private char invisible_char='.';

    public Trap( String name, int attack, int defence, int healthCapacity, int experience, Position position) {
        super(visible_char, name, attack, defence, healthCapacity, experience, position);
    }



/*    public Trap(char tile, String name, Health healthCapacity, int attack, int defense, int experience, int visibility, int invisibility, int ticks, Position p) {
        super(tile, name, attack, healthCapacity,defense, experience);
        this.invisibility_time=invisibility;
        this.visibility_time=visibility;
        this.ticks_count=ticks;
    }*/


    public boolean isVisible()
    {
        return this.visible;
    }
    public void changeStatus()
    {
        if(visible)
            if(ticks_count==visibility_time)
            {
                ticks_count=0;
                visible=false;
                setTile(invisible_char);
            }
        else
            if(ticks_count==invisibility_time)
            {
                ticks_count=0;
                visible=true;
                setTile(visible_char);
            }
    }

    @Override
    public void visit(Wall w) {

    }

    public void setTile(char tile)
    {
        super.setTile(tile);
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
        if(this.position.Distance((player.getPosition()))<2)
            this.attack(player);
    }

    public void OnGameTick(){

        // call the method end_turn (player)
    }
    
}
