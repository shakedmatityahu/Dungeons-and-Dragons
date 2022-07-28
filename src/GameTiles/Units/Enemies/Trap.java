package GameTiles.Units.Enemies;

import Dungeons_and_Dragons.Position;
import GameTiles.Units.Unit;
import GameTiles.Wall;
import GameTiles.Units.Players.Player;

public class Trap extends Enemy {


    private int visibility_time;
    private int invisibility_time;
    private int ticks_count=0;
    private boolean visible=true;
    private char invisible_char='.';

    public Trap( String name, int attack, int defence, int healthCapacity, int experience,int visibility_time,int invisibility_time, Position position) {
        super(visible_char, name, attack, defence, healthCapacity, experience, position);
        this.visibility_time=visibility_time;
        this.invisibility_time=invisibility_time;
    }
    public Trap( String name, int attack, int defence, int healthCapacity, int experience,int visibility_time,int invisibility_time) {
        super(visible_char, name, attack, defence, healthCapacity, experience);
        this.visibility_time=visibility_time;
        this.invisibility_time=invisibility_time;
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
    public int getVisibilityTime(){return visibility_time;}
    public int getInVisibilityTime(){return invisibility_time;}
    public String describe (){
        String des=((Unit)this).describe();
        des+=String.format("%s\t\tExperienceValue: %i\t\tVisibilityTime: %i\t\tInvisibilityTime: %i",getExprince(),getVisibilityTime(),getInVisibilityTime());
        return des;
    }
    @Override
    public String toString() {
        if (ticks_count<visibility_time)
            return String.valueOf(tile);
        else
            return ".";
        }

}
