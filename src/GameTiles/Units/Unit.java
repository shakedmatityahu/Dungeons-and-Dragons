package GameTiles.Units;
import Dungeons_and_Dragons.*;
import GameTiles.DesignPatterns.Visitor;
import GameTiles.Empty;
import GameTiles.Tile;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Enemies.Monster;
import GameTiles.Units.Enemies.Trap;
import GameTiles.Units.Players.Player;
import GameTiles.Wall;
import UI.MessageCallback;

import java.util.Random;

public abstract class Unit extends Tile implements GameTicker, Visitor {



    protected String name;
    protected Health health;
    protected int attack;
    protected int defense;
    protected static final char PLAYERSIGN = '@';
    private MessageCallback messageCallback;


///*    protected void set(Position position, MessageCallback messageCallback){
//        this.messageCallback = messageCallback;
//        this.position = position;
//    }*/

    protected Unit(char tile, String name, int attack, int defense) {
        super(tile);
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        health = new Health();
        GameTickListener.RegisterGameTick(this);

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int pool,int amount) {
        this.health.setHealthPool(pool);
        this.health.setHealthAmount(amount);

       MessageCallback.send("plasd");
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public Health getHealth() {
        return health;
    }

    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
   }

    public void damage(int damage) {
        this.health.damage(damage);
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    protected void battle(Unit defender)
    {
        int defense= new Random().nextInt(defender.getAttack());
        int attack=new Random().nextInt(this.getAttack());
        if(attack -defense > 0)
        {
            defender.damage(attack - defense);
        }
    }

    public void interact(Tile tile){
		tile.accept(this);
    }

    public void visit(Empty empty){
        this.swap(empty);
    }
    public void visit(Wall w){}
    public void accept (Visitor v){}

    public int AttackerRoll()
    {
        int tmp = this.getAttack();
        int i = (RealRandom)? ( new Random().nextInt((tmp + 1))) : (tmp/2) ;

        return i;
        //return new Random().nextInt((this.getAttack()) + 1);
    }

    public int DefenderRoll(){
        return new Random().nextInt((this.getDefense()) + 1);
    }

    public void die(){
        Position tmp = new Position(this.position);
        //this = null
        //implement better
    }


   /* public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }*/

    

}
