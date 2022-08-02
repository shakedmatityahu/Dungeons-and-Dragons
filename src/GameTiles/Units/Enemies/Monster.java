package GameTiles.Units.Enemies;

import Dungeons_and_Dragons.GameBoard;
import Dungeons_and_Dragons.Position;
import GameTiles.Tile;
import GameTiles.Units.Players.Player;

import java.util.Random;

public class Monster extends Enemy {
    protected int vision_range;

    public Monster(char tile, String name, int attack, int defense, int healthCapacity, int experience, Position position, int vision) {
        super(tile, name, healthCapacity, attack, defense, experience, position);
        vision_range = vision;

    }

    public int getVision_range() {
        return vision_range;
    }

    @Override
    public void move(Player player, GameBoard board) {
        if (isInRange(player, vision_range))
        {
            int dx = getPosition().getX() - player.getPosition().getX();
            int dy = getPosition().getY() - player.getPosition().getY();
            if (Math.abs(dx) > Math.abs(dy))
            {
                if (dx > 0) {
                    step(board, 0);
                } else {
                    step(board, 1);
                }
            }
            else
                if (dy > 0)
                {
                step(board, 2);
                }
                else
                {
                    step(board, 3);
                }
        }
        else
        {
            step(board, new Random().nextInt(4));
        }

    }

    private void step(GameBoard board, int step) {
        Position tmp = new Position(this.getPosition());
        Tile tile;
        switch (step) {
            case 0:// up
                tmp.setX(tmp.getX() - 1);
                tile = board.get(tmp.getX(), tmp.getY());
                this.onTick(tile);
                break;
            case 1:// down
                tmp.setX(tmp.getX() + 1);
                tile = board.get(tmp.getX(), tmp.getY());
                this.onTick(tile);
                break;
            case 2://left
                tmp.setY(tmp.getY() - 1);
                tile = board.get(tmp.getX(), tmp.getY());
                this.onTick(tile);
                break;
            case 3:// right
                tmp.setY(tmp.getY() + 1);
                tile = board.get(tmp.getX(), tmp.getY());
                this.onTick(tile);
                break;
            case 4:
                break;
            default:
                throw new RuntimeException("something is fishy not a legal direction");
        }
    }

    @Override
    public int getRange() {
        return getVision_range();
    }

    public String describe (){
        String des= super.describe()+String.format("\t\tVisionRange: %d\t\tExperienceValue: %d",vision_range, getExperience_value());
        return des;
    }



}
