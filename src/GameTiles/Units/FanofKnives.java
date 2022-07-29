package GameTiles.Units;

import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;

public class FanofKnives extends Ability{

    private final int MAX_ENERGY = 100;
    private int cost;
    private int currentEnergy;
    public FanofKnives(String name, int range, int cost,int currentEnergy) {
        super(name, range );
        this.cost=cost;
        this.currentEnergy=currentEnergy;
    }

    public boolean canCastAbility(int energy,int cost){return energy<cost;}

    @Override
    public void levelUp(int level) {
        currentEnergy=MAX_ENERGY;
    }

    @Override
    public void gameTick(int level) {
        currentEnergy=Math.min(currentEnergy+10,MAX_ENERGY);
    }

    @Override
    public void abilityCast(Player p, Enemy enemy)
    {
        p.battle(enemy, p.attack);
        currentEnergy-=cost;
    }

    @Override
    public String describe() {
        return String.format("%s\t\tcost: %i\t\tcurrentEnergy: %i",cost,currentEnergy);

    }
}
