package GameTiles.Units.Players;

import GameTiles.DesignPatterns.Visitor;
import Dungeons_and_Dragons.GameBoard;
import GameTiles.Tile;
import GameTiles.Wall;
import UI.MessageCallback;
import GameTiles.Units.Ability;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Player extends Unit {


    ArrayList<Enemy> enemies;
    protected int experience = 0;
    protected int level = 1;
    protected boolean isAlive;
    protected Ability specialAbility;

    protected static final int PLAYER_EXP_MULTIPLAYER =10;
    protected static final int PLAYER_HEALTH_MULTIPLAYER =10;
    protected static final int PLAYER_ATTACK_MULTIPLAYER =4;
    protected static final int PLAYER_DEFENSE_MULTIPLAYER =4;

    protected Player(String name, int attack, int defense) {
        super(PLAYERSIGN, name, attack, defense);
        isAlive=true;
    }

    public static Player playerFactory(String name){
        switch (name){
            case "Jon Snow":
                return new Warrior(name,300,30,4,3);
        }
        return new Warrior("DEMO", 0,0,0,3);
    }

    public void levelUp(){
        experience = experience-(PLAYER_EXP_MULTIPLAYER*level);
         level = level + 1;
         health.setHealthPool(PLAYER_HEALTH_MULTIPLAYER*level);
         health.setHealthAmount(PLAYER_HEALTH_MULTIPLAYER*level);
         attack =  (PLAYER_ATTACK_MULTIPLAYER*level);
         defense = (PLAYER_DEFENSE_MULTIPLAYER*level);
    }

    public abstract void OnAbilityCast(List<Enemy> list)throws Exception;
    @Override
    protected void battle(Unit defender){
        super.battle(defender);
        /*if(defender.getHealth().getHealthPool()<=0)
        {
            MessageCallback.print( "you killed "+ defender.getName());
            this.addExprincePoints(this.experience);
            this.initialize(defender.getPosition());
            GameBoard.reomve(defender);
            enemies.remove(defender);
        }
        defender.*/
    }

    public void battle (Enemy enemy, int attack)
    {
            int defense= defendeRoll(enemy);
            if(attack -defense > 0)
            {
                enemy.ReceiveDamage(attack - defense);
            }
            //חסר קצת מלא
            if (experience >= PLAYER_EXP_MULTIPLAYER*level){
                levelUp();
            }

    }
    private void addExprincePoints(int experience) {
        this.experience=this.experience+experience;

    }

    public void death ()
    {
        this.isAlive=false;
        MessageCallback.send("Game Over you died");
    }

    @Override
    public void onTick(Tile tile) {
        super.onTick(tile);
        if(isDead())
        {
            death();
        }
        specialAbility.gameTick(level);
    }

    @Override
    public void accept(Visitor v)
    {
        v.visit(this);
    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit (Enemy e)
    {
        this.battle(e);
    }


}
