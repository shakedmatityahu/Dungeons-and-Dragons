package Units.Enemies;

import Dungeons_and_Dragons.Position;
import Dungeons_and_Dragons.Tile;
import Units.Health;

import Units.Players.Player;

public class Monster extends Enemy {

    private int vision_range;


    public Monster(char tile, String name, int attack, int defence, int healthCapacity, int experience, Position position, int vision) {
        super(tile, name, attack, defence, healthCapacity, experience, position);
        vision_range = vision;

    }



  public Monster(char tile, String name, int attack, int healthCapacity, int defense,int experience, int vision) {
      super(tile, name, healthCapacity, attack, defense,experience,new Position());
      this.vision_range = vision;
  }

    public void turn (String move, Player player)
    {
        if(this.getTile().Distance(player.getTile())<vision_range) {
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
        }
        else
            this.initialize(new Position(this.rollMove()) );



    }


}
