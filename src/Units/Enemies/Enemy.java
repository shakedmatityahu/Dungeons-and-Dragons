package Units.Enemies;

import Dungeons_and_Dragons.*;
import Units.Players.Health;
import Units.Players.Player;
import Units.Unit;

import java.util.Random;

public class Enemy extends Unit {

    protected static final int EXP_MULTIPLAYER =10;
    protected static final int EALTH_MULTIPLAYER =10;
    protected static final int ATTACK_MULTIPLAYER =4;
    protected static final int DEFENSE_MULTIPLAYER =4;


    private int experience_value  ;
    public Enemy(char tile, String name,Health healthCapacity, int attack, int defense, int experience) {
        this(tile, name,attack,healthCapacity, defense,experience,new Position());
    }
    protected Enemy(char tile, String name, int attack, Health healthCapacity, int defense, int experience, Position postion ) {
        super(tile, name,healthCapacity, attack, defense);
        this.experience_value = experience;
        this.position=postion;

    }

    public Position rollMove ()
    {
        String[] moves= new String[4];
        moves[0]="left";
        moves[1]="right";
        moves[2]="up";
        moves[3]="down";
        moves[4]="Stay";
        Random random= new Random();
        int number= random.nextInt(moves.length);
        return  this.position.move(moves[number]);
    }


    public Position getPosition() {
        return position;
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
