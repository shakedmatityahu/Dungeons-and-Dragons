package GameTiles;
import GameTiles.DesignPatterns.Visitor;
import Dungeons_and_Dragons.*;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;
import GameTiles.Units.Unit;

public class Wall extends Tile {

    public Wall (Position p)
    {
        super('#');
        this.position=p;
    }
    public void accept(Visitor v)
    {
        v.visit(this);

    }

}
