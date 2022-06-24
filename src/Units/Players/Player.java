package Units.Players;

import Units.Unit;

public class Player extends Unit {



    protected Player(String name, int healthCapacity, int attack, int defense) {
        super(PLAYERSIGN, name, healthCapacity, attack, defense);
    }

    @Override
    public void accept(Unit unit) {

    }
}
