package DesignPatterns;

import GameTiles.Empty;
import Units.Enemies.Enemy;
import Units.Players.Player;
package src;
public interface Visitor {
    public void visit (Enemy e);
    public void visit(Wall w);
    public void (Player p);

}
