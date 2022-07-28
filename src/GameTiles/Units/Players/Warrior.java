package GameTiles.Units.Players;
import Dungeons_and_Dragons.*;
import GameTiles.Empty;
import GameTiles.Units.*;

public class Warrior extends Player {

    //final for special ability
    private final String WARRIOR_ABILITY_NAME = "Avenger’s Shield";
    private final int WARRIOR_ABILITY_RANGE = 3;
    private final double ABILITY_COST = 0.1;
    //finals for levelUp
    private final int WARRIOR_HEALTH_MULTIPLAYER = 5;
    private final int WARRIOR_ATTACK_MULTIPLAYER = 2;
    private final int WARRIOR_DEFENSE_MULTIPLAYER = 1;

    private Warrior(String name, int attack, int defense, Ability specialAbility) {
        super(name, attack, defense);
        health.setHealthPool(PLAYER_HEALTH_MULTIPLAYER);
        health.setHealthAmount(PLAYER_HEALTH_MULTIPLAYER);
        this.specialAbility = specialAbility;
    }

    public Warrior createWarrior(int x, int y,int initHealth,String name, int collDown) {
        try {
            if (collDown < 0) {
                throw new RuntimeException("Ability cool down can not be negative integer");
            } else {
                Position newPosition = new Position(x,y);
                char charls = PLAYERSIGN;
                Health newHealth = new Health(initHealth,initHealth);


                Ability newAbility = new Ability(WARRIOR_ABILITY_NAME, WARRIOR_ABILITY_RANGE, collDown);
                Warrior newWorrior = new Warrior(name, PLAYER_ATTACK_MULTIPLAYER, PLAYER_DEFENSE_MULTIPLAYER, newAbility);
                newWorrior.initialize(newPosition);
                return newWorrior;
            }
        } catch (Exception e) {
            System.out.println("Warrior was not formed since");
            System.out.println(e.getMessage());
            return null;
        }
    }


    public void OnAbilityCast() throws Exception {
        if (health.getHealthAmount() <= health.getHealthPool() * ABILITY_COST) {
            throw new Exception("Casting special ability will result with Warrior death YOU MERDAERER!!!");
        } else {
            this.specialAbility.resetCoolDown();
            this.health.setHealthAmount(10 * health.getHealthAmount());
            //continue implementation.
            //this.specialAbility.AvengersShield(this);
        }
    }

    @Override
    public void levelUp() {
        super.levelUp();
        specialAbility.resetCoolDown();
        health.setHealthPool(health.getHealthPool() + (WARRIOR_HEALTH_MULTIPLAYER * level));
        health.setHealthAmount(health.getHealthPool());
        attack += (WARRIOR_ATTACK_MULTIPLAYER * level);
        defense += (WARRIOR_DEFENSE_MULTIPLAYER * level);
    }

    public void onGameTick() {
        this.specialAbility.UpdateCoolDown();
    }

    @Override
    public void OnGameTick() {
        super.OnGameTick();
    }
}
