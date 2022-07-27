package Units.Players;

import GameTiles.Empty;
import Units.Ability;
import Units.Unit;

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

    private Mage(String name, int attack, int defense, Ability specialAbility , int manaCost) {
        super(name, attack, defense);
       /* this.specialAbility = specialAbility;
        this.currentMana = MAX_ENERGY;
        this.manaCost = manaCost;
        change all
        */
    }

    public Mage createMage(String name, int collDown, int manaCost) {
        /*try {
            if (collDown < 0) {
                throw new RuntimeException("Ability cool down can not be negative integer");
            } else {
                //fix ability
                Ability newAbility = new Ability(MAGE_ABILITY_NAME, MAGE_ABILITY_RANGE, collDown);
                Mage newMage = new Mage(name, PLAYER_ATTACK_MULTIPLAYER, PLAYER_DEFENSE_MULTIPLAYER, newAbility,manaCost);
                return newMage;
            }
        } catch (Exception e) {
            System.out.println("Mage was not formed since");
            System.out.println(e.getMessage());*/
            return null;
        //}
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

    public void OnAbilityCast() throws Exception {
        if (currentMana < manaCost) {
            throw new Exception("Casting special ability will result with Mage death YOU MERDAERER!!!");
        } else {
            currentMana -= manaCost;
            hitsCount = 0;
            //continue implementation.
            //this.specialAbility.AvengersShield(this);
        }
    }

    @Override
    public void accept(Unit unit) {

        //not sure what this shoud do
    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit(Empty empty) {

    }

    @Override
    public void OnGameTick() {
        super.OnGameTick();
    }
}
