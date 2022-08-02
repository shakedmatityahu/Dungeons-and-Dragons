package GameTiles.Units.Enemies;

import Dungeons_and_Dragons.GameBoard;
import Dungeons_and_Dragons.Position;
import GameTiles.Tile;
import GameTiles.Units.Players.Player;

import java.util.List;
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
        if (isInRange(player, vision_range)) {
            int dx;
            int dy;
            dx = Math.abs(player.getPosition().getX() - this.getPosition().getX());
            dy = Math.abs(player.getPosition().getY() - this.getPosition().getY());
            if (dx > dy)
                if (dx > 0) {
                    step(board, 1);
                } else {
                    step(board, 2);
                }
            else if (dy > 0) {
                step(board, 3);
            } else {
                step(board, 4);
            }
        } else {
            step(board, new Random().nextInt(4));
        }

    }

    private void step(GameBoard board, int step) {
        Position position = new Position(getPosition());
        Tile tile;
        switch (step) {
            case 0:// up
                position.setX(position.getX() - 1);
                tile = board.get(position.getX(), position.getY());
                this.Up(tile);
                break;
            case 1:// down
                position.setX(position.getX() + 1);
                tile = board.get(position.getX(), position.getY());
                this.Down(tile);
                break;
            case 2://left
                position.setY(position.getY() - 1);
                tile = board.get(position.getX(), position.getY());
                this.Left(tile);
                break;
            case 3:// right
                position.setY(position.getY() + 1);
                tile = board.get(position.getX(), position.getY());
                this.Right(tile);
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
