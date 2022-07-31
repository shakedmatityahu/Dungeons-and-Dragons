package GameTiles.Units.Players;

import GameTiles.Tile;
import GameTiles.Units.Resource.Blizzard;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mage extends Player {


    //finals for special ability
    private final int MAGE_ABILITY_RANGE = 2;
    private final String MAGE_ABILITY_NAME = "Blizzar";


    ///finals special for Mage
    private final int MAGE_MANA_MULTIPLAYER = 25;
    private final int ENERGY_RAISE = 10;
    private final int MAGE_SPELL_MULTIPLAYER = 10;
    private final int MAGE_MANA_DIV = 4;

    public Mage(String name,int helth, int attack, int defense , int manaPool, int manaCost, int spellPower, int hitsCount ,int Range) {
        super(name,helth ,attack, defense);
        this.specialAbility= new Blizzard(MAGE_ABILITY_NAME,Range,manaPool,spellPower,hitsCount,manaCost);
    }

    @Override
    public void onTick(Tile tile) {
        super.onTick(tile);
        specialAbility.gameTick(level);
    }

    @Override
    public void levelUp() {
        super.levelUp();
       this.specialAbility.levelUp(level);
    }

    public void OnAbilityCast(List<Enemy> enemyList) throws Exception {
         if(!canCast())
            throw new Exception("Casting special ability will result with Mage death YOU MERDAERER!!!");
         else {
            List<Enemy> listEnemyInRange = new ArrayList<Enemy>();
            for (Enemy enemy : enemyList) {
                if (this.isInRange((Tile) enemy, MAGE_ABILITY_RANGE))
                    listEnemyInRange.add(enemy);
            }
            specialAbility.abilityCast(this,listEnemyInRange);



        }
    }
    public String describe (){
        return super.describe();
    }


}
