package Units.Enemies;

import Dungeons_and_Dragons.*;
import Units.Unit;

public class Enemy extends Unit {

    private int experience_value  ;
    public Enemy(char tile, String name, int healthCapacity, int attack, int defense, int exprience) {
        this(tile, name, healthCapacity, attack, defense,exprience,new Position());

    }
    public Enemy(char tile, String name, int healthCapacity, int attack, int defense, int exprience, Position postion )
    {
        super(tile,name,healthCapacity,attack,defense);
        this.experience_value=exprience;
    }

    public Position getPosition() {
        return null;
    }

    @Override
    public void accept(Unit unit) {
        

    }
}
