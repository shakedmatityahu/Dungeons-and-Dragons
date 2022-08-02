package GameTiles.Units.Resource;

import GameTiles.Tile;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;

import java.util.ArrayList;
import java.util.List;

public class AvengersShield extends Ability
{

    private final double ABILITY_COST = 0.1;

    private int coolDown;
    private int remainingCoolDown =0;
    public AvengersShield(String name, int range, int coolDown) {
        super(name, range);
        this.coolDown=coolDown;

    }


    @Override
    public Ability clone() {
        Ability copy= new AvengersShield(getName(),getRange(),coolDown);
        return copy;
    }

    @Override
    public boolean canCastAbility ()
    {
        if(remainingCoolDown>0)
            return false;
        return true;
    }

    @Override
    public void levelUp(int level) {
        this.remainingCoolDown=0;
    }

    @Override
    public void gameTick(int level) {
        if (remainingCoolDown > 0)
            remainingCoolDown--;
    }

    @Override
    public void abilityCast(Player p, List<Enemy> enemyList)
    {
        if (!enemyList.isEmpty()) {
            int randomNumber = p.randomNumber(enemyList.size());
            Enemy enemy = enemyList.get(randomNumber);
            remainingCoolDown=coolDown;
            int damage = (int)(p.getHealth().getHealthPool()*ABILITY_COST);
            p.castAbility(enemy, damage);
        }
        else
            remainingCoolDown=coolDown;

    }
    private int getCoolDown(){
        return coolDown;
    }
    public String describe(){
        return "CoolDown:"+remainingCoolDown+ "/"+ coolDown;

    }

}
