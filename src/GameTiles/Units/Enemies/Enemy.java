package GameTiles.Units.Enemies;

import GameTiles.DesignPatterns.Visitor;
import Dungeons_and_Dragons.*;
import GameTiles.Tile;
import GameTiles.Units.Players.Player;
import GameTiles.Units.*;
import UI.EnemyDeathCallBack;
import UI.MessageCallback;

import java.util.ArrayList;
import java.util.Random;


public abstract class Enemy extends Unit {



    private EnemyDeathCallBack enemyDeathCallBack;
    protected static final char visible_char = '.';


    private int experience_value;

    public Enemy(char tile, String name,int health, int attack, int defence, int experience, Position position) {
        super(tile, name,health,attack, defence);
        this.experience_value = experience;
        this.initialize(position);
    }

    public void death()
    {
        setHealth(0,0);
        enemyDeathCallBack.call(getName()+ " died.");
    }
    public void setEnemyDeathCallBack(EnemyDeathCallBack edc){
        enemyDeathCallBack = edc;
    }

    public int getExprince() {
        return experience_value;
    }

    @Override
    public void accept (Visitor v)
    {
        v.visit (this);
    }

    public void visit(Enemy e) {    }

    public void visit(Player p) {
        this.battle(p);
    }

    public int getRange(){
        return 0;
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
        if(c=='g')
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
            /*return new Trap(c,"Queen's Trap",50,10,250,100,3,7,p);*/
        }
        if(c=='Q')
        {
            return new Trap(c,"Queen's Trap",50,10,250,100,3,7,p);
        }
        if(c=='D')
        {
            return new Trap(c,"Death Trap",100,20,500,250,1,10,p);
        }
        return new Trap(c,"Unknown",100,20,500,250,1,10,p);
    }

}



