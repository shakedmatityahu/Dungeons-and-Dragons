package Units.Players;

import Units.Unit;

public class Warrior  extends Player {

    private Health Heath;


    protected Warrior(char tile, String name, int healthCapacity, int attack, int defense) {
        super(tile, name, healthCapacity, attack, defense);
    }

    @Override
    public void accept(Unit unit) {

    }
}
