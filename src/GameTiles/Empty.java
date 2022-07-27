package GameTiles;

import DesignPatterns.Visitor;
import Dungeons_and_Dragons.Position;
import Dungeons_and_Dragons.Tile;
import Units.Enemies.Enemy;
import Units.Players.Player;
import Units.Unit;

public class Empty extends Tile  {
    public Empty() {
        super('.');
    }

    public Empty(Position p) {
        super('.');
        this.position=p;
    }

    @Override
    public void accept(Unit unit) {
    // why?
    }

    public void visit (Player p)
    {

    }
    public void  visit(Enemy e)
    {

    }
    public void visit (Empty empty)
    {

    }
    public void visit (Wall w)
    {

    }
    @Override
    public void accept (Visitor v)
    {
        v.visit(this);
    }
}
