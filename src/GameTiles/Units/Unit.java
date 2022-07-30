package GameTiles.Units;
import Dungeons_and_Dragons.*;
import GameTiles.DesignPatterns.Visitor;
import GameTiles.Empty;
import GameTiles.Tile;
import GameTiles.Units.Resource.Health;
import GameTiles.Wall;
import UI.MessageCallback;

import java.util.Random;

public abstract class Unit extends Tile implements Visitor {



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

    protected Unit(char tile, String name,int health, int attack, int defense) {
        super(tile);
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.health = new Health(health);


    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int pool,int amount) {
        this.health.setHealthPool(pool);
        this.health.setHealthAmount(amount);

    }

    public void setAttack(int attack) {this.attack = attack;}


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

    public void ReceiveDamage(int damage) {
        health.inflictDamage(damage);
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    protected void battle(Unit defender)
    {
        int defense= defendeRoll(defender);
        int attack= attackRoll(this);
        if(attack -defense > 0)
        {
            defender.ReceiveDamage(attack - defense);
        }
    }

    public boolean isDead() {
        return health.getHealthAmount() <= 0;
    }

    protected static int defendeRoll(Unit unit)
    {
        return new Random().nextInt(unit.getDefense());
    }

    protected static int attackRoll(Unit unit)
    {
        return new Random().nextInt(unit.getAttack());
    }

    
    public void onTick(Tile tile) {
        interact(tile);
    }



    public void interact(Tile tile){
		tile.accept(this);
    }

    public void visit(Empty empty){
        this.swap(empty);
    }
    public void visit(Wall w){}
    public void accept (Visitor v){}

    public void die(){
        Position tmp = new Position(this.position);
        //this = null
        //implement better
    }

    public void Up(Tile tile){
        onTick(tile);
    }


    public void Down(Tile tile){
        onTick(tile);
    }

    public void Left(Tile tile){
        onTick(tile);
    }

    public void Right(Tile tile){
        onTick(tile);
    }

    public void Stay(Tile tile){}

    @Override
    public String toString(){
        return tile+"";
    }
    
    public void print()
    {
        //MessageCallback.send(describe());
    }
}
