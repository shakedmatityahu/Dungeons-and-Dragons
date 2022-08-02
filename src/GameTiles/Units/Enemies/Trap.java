package GameTiles.Units.Enemies;

import Dungeons_and_Dragons.Position;
import GameTiles.Empty;
import GameTiles.Tile;
import GameTiles.Units.Unit;

public class Trap extends Enemy {


    private int visibility_time;
    private int invisibility_time;
    private int ticks_count=0;
    private boolean visible=true;

    public Trap(char c,String name, int attack, int defense, int healthCapacity, int experience,int visibility_time,int invisibility_time, Position position) {
        super(c, name,healthCapacity, attack, defense, experience, position);
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
        String des=super.describe();
        des+=String.format("\t\tExperienceValue: %d\t\tVisibilityTime: %d\t\tInvisibilityTime: %d", getExperience_value(),getVisibilityTime(),getInVisibilityTime());
        return des;
    }

    @Override
    public void visit(Empty empty){}
    @Override
    public String toString() {
        return visible ? String.valueOf(tile) : ".";
    }
}
