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
    public Enemy defineUnit (char c)
    {
        if(c=='s') {
            return new Monster(this.tile, "Lannister Solider", 8, 3, 80, 25, this.position, 3);
        }
        if(c=='k')
        {
                return new Monster(this.tile,"Lannister Knight",14,8,200,50,this.position,4);
        }
        if(c=='q')
        {
            return new Monster(this.tile,"Queen’sGuard",20,15,400,100,this.position,5);
        }
        if(c=='z')
        {
            return new Monster(this.tile,"Wright",30,15,600,100,this.position,3);
        }
        if(c=='b')
        {
            return new Monster(this.tile,"Bear-Wright",75,30,1000,250,this.position,4);
        }
        if(c=='b')
        {
            return new Monster(this.tile,"Giant-Wright",100,40,1500,500,this.position,5);
        }
        if(c=='w')
        {
            return new Monster(this.tile,"White Walker",150,50,2000,1000,this.position,6);
        }
        if(c=='M')
        {
            return new Monster(this.tile,"The Mountian ",60,25,1000,500,this.position,6);
        }
        if(c=='C')
        {
            return new Monster(this.tile,"Queen Cersei",10,10,100,1000,this.position,1);
        }
        if(c=='K')
        {
            return new Monster(this.tile,"Night's King",300,150,5000,5000,this.position,8);
        }
        if(c=='B')
        {
            return new Trap("Bonus Trap",1,1,1,250,1,5);
        }
        if(c=='Q')
        {
            return new Trap("Queen's Trap",50,10,250,100,3,7);
        }
        if(c=='D')
        {
            return new Trap("Death Trap",100,20,500,250,1,10);
        }
        return null;
    }

   /* public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }*/

    

}
