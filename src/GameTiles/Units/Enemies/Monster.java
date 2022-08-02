package GameTiles.Units.Enemies;

import Dungeons_and_Dragons.Position;

public class Monster extends Enemy {
    protected int vision_range;

    public Monster(char tile, String name, int attack, int defense, int healthCapacity, int experience, Position position, int vision) {
        super(tile, name,healthCapacity, attack, defense, experience, position);
        vision_range = vision;

    }

    public int getVision_range(){return vision_range;}

    @Override
    public int getRange() {
        return getVision_range();
    }

    public String describe (){
        String des= super.describe()+String.format("\t\tVisionRange: %d\t\tExperienceValue: %d",vision_range, getExperience_value());
        return des;
    }



}
