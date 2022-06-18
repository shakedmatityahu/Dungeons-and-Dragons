package Units.Players;

import Units.Unit;

public class Mage extends Player {


    protected Mage(char tile, String name, int healthCapacity, int attack, int defense) {
        super(tile, name, healthCapacity, attack, defense);
    }

    @Override
    public void accept(Unit unit) {

    }
}
