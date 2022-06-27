package Units.Enemies;

import Dungeons_and_Dragons.*;
import Units.Players.Player;
import Units.*;

import java.util.Random;


public abstract class Enemy extends Unit {


    protected static final int EXP_MULTIPLAYER =10;
    protected static final int EALTH_MULTIPLAYER =10;
    protected static final int ATTACK_MULTIPLAYER =4;
    protected static final int DEFENSE_MULTIPLAYER =4;

    protected static final char visible_char = '?';


    private int experience_value  ;

    public Enemy(char tile, String name, int attack,int defence,int healthCapacity,int experience ,Position position ) {
        super(tile, name, attack , defence);
        this.setHealth(healthCapacity,healthCapacity);
        this.experience_value = experience;
        this.initialize(position);


    }
    public int getAttack ()
    {
        return this.attack;
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
        return  this.position.moveMonster(moves[number]);
    }


    public Position getPosition() {
        return this.getPosition();
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

    protected void battle (Player player)
    {
        int attack= new Random().nextInt(this.getAttack());
        int defense=new Random().nextInt(player.getAttack());
        player.damage(attack-defense);
        if(player.getHealth().getHealthPool()<=0)
            System.out.println("the player died in the battle");



    }

    public int getExprince () { return this.experience_value; }

    public void damage(int damage) {
        this.getHealth().setHealthPool(this.getHealth().getHealthPool()-damage);
    }

    public void remove() {
    }
}



