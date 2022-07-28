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


    public static Enemy enemyFactory(char c, Position p)
    {
        if(c=='s') {
            return new Monster(c, "Lannister Solider", 8, 3, 80, 25, p, 3);
        }
        if(c=='k')
        {
            return new Monster(c,"Lannister Knight",14,8,200,50,p,4);
        }
        if(c=='q')
        {
            return new Monster(c,"Queen’sGuard",20,15,400,100,p,5);
        }
        if(c=='z')
        {
            return new Monster(c,"Wright",30,15,600,100,p,3);
        }
        if(c=='b')
        {
            return new Monster(c,"Bear-Wright",75,30,1000,250,p,4);
        }
        if(c=='b')
        {
            return new Monster(c,"Giant-Wright",100,40,1500,500,p,5);
        }
        if(c=='w')
        {
            return new Monster(c,"White Walker",150,50,2000,1000,p,6);
        }
        if(c=='M')
        {
            return new Monster(c,"The Mountian ",60,25,1000,500,p,6);
        }
        if(c=='C')
        {
            return new Monster(c,"Queen Cersei",10,10,100,1000,p,1);
        }
        if(c=='K')
        {
            return new Monster(c,"Night's King",300,150,5000,5000,p,8);
        }
        if(c=='B')
        {
            return new Trap(c,"Bonus Trap",1,1,1,250,1,5,p);
        }
        if(c=='Q')
        {
            return new Trap(c,"Queen's Trap",50,10,250,100,3,7,p);
        }
        if(c=='D')
        {
            return new Trap(c,"Death Trap",100,20,500,250,1,10,p);
        }
        return null;
    }



}



