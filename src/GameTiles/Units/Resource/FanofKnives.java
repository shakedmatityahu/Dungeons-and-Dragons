package GameTiles.Units.Resource;

import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;

public class FanofKnives extends Ability{

    private final int MAX_ENERGY = 100;
    private int cost;
    private int currentEnergy;
    public FanofKnives(String name, int range, int cost) {
        super(name, range );
        this.cost=cost;
        this.currentEnergy=MAX_ENERGY;
    }



    public boolean canCastAbility(int energy,int cost){return energy<cost;}

    @Override
    public boolean canCastAbility() {
        return currentEnergy < cost;
    }

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
        p.battle(enemy, p.getAttack());
        currentEnergy-=cost;
    }

    @Override
    public String describe() {
        return String.format("%s\t\tcost: %i\t\tcurrentEnergy: %i",cost,currentEnergy);

    }
    public int getCost() {
        return cost;
    }
}
