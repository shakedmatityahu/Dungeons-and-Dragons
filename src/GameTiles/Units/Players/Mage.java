package GameTiles.Units.Players;

import GameTiles.Empty;
import GameTiles.Tile;
import GameTiles.Units.Ability;
import GameTiles.Units.Blizzard;
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

    private Mage(String name, int attack, int defense , int manaCost,int currentMana, int hitsCount, int manaPool, int spellPower) {
        super(name, attack, defense);
        this.specialAbility= new Blizzard(MAGE_ABILITY_NAME,MAGE_ABILITY_RANGE,manaPool,spellPower,hitsCount,manaCost);
    }


    public String describe (){
        String des=((Unit)this).describe();
        des+=this.specialAbility.describe();
        return des;
    }

    @Override
    public void onTick() {

        System.out.println("not implemented Mage game tick");
    }

    @Override
    public void levelUp() {
        super.levelUp();
       this.specialAbility.levelUp(level);
    }

    public void onGameTick() {
        this.specialAbility.gameTick(level);
    }

    public void OnAbilityCast(List<Enemy> enemyList) throws Exception {
        boolean canCast=this.specialAbility.canCastAbility();
        if(!canCast)
            throw new Exception("Casting special ability will result with Mage death YOU MERDAERER!!!");
         else {
            int hits = 0;
            List<Enemy> listEnemyInRange = new ArrayList<Enemy>();
            int randomNumber = 0;
            for (Enemy enemy : enemyList) {
                if (this.isInRange((Tile) enemy, MAGE_ABILITY_RANGE))
                    listEnemyInRange.add(enemy);
            }
            while (!listEnemyInRange.isEmpty())
            {
                randomNumber = new Random().nextInt(listEnemyInRange.size());
                Enemy enemy=listEnemyInRange.get(randomNumber);
                this.specialAbility.abilityCast(this,enemy);
            }

        }
    }


}
