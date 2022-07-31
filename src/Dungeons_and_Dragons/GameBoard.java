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

    public void setPrintBoardCallBack(PrintBoardCallBack boardCallBack) {
        this.printBoardCallBack = boardCallBack;
    }
    public GameBoard(List<Tile> tiles, int col, int rowNum){
        this.tiles =tiles;
        column = col;
        row =rowNum;

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
            old =player;
            //sortTiles();
        }

    }

    public Tile finedTile(Position pose) {
        for (Tile tmp : tiles) {
            if (pose.equals(tmp.getPosition()))
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
        int counter = 0;
        for(Tile t : tiles) {
            if(counter < row) {
                output += t;
                counter++;
            } else {
                output += "\n" + t;
                counter = 1;
            }
        }
        return output;
    }


    public void printBoard()
    {
        printBoardCallBack.print();
    }

    public void addTile(Tile tileFactory) {
        tiles.add(tileFactory);

    }

    public void remove(Tile tile) {
        tiles.remove(tile);
    }

    public void Replace(Tile oldTile ,Tile newTile){
        addTile(newTile);
        remove(oldTile);
    }
}