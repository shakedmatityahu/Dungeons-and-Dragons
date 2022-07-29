package GameTiles.Units.Players;

import GameTiles.Empty;
import GameTiles.Units.Ability;
import GameTiles.Units.Unit;

public class Rogue extends Player {

    private int cost;
    int currentEnergy;


    private final int MAX_ENERGY = 100;

    //finals for special ability
    private final int ROGUE_ABILITY_RANGE = 2;
    private final String ROGUE_ABILITY_NAME = "Fan of Knives";

    ///finals special for
    private final int ROGUE_ATTACK_MULTIPLAYER = 3;
    private final int ENERGY_RAISE = 10;

    private Rogue(String name, int attack, int defense, Ability specialAbility , int cost) {
        super(name, attack, defense);
        this.specialAbility = specialAbility;
        this.currentEnergy = MAX_ENERGY;
        this.cost = cost;
    }

    public Rogue createRogue(String name, int collDown, int cost) {
        try {
            if (collDown < 0) {
                throw new RuntimeException("Ability cool down can not be negative integer");
            } else {
                //fix ability
                Ability newAbility = new Ability(ROGUE_ABILITY_NAME, ROGUE_ABILITY_RANGE, collDown);
                Rogue newRogue = new Rogue(name, PLAYER_ATTACK_MULTIPLAYER, PLAYER_DEFENSE_MULTIPLAYER, newAbility,cost);
                return newRogue;
            }
        } catch (Exception e) {
            System.out.println("Rogue was not formed since");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void onTick()
    {
        System.out.println("not implemented Rougue game tick");
    }

    @Override
    public void levelUp() {
        super.levelUp();
        //actual implement attack
        currentEnergy = MAX_ENERGY;
        attack += (ROGUE_ATTACK_MULTIPLAYER * level);
    }

    public void onGameTick() {
        currentEnergy = Math.min(MAX_ENERGY,currentEnergy+ENERGY_RAISE);
    }

    public void OnAbilityCast() throws Exception {
        if (currentEnergy < cost) {
            throw new Exception("Casting special ability will result with Rogue death YOU MERDAERER!!!");
        } else {
            currentEnergy -= cost;
            this.health.setHealthAmount(10 * health.getHealthAmount());
            //continue implementation.
            //this.specialAbility.AvengersShield(this);
        }
    }



    public int getCost(){return cost;}
    public String describe (){
        String des=((Unit)this).describe();
        des+=String.format("%s\t\tCost: %i",getCost());
        return des;
    }
}

