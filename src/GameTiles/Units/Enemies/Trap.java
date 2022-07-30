package GameTiles.Units.Enemies;

import Dungeons_and_Dragons.Position;
import GameTiles.Empty;
import GameTiles.Tile;
import GameTiles.Units.Unit;
import GameTiles.Wall;
import GameTiles.Units.Players.Player;

public class Trap extends Enemy {


    private int visibility_time;
    private int invisibility_time;
    private int ticks_count=0;
    private boolean visible=true;

    public Trap(char c,String name, int attack, int defence, int healthCapacity, int experience,int visibility_time,int invisibility_time, Position position) {
        super(c, name, attack, defence, healthCapacity, experience, position);
        this.visibility_time=visibility_time;
        this.invisibility_time=invisibility_time;
    }

    public boolean isVisible()
    {
        return this.visible;

    }

    @Override
    protected void battle(Unit defender){}

    @Override
    public void onTick(Tile tile)
    {
        visible = ticks_count < visibility_time;
        if (ticks_count == (visibility_time + invisibility_time) )
            ticks_count=0;
        else
            ticks_count++;
        if(isInRange(tile,2))
        {
            interact(tile);
        }
    }

    @Override
    public int getRange() {
        return 0;
    }

    public int getVisibilityTime(){return visibility_time;}
    public int getInVisibilityTime(){return invisibility_time;}

    public String describe (){
        String des=((Unit)this).describe();
        des+=String.format("%s\t\tExperienceValue: %i\t\tVisibilityTime: %i\t\tInvisibilityTime: %i",getExprince(),getVisibilityTime(),getInVisibilityTime());
        return des;
    }

    @Override
    public void visit(Empty empty){}
    @Override
    public String toString() {
        return visible ? String.valueOf(tile) : ".";
    }
}
