package Units.Players;

import Units.Unit;

public class Player extends Unit {



    private int experience = 0;
    private int level = 1;
    protected Ability specialAbility;

    final int EXP_MULTIPLAYER =10;
    final int HEALTH_MULTIPLAYER =10;
    final int ATTACK_MULTIPLAYER =4;
    final int DEFENSE_MULTIPLAYER =4;

    protected Player(String name, int attack, int defense) {
        super(PLAYERSIGN, name, attack, defense);
        health.setHealthPool(HEALTH_MULTIPLAYER);
        health.setHealthAmount(HEALTH_MULTIPLAYER);
    }


    public void levelUp(){
        experience = experience-(EXP_MULTIPLAYER*level);
         level = level + 1;
         health.setHealthPool(HEALTH_MULTIPLAYER*level);
         health.setHealthAmount(HEALTH_MULTIPLAYER*level);
         attack =  (ATTACK_MULTIPLAYER*level);
         defense = (DEFENSE_MULTIPLAYER*level);
    }

    public void cast() throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public void accept(Unit unit) {

    }
}
