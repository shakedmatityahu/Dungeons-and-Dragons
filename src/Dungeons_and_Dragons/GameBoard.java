package Dungeons_and_Dragons;
import GameTiles.Tile;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Empty;
import GameTiles.Units.Players.Player;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;

    public GameBoard(List<Tile> tiles){
        this.tiles =tiles;
    }

    public static void reomve(Enemy enemy) {
    }

    public Tile get(int x, int y) throws ExecutionControl.NotImplementedException {
        for(Tile t : tiles){
            if (t.getPosition().equals(Position.at(x, y))){
                return t;
            }
        }
        throw new RuntimeException("No tile");
    }


    public void setPlayer(Player player){
        Tile old =findPlayerPosition();
        if(old != null){
            player.initialize(old.getPosition());
            tiles.remove(old);
            tiles.add(player);
        }

    }

    public Tile finedTile(Position pose) {
        for (Tile tmp : tiles) {
            if (pose.compareTo(tmp.getPosition()) == 0)
                return tmp;
        }
        return null;
    }

    private Tile findPlayerPosition(){
        for (Tile tile : tiles){
             if(tile.getTile() =='@')
                 return tile;
        }
        return null;
    }

    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new Empty(p));
    }

    public void sortTiles(){
        tiles = tiles.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        sortTiles();
        String output = "";
        int line = 0;
        for(Tile tile : tiles) {
            if(line == tile.getPosition().getX()) {
                output += tile.toString();
            }

            else
            {
                line++;
                output += "\n" + tile.toString();
            }
        }
        return output;
    }

    public void addTile(Tile tileFactory) {
        tiles.add(tileFactory);
        sortTiles();
    }

    public void remove(Tile tile) {
        tiles.remove(tile);
        sortTiles();
    }

    public void Replace(Tile oldTile ,Tile newTile){
        addTile(newTile);
        remove(oldTile);
    }
}