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

    private int manaCost;
    private int currentMana;
    private int manaPool;
    private int spellPower;
    private int hitsCount;

    //finals for special ability
    private final int MAGE_ABILITY_RANGE = 2;
    private final String MAGE_ABILITY_NAME = "Blizzar";


    ///finals special for Mage
    private final int MAGE_MANA_MULTIPLAYER = 25;
    private final int ENERGY_RAISE = 10;
    private final int MAGE_SPELL_MULTIPLAYER = 10;
    private final int MAGE_MANA_DIV = 4;

    private Mage(String name, int attack, int defense, int coolDown , int manaCost, int hitsCount, int manaPool, int spellPower) {
        super(name, attack, defense);
        this.manaPool=manaPool;
        this.currentMana = manaPool/MAGE_MANA_DIV;
        this.manaCost = manaCost;
        this.spellPower=spellPower;
        this.hitsCount=hitsCount;
        this.specialAbility= new Blizzard(MAGE_ABILITY_NAME,MAGE_ABILITY_RANGE,coolDown);
    }
    public int getHitsCount(){return hitsCount; }
    public int getCurrentMana(){return currentMana; }
    public int getSpellPower(){return spellPower; }
    public int getManaCost(){return manaCost; }
    public int getManaPool(){return manaPool; }

    public String describe (){
        String des=((Unit)this).describe();
        des+=String.format("%s\t\tManaPool: %i\t\tManaCost: %i\t\tCurrentMana: %i\t\tSpellPower: %i\t\tHitsCount: %i",getManaPool(),getManaCost(),getCurrentMana(),getSpellPower(),getHitsCount());
        return des;
    }

    @Override
    public void onTick() {

        System.out.println("not implemented Mage game tick");
    }

    @Override
    public void levelUp() {
        super.levelUp();
        //actual implement attack
        manaPool += MAGE_MANA_MULTIPLAYER*level;
        currentMana = (int) Math.min((double) currentMana+(currentMana/MAGE_MANA_DIV),(double)manaPool);
        spellPower += (MAGE_SPELL_MULTIPLAYER*level);
    }

    public void onGameTick() {
        currentMana = Math.min(manaPool, currentMana+level);
    }

    public void OnAbilityCast(List<Enemy> enemyList) throws Exception {
        boolean canCast=this.specialAbility.canCastAbility(manaCost,manaCost);
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
            while (hits < this.hitsCount && !listEnemyInRange.isEmpty()) {
                randomNumber = new Random().nextInt(listEnemyInRange.size());
                this.battle(listEnemyInRange.get(randomNumber), spellPower);
                hits++;
            }
            currentMana -= manaCost;
            hitsCount = 0;
        }
    }


}
