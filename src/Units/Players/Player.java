package Units.Players;

import Dungeons_and_Dragons.GameBoard;
import Dungeons_and_Dragons.Tile;
import UI.MessageCallback;
import Units.Ability;
import Units.Enemies.Enemy;
import Units.Unit;
import UI.MessageCallback;
import java.util.ArrayList;
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


    public void levelUp(){
        experience = experience-(PLAYER_EXP_MULTIPLAYER*level);
         level = level + 1;
         health.setHealthPool(PLAYER_HEALTH_MULTIPLAYER*level);
         health.setHealthAmount(PLAYER_HEALTH_MULTIPLAYER*level);
         attack =  (PLAYER_ATTACK_MULTIPLAYER*level);
         defense = (PLAYER_DEFENSE_MULTIPLAYER*level);
    }

    public void OnAbilityCast() throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public void accept(Unit unit) {

    }

    public void damage(int damage) {
        this.health.damage(damage);
    }

    protected void battle (Enemy enemy)
    {
        int defense= new Random().nextInt(enemy.getAttack());
        int attack=new Random().nextInt(this.getAttack());
        enemy.damage(attack-defense);
        if(enemy.getHealth().getHealthPool()<=0)
        {
            MessageCallback.print( "you killed "+ enemy.getName());
            this.addExprincePoints(this.experience);
            this.initialize(enemy.getPosition());
            GameBoard.reomve(enemy);
            enemies.remove(enemy);
        }
    }

    private void addExprincePoints(int experience) {
        this.experience=this.experience+experience;

    }

    public void death ()
    {
        this.isAlive=false;
        MessageCallback.print("Game Over you died");
    }
}
