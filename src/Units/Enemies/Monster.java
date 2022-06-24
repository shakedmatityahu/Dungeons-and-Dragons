package Units.Enemies;

import Dungeons_and_Dragons.Tile;
import Units.Players.Player;

public class Monster extends Enemy {

    private int vision_range;
    public Monster(char tile, String name, int healthCapacity, int attack, int defense,int exprience, int vision) {
        super(tile, name, healthCapacity, attack, defense,exprience);
        this.vision_range=vision;
    }

    public void Movement (String move, Player player)
    {
        if(position.Range(player.getPosition())<vision_range) {
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
            Math.random()
        }


    }
}
