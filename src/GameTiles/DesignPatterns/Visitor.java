package GameTiles.DesignPatterns;

import GameTiles.Empty;
import GameTiles.Wall;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;

public interface Visitor {
    public void visit (Enemy e);
    public void visit (Empty empty);
    public void visit(Wall w);
    public void visit (Player p);

}
