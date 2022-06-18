package Units.Enemies;

import Dungeons_and_Dragons.*;
import Units.Unit;

public class Enemy extends Unit {
    protected Enemy(char tile, String name, int healthCapacity, int attack, int defense) {
        super(tile, name, healthCapacity, attack, defense);
    }

    public Position getPosition() {
        return null;
    }

    @Override
    public void accept(Unit unit) {

    }
}
