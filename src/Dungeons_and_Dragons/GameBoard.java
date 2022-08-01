package Dungeons_and_Dragons;
import GameTiles.Tile;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Empty;
import GameTiles.Units.Players.Player;
import GameTiles.Units.Unit;
import UI.PrintBoardCallBack;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    public static Tile[][] board;
    private List<Tile> tiles;
    private PrintBoardCallBack printBoardCallBack;
    private int column;
    private int row;
    private  Player player;

    public void setPrintBoardCallBack(PrintBoardCallBack boardCallBack) {
        this.printBoardCallBack = boardCallBack;
    }
    public GameBoard(Tile[][] array, int rowNum, int col,Player player,Player fakePlayer){
        this.board=new Tile[rowNum][col];
        this.board=array;
        this.player=player;
        this.player.initialize(fakePlayer.getPosition());
        board[fakePlayer.getPosition().getY()][fakePlayer.getPosition().getX()]=player;
        column = col;
        row =rowNum;

    }



    public Tile get(int x, int y) throws ExecutionControl.NotImplementedException {
        if(board[x][y]!=null)
            return board[x][y];
        throw new RuntimeException("No tile");
    }



    public void setPlayer(Player player){
        player.initialize(findPlayerPosition().getPosition());


    }

    public Tile finedTile(Position pose) {
        if (board[pose.getX()][pose.getY()]!=null) {
            return board[pose.getX()][pose.getY()];
            }
        return null;
    }

    private Tile findPlayerPosition(){
        for (int i=0;i<row;i++){
            for(int j=0;j<column;j++) {
                if (board[i][j].getTile() == '@')
                    return board[i][j];
            }
        }
        return null;
    }
    public Player getPlayer(){return player;}


    public void remove(Enemy e) {
        board[e.getPosition().getX()][e.getPosition().getY()]=new Empty(e.getPosition());
    }

    public void sortTiles(){
        tiles = tiles.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String output = "";
        int counter = 0;
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++) {
                output += board[i][j].getTile();
            }
                output += "\n" ;
        }
        return output;
    }


    public void printBoard()
    {
        printBoardCallBack.print();
    }

    public void addTile(Tile tileFactory) {
        board[tileFactory.getPosition().getX()][tileFactory.getPosition().getY()]=tileFactory;

    }

    public void remove(Tile tile) {
        board[tile.getPosition().getX()][tile.getPosition().getY()]=new Empty(tile.getPosition());
    }

    public void Replace(Tile oldTile, Tile newTile){
        Tile tmp=board[oldTile.getPosition().getX()][oldTile.getPosition().getY()];
        //maybe a run time error with ailsing
        board[oldTile.getPosition().getX()][oldTile.getPosition().getY()]=newTile;
        board[newTile.getPosition().getX()][newTile.getPosition().getY()]=oldTile;
    }


}