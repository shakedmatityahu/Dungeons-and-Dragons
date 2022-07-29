package GameTiles.Units;

public class FanofKnives extends Ability{
    public FanofKnives(String name, int range, int coolDown) {
        super(name, range, coolDown);
    }

    public boolean canCastAbility(int energy,int cost){return energy<cost;}
}
