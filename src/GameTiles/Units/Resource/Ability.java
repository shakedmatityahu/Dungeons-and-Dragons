package GameTiles.Units.Resource;

import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;

import java.util.List;

public abstract class Ability{
    private String name;
    private int range ;


    public Ability(String name, int range){
        this.name = name;
        this.range = range;

    }

    public String getName() {
        return name;
    }

    public int getRange() {
        return range;
    }

    public void setName(String name) {
        if(name != null)
        this.name = name;
    }

    public void setRange(int range) {
        if(range>0)
        this.range = range;
    }




    /*
    maby we will add visitor pattern
    public void AvengersShield(Warrior warrior) {

    }*/
    public abstract boolean canCastAbility();



    public abstract void levelUp(int level);
    public abstract  void gameTick(int level);
    public abstract void abilityCast(Player p, List<Enemy> enemy);

    public abstract String describe();

}
