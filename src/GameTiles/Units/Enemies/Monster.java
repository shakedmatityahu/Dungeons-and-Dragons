package GameTiles.Units.Enemies;

import Dungeons_and_Dragons.Position;
import GameTiles.DesignPatterns.Visitor;
import GameTiles.Units.Unit;
import GameTiles.Wall;

import GameTiles.Units.Players.Player;

public class Monster extends Enemy {

    private int vision_range;


    public Monster(char tile, String name, int attack, int defense, int healthCapacity, int experience, Position position, int vision) {
        super(tile, name, attack, defense, healthCapacity, experience, position);
        vision_range = vision;

    }

  public Monster(char tile, String name, int attack, int healthCapacity, int defense,int experience, int vision) {
      super(tile, name, healthCapacity, attack, defense,experience,new Position());
      this.vision_range = vision;
  }

    public void turn (String move, Player player)
    {
        if(this.Distance(player)<vision_range) {
            int dx;
            int dy;
            dx = Math.abs(player.getPosition().getX() - this.getPosition().getX());
            dy = Math.abs(player.getPosition().getY() - this.getPosition().getY());
            if (dx > dy)
                if (dx > 0)
                    move = "left";
                else
                    move = "right";
            else if (dy > 0)
                move = "up";
            else
                move = "down";

            this.position.moveMonster(move);
        }
        else
            this.initialize(new Position(this.rollMove()) );
    }

    public void OnGameTick(){
        //????need TO-DO
        //this.turn(); //צריך לשלוח גם את התזוזה וגם את השחקן אני חושבת..?

    }
    public int getVision_range(){return vision_range;}

    @Override
    public void onTick(Player player) {
        System.out.println("not implemented M game tick");
    }

    public String describe (){
        String des=((Unit)this).describe();
        des+=String.format("%s\t\tVisionRange: %i\t\tExperienceValue: %i",getVision_range(),getExprince());
        return des;
    }



}
