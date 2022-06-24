package Units.Players;

public class Ability{
    private String name;
    private int range ;
    private int coolDown;
    private int remainingCoolDown =0;

    public Ability(String name, int range, int coolDown){
        this.name = name;
        this.range = range;
        this.coolDown = coolDown;
    }

    public String getName() {
        return name;
    }

    public int getRange() {
        return range;
    }

    public void setName(String name) {
        if(name != null)
        this.name = name;
    }

    public void setRange(int range) {
        if(range>0)
        this.range = range;
    }

    public void UpdateCoolDown(){
        if (remainingCoolDown > 0)
            remainingCoolDown--;
    }

    public void resetCoolDown(){
        remainingCoolDown = this.coolDown;
    }

    /*
    maby we will add visitor pattern
    public void AvengersShield(Warrior warrior) {

    }*/
}
