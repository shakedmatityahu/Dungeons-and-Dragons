package Units;


import Dungeons_and_Dragons.Position;
import Dungeons_and_Dragons.Tile;
import GameTiles.Empty;
import Units.Players.Health;

import java.util.Random;

public abstract class Unit extends Tile {

    protected char tile;
    protected String name;
    protected Health health;
    protected int attack;
    protected int defense;
    protected static final char PLAYERSIGN = '@';
    protected Unit(char tile, String name, int attack, int defense) {
        super(tile);
        health = new Health();
    }

    protected  Unit (char tile ,String name, Health healthCapacity, int attack, int defense)
    {
        this.tile=tile;
        this.name=name;
        this.health=healthCapacity;
        this.attack=attack;
        this.attack=attack;
        this.defense=defense;

    }
    public String getName() {
        return name;
    }

    public Health getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int AttackerRoll()
    {
        return new Random().nextInt((this.getAttack()) + 1);
    }

    public int DefenderRoll(){
        return new Random().nextInt((this.getDefense()) + 1);
    }

    public void die(){
        Position tmp = new Position(this.position);
        //this = null
        //implement better


    }



//    protected void initialize(Position position, MessageCallback messageCallback){
//
//    }
//
//    protected int attack() throws Exception {
//        throw new Exception("TO-DO");
//    }
//
//    public int defend(){
////        ...
//    }
//
//    // Should be automatically called once the unit finishes its turn
//    public abstract void processStep();
//
//    // What happens when the unit dies
//    public abstract void onDeath();
//
//    // This unit attempts to interact with another tile.
//    public void interact(Tile tile){
////		...
//    }
//
//    public void visit(Empty e){
////		...
//    }
//
//    public abstract void visit(Player p);
//    public abstract void visit(Enemy e);
//
//    // Combat against another unit.
//    protected void battle(Unit u){
////        ...
//    }
//
//
////    public String describe() {
////        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
////    }
}
