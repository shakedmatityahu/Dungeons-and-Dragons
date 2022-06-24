package Units.Players;

import Units.Unit;

public class Warrior  extends Player {

    final String ABILITY_NAME = "Avenger’s Shield";
    final int ABILITY_RANGE = 3;

        protected Warrior(String name, int attack, int defense) {
        super(name, attack, defense);
            specialAbility = new Ability(ABILITY_NAME,ABILITY_RANGE);
        }



    public void cast() throws Exception {
        if(health.getHealthAmount()*0.1)
    }

    @Override
    public void accept(Unit unit) {

    }
}
