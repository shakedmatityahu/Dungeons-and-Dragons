package DesignPatterns;

import GameTiles.Empty;
import GameTiles.Wall;
import Units.Enemies.Enemy;
import Units.Players.Player;

public interface Visitor {
    public void visit (Enemy e);
    public void visit (Empty empty);
    public void visit(Wall w);
    public void visit (Player p);

}
