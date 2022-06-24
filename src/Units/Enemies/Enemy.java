package Units.Enemies;

import Dungeons_and_Dragons.*;
import Units.Unit;

public class Enemy extends Unit {

    private int experience_value  ;
    public Enemy(char tile, String name, int healthCapacity, int attack, int defense, int experience) {
        this(tile, name, healthCapacity, attack, defense,experience,new Position());

    }
    public Enemy(char tile, String name, int healthCapacity, int attack, int defense, int experience, Position postion )
    {
        super(tile,name,healthCapacity,attack,defense);
        this.experience_value=experience;
    }

    public Position getPosition() {
        return null;
    }

    @Override
    public void accept(Unit unit) {
        

    }
}
