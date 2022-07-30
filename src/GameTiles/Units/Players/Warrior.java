package GameTiles.Units.Players;
import Dungeons_and_Dragons.*;
import GameTiles.Empty;
import GameTiles.Tile;
import GameTiles.Units.*;
import GameTiles.Units.Enemies.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player {

    //final for special ability
    private final String WARRIOR_ABILITY_NAME = "Avenger’s Shield";
    private final int WARRIOR_ABILITY_RANGE = 3;
    //finals for levelUp
    private final int WARRIOR_HEALTH_MULTIPLAYER = 5;
    private final int WARRIOR_ATTACK_MULTIPLAYER = 2;
    private final int WARRIOR_DEFENSE_MULTIPLAYER = 1;

    public Warrior(String name, int Health, int attack, int defense,int coolDown) {
        super(name, attack, defense);
        health = new Health(Health);
        specialAbility = new AvengersShield(WARRIOR_ABILITY_NAME,WARRIOR_ABILITY_RANGE,coolDown);
    }

    public void OnAbilityCast(List<Enemy> enemyList) throws Exception {
        boolean abilityCast= specialAbility.canCastAbility();
        if(!abilityCast)
            throw new Exception("Casting special ability will result with Warrior death YOU MERDAERER!!!");
        List<Enemy> listEnemyInRange = new ArrayList<Enemy>();
        int randomNumber = 0;
        for (Enemy enemy : enemyList) {
            if (this.isInRange((Tile) enemy, WARRIOR_ABILITY_RANGE))
                listEnemyInRange.add(enemy);
        }
            randomNumber = new Random().nextInt(listEnemyInRange.size());
            Enemy enemy=listEnemyInRange.get(randomNumber);
            this.specialAbility.abilityCast(this,enemy);
            this.health.setHealthAmount(Math.min(this.health.getHealthPool() + 10 * this.defense,this.health.getHealthPool()));
    }

    @Override
    public void onTick(Tile tile) {
        super.onTick(tile);
        specialAbility.gameTick(level);
    }

    @Override
    public void levelUp() {
        super.levelUp();
        specialAbility.levelUp(level);
        health.setHealthPool(health.getHealthPool() + (WARRIOR_HEALTH_MULTIPLAYER * level));
        health.setHealthAmount(health.getHealthPool());
        attack += (WARRIOR_ATTACK_MULTIPLAYER * level);
        defense += (WARRIOR_DEFENSE_MULTIPLAYER * level);
    }


    public String describe (){
        String des=((Unit)this).describe();
        des+=this.specialAbility.describe();
        return des;
    }
}
