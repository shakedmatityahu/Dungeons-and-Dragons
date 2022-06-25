package Units.Players;

import Units.Ability;
import Units.Unit;

public class Player extends Unit {



    protected int experience = 0;
    protected int level = 1;
    protected Ability specialAbility;

    protected static final int PLAYER_EXP_MULTIPLAYER =10;
    protected static final int PLAYER_HEALTH_MULTIPLAYER =10;
    protected static final int PLAYER_ATTACK_MULTIPLAYER =4;
    protected static final int PLAYER_DEFENSE_MULTIPLAYER =4;

    protected Player(String name, int attack, int defense) {
        super(PLAYERSIGN, name, attack, defense);
        health.setHealthPool(PLAYER_HEALTH_MULTIPLAYER);
        health.setHealthAmount(PLAYER_HEALTH_MULTIPLAYER);
    }


    public void levelUp(){
        experience = experience-(PLAYER_EXP_MULTIPLAYER*level);
         level = level + 1;
         health.setHealthPool(PLAYER_HEALTH_MULTIPLAYER*level);
         health.setHealthAmount(PLAYER_HEALTH_MULTIPLAYER*level);
         attack =  (PLAYER_ATTACK_MULTIPLAYER*level);
         defense = (PLAYER_DEFENSE_MULTIPLAYER*level);
    }

    public void OnAbilityCast() throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public void accept(Unit unit) {

    }
}
