package GameTiles.Units.Players;
import Dungeons_and_Dragons.*;
import GameTiles.Empty;
import GameTiles.Tile;
import GameTiles.Units.*;
import GameTiles.Units.Enemies.Enemy;

import java.util.List;

public class Warrior extends Player {

    //final for special ability
    private final String WARRIOR_ABILITY_NAME = "Avenger’s Shield";
    private final int WARRIOR_ABILITY_RANGE = 3;
    private final double ABILITY_COST = 0.1;
    //finals for levelUp
    private final int WARRIOR_HEALTH_MULTIPLAYER = 5;
    private final int WARRIOR_ATTACK_MULTIPLAYER = 2;
    private final int WARRIOR_DEFENSE_MULTIPLAYER = 1;

    private Warrior(String name, int attack, int defense, int coolDown) {
        super(name, attack, defense);
        health.setHealthPool(PLAYER_HEALTH_MULTIPLAYER);
        health.setHealthAmount(PLAYER_HEALTH_MULTIPLAYER);
        this.specialAbility = new AvengersShield(WARRIOR_ABILITY_NAME,WARRIOR_ABILITY_RANGE,coolDown);
    }
    public Warrior(String name, int Health, int attack, int defense) {
        super(name, attack, defense);
        health.setHealthPool(Health);
        health.setHealthAmount(Health);
        //fill special ability
        this.specialAbility = null;
    }



    public Warrior createWarrior(int x, int y,int initHealth,String name, int collDown) {
        try {
            if (collDown < 0) {
                throw new RuntimeException("Ability cool down can not be negative integer");
            } else {
                Position newPosition = new Position(x,y);
                char charls = PLAYERSIGN;
                Health newHealth = new Health(initHealth,initHealth);
                Warrior newWarrior = new Warrior(name, PLAYER_ATTACK_MULTIPLAYER, PLAYER_DEFENSE_MULTIPLAYER, collDown);
                newWarrior.initialize(newPosition);
                return newWarrior;
            }
        } catch (Exception e) {
            System.out.println("Warrior was not formed since");
            System.out.println(e.getMessage());
            return null;
        }
    }


    public void OnAbilityCast(List<Enemy> enemyList) throws Exception {
        boolean abilityCast=this.specialAbility.canCastAbility();
        if(!abilityCast)
            throw new Exception("Casting special ability will result with Warrior death YOU MERDAERER!!!");
        boolean cast=false;
        for(Enemy enemy :enemyList) {
            if (this.isInRange((Tile) enemy, WARRIOR_ABILITY_RANGE)) {
                this.battle(enemy, (int) ABILITY_COST * health.getHealthPool());
                if (health.getHealthAmount() + 10 * this.defense <= health.getHealthPool())
                    this.health.setHealthAmount(health.getHealthPool() + 10 * this.defense);
                else
                    this.specialAbility.resetCoolDown();
                cast = true;
            }
            if (cast)
                break;
        }

    }

    @Override
    public void onTick() {
        System.out.println("not implemented w game tick");
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



    public int getCoolDown(){return this.specialAbility.getCoolDown();}
    public String describe (){
        String des=((Unit)this).describe();
        des+=String.format("%s\t\tCoolDown: %i",getCoolDown());
        return des;
    }
}
