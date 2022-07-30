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
    public boolean canCastAbility ()
    {
        if(coolDown>0)
            return true;
        return false;
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
        int randomNumber= p.randomNumber(enemyList.size());
        Enemy enemy=enemyList.get(randomNumber);
        remainingCoolDown=coolDown;
        p.battle(enemy, (int) ABILITY_COST * p.getHealth().getHealthPool());

    }
    public String describe(){
        return String.format("%s\t\tCoolDown: %i",coolDown);

    }
}
