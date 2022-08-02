package Dungeons_and_Dragons;
import GameTiles.Tile;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Empty;
import GameTiles.Units.Players.Player;
import UI.PrintBoardCallBack;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;
    private PrintBoardCallBack printBoardCallBack;
    private int column;
    private int row;
    private Player player;

    public void setPrintBoardCallBack(PrintBoardCallBack boardCallBack) {
        this.printBoardCallBack = boardCallBack;
    }
    public GameBoard(List<Tile> tiles, int col, int rowNum,Player initPlayer){
        this.tiles =tiles;
        column = col;
        rowNum = row;
        player = initPlayer;
    }

    public static void reomve(Enemy enemy) {
    }

    public Tile get(int x, int y) {
        for(Tile t : tiles){
            if (t.getPosition().compareTo(new Position(x, y)) == 0){
                return t;
            }
        }
        return null;
    }


    public void setPlayer(Tile newPlayer){
        Tile old =this.player;
        if(old != null){
            newPlayer.initialize(player.getPosition());
            tiles.remove(player);
            tiles.add(newPlayer);
            sortTiles();
        }
    }

    public void removeEnemy(Tile enemy) {
        if (enemy != null) {
            Tile newEmpty = new Empty(enemy.getPosition());
            tiles.remove(enemy);
            tiles.add(newEmpty);
            sortTiles();
        }
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
                output += tile;
            }
            else
            {
                line++;
                output += "\n" + tile;
            }
        }
        return output;
    }


    public void swap(Position deadPos) {
        Tile old_enemy = get(deadPos.getX(),deadPos.getY());
         old_enemy= new Empty(deadPos);
    }
}