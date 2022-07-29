package GameTiles.Units;

public class AvengersShield extends Ability
{

    public AvengersShield(String name, int range, int coolDown) {
        super(name, range, coolDown);
    }

    public boolean canCastAbilty ()
    {
        if(getCoolDown()>0)
            return true;
        return false;
    }


}
