package GameTiles.Units.Enemies;

import GameTiles.DesignPatterns.Visitor;
import Dungeons_and_Dragons.*;
import GameTiles.Empty;
import GameTiles.Units.Players.Player;
import GameTiles.Units.*;
import UI.EnemyDeathCallBack;

import java.util.Random;


public abstract class Enemy extends Unit {


    protected static final int EXP_MULTIPLAYER = 10;
    protected static final int EALTH_MULTIPLAYER = 10;
    protected static final int ATTACK_MULTIPLAYER = 4;
    protected static final int DEFENSE_MULTIPLAYER = 4;
    private EnemyDeathCallBack enemyDeathCallBack;

    protected static final char visible_char = '?';


    private int experience_value;

    public Enemy(char tile, String name, int attack, int defence, int healthCapacity, int experience, Position position) {
        super(tile, name, attack, defence);
        this.setHealth(healthCapacity, healthCapacity);
        this.experience_value = experience;
        this.initialize(position);
    }
    public Enemy(char tile, String name, int attack, int defence, int healthCapacity, int experience) {
        super(tile, name, attack, defence);
        this.setHealth(healthCapacity, healthCapacity);
        this.experience_value = experience;

    }

    public int getAttack() {
        return this.attack;
    }

    public Position rollMove() {
        String[] moves = new String[4];
        moves[0] = "left";
        moves[1] = "right";
        moves[2] = "up";
        moves[3] = "down";
        moves[4] = "Stay";
        Random random = new Random();
        int number = random.nextInt(moves.length);
        return this.position.moveMonster(moves[number]);
    }


    public Position getPosition() {
        return this.getPosition();
    }

    public void setEnemyDeathCallBack(EnemyDeathCallBack edc){
        enemyDeathCallBack = edc;
    }

    public void setTile(char tile) {

    }

    public void attack(Player player) {
        //super.attack(player);
        //אם השחקן מת להוציא הודעה שהוא מת
    }

    protected void battle(Player player) {
        int attack = new Random().nextInt(this.getAttack());
        int defense = new Random().nextInt(player.getAttack());
        player.damage(attack - defense);
        if (player.getHealth().getHealthPool() <= 0)
            player.death();

    }


    public int getExprince() {
        return this.experience_value;
    }

    public void damage(int damage) {
        this.getHealth().setHealthPool(this.getHealth().getHealthPool() - damage);
    }


    public void visit(Enemy e) {    }

    public void visit(Player p) {
        this.battle(p);
    }

    @Override
    public void accept (Visitor v)
    {
        v.visit (this);
    }



}



