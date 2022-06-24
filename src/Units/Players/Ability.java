package Units.Players;

public class Ability{
    private String name;
    private int range ;

    public Ability(String name, int range){
        this.name = name;
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public int getRange() {
        return range;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
