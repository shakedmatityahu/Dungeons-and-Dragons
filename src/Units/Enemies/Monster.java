package Units.Enemies;

import Dungeons_and_Dragons.Position;
import Dungeons_and_Dragons.Tile;
import Units.Players.Health;
import Units.Players.Player;

public class Monster extends Enemy {

    private int vision_range;
    public Monster(char tile, String name, int attack, Health healthCapacity, int defense, int exprience, int vision, Position position) {
        super(tile, name,healthCapacity,attack, defense,exprience);
        this.vision_range=vision;
        this.initialize(position);
    }

    public void turn (String move, Player player)
    {
        if(position.Distance(player.getPosition())<vision_range) {
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
