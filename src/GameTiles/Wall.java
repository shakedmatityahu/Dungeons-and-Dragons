package GameTiles;
import DesignPatterns.Visitor;
import Dungeons_and_Dragons.*;
import Units.Enemies.Enemy;
import Units.Players.Player;
import Units.Unit;

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
    public void visit(Player p)
    {

    }
    public void visit (Enemy e)
    {

    }
    public void visit (Empty empty)
    {

    }

    public void visit (Wall w)
    {

    }

    @Override
    public void accept(Unit unit) {
        //למה צריך את זה בעצם?
    }
}
