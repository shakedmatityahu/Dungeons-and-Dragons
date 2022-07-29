package GameTiles.Units;

public class Blizzard extends Ability
{


    public Blizzard(String name, int range, int coolDown) {
        super(name, range, coolDown);
    }
    public boolean canCastAbility (int mana, int cost)
    {
        if(mana< cost)
            return true;
        return false;
    }
}
