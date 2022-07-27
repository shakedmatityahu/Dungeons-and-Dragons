package GameTiles;

import GameTiles.DesignPatterns.Visitor;
import Dungeons_and_Dragons.Position;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;
import GameTiles.Units.Unit;

public class Empty extends Tile  {
    public Empty() {
        super('.');
    }

    public Empty(Position p) {
        super('.');
        this.position=p;
    }

    @Override
    public void accept (Visitor v)
    {
        v.visit(this);
    }


}
