package GameTiles.Units.Players;

import GameTiles.DesignPatterns.Visitor;
import GameTiles.Tile;
import UI.MessageCallback;
import GameTiles.Units.Resource.Ability;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final int PRE_DEF_PLAYERS =7;

    protected Player(String name,int health, int attack, int defense) {
        super(PLAYERSIGN, name,health, attack, defense);
        isAlive=true;
    }

    public static Map<Integer, Player> playerFactory(){
        Map<Integer, Player> PlayerMap = new HashMap<Integer, Player>();
        PlayerMap.put(1, new Warrior("Jon Snow",300,30,4,3));
        PlayerMap.put(2, new Warrior("The Hound",400,20,6,5));
        PlayerMap.put(3, new Mage("Melisandre",100,5,1,300,30,15,5,6));
        PlayerMap.put(4, new Mage("Thoros of Myr",250,25,4,150,20,20,3,4));
        PlayerMap.put(5, new Rogue("Arya Stark",150,40,2,20));
        PlayerMap.put(6, new Rogue("",250,35,3,50));

        return PlayerMap;
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

    protected void battle(Enemy defender){
        super.battle(defender);
        if (defender.isDead())
            addExprincePoints(defender.getExprince());
            swap(defender);
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

    public void battle(Enemy enemy, int attack)
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

    protected boolean canCast(){
        return specialAbility.canCastAbility();
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
