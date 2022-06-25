package Units.Enemies;

import Dungeons_and_Dragons.*;
import Units.Players.Player;
import Units.*;

public class Enemy extends Unit {

    protected static final int EXP_MULTIPLAYER =10;
    protected static final int EALTH_MULTIPLAYER =10;
    protected static final int ATTACK_MULTIPLAYER =4;
    protected static final int DEFENSE_MULTIPLAYER =4;

    private int experience_value  ;
    public Enemy(char tile, String name,Health healthCapacity, int attack, int defense, int experience) {
        this(tile, name,attack,healthCapacity, defense,experience,new Position());
    }
    public Enemy(char tile, String name, int attack, Health healthCapacity, int defense, int experience, Position postion ) {
        super(tile, name,healthCapacity, attack, defense);
        this.experience_value = experience;

    }

    public Position getPosition() {
        return null;
    }

    @Override
    public void accept(Unit unit) {
        

    }
    public void setTile(char tile)
    {

    }
    public void attack (Player player)
    {
        //super.attack(player);
        //אם השחקן מת להוציא הודעה שהוא מת
    }
}
