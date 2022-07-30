package GameTiles.Units.Players;

import GameTiles.Tile;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Resource.FanofKnives;

import java.util.List;

public class Rogue extends Player {

    //finals for special ability
    private final int ROGUE_ABILITY_RANGE = 2;
    private final String ROGUE_ABILITY_NAME = "Fan of Knives";

    ///finals special for
    private final int ROGUE_ATTACK_MULTIPLAYER = 3;
    private final int ENERGY_RAISE = 10;

    public Rogue(String name,int health, int attack, int defense, int cost ) {
        super(name,health, attack, defense);
        this.specialAbility = new FanofKnives(ROGUE_ABILITY_NAME,ROGUE_ABILITY_RANGE,cost);
    }


    @Override
    public void onTick(Tile tile) {
        super.onTick(tile);
        specialAbility.gameTick(level);
    }

    @Override
    public void levelUp() {
        super.levelUp();
        attack += (ROGUE_ATTACK_MULTIPLAYER * level);
        specialAbility.levelUp(level);
    }



    public void OnAbilityCast(List<Enemy> enemyList) throws Exception {
        if (!canCast()){
            throw new Exception("Casting special ability will result with Rogue death YOU MERDAERER!!!");
        } else {
            for (Enemy enemy : enemyList) {
                if ((isInRange((Tile) enemy, ROGUE_ABILITY_RANGE)) && canCast())
                    this.specialAbility.abilityCast(this,enemy);
            }
        }
    }

    public String describe (){
        String des= super.describe();
        des+=String.format("%s\t\tCost: %i");
        return des;
    }
}

