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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private  Tile[][] board;
    private List<Tile> tiles;
    private PrintBoardCallBack printBoardCallBack;
    private int column;
    private int row;
    private  Player player;

    public void setPrintBoardCallBack(PrintBoardCallBack boardCallBack) {
        this.printBoardCallBack = boardCallBack;
    }
    public GameBoard(Tile[][] array, int rowNum, int col,Player player,Player fakePlayer,List<Tile> tiles){
        this.board=new Tile[rowNum][col];
        this.board=array;
        this.player=player;
        this.player.initialize(fakePlayer.getPosition());
        board[fakePlayer.getPosition().getX()][fakePlayer.getPosition().getY()]=player;
        column = col;
        row =rowNum;
        this.tiles=tiles;

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

    public Tile findPlayerPosition(){
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
        //tiles = tiles.stream().sorted().collect(Collectors.toList());
        Comparator<Tile> comp = new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2)
            {
                if(o1.getPosition()==null && o2.getPosition()==null)
                    System.out.println("null");
                int o1Y = o1.getPosition().getY();
                int o2Y = o2.getPosition().getY();
                if(o1Y < o2Y)
                    return -1;
                else if (o1Y > o2Y)
                    return 1;
                else{
                    int o1X = o1.getPosition().getX();
                    int o2X = o2.getPosition().getX();
                    if(o1X < o2X)
                        return -1;
                    else if(o1X > o2X)
                        return 1;
                    else
                        return 0;
                }
            }
        };

        tiles = tiles.stream().sorted(comp).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String output = "";
        int counter = 0;
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++) {
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


    public void sortArray() {
        try{
            //sortTiles();
            for(Tile tile:tiles)
            {
                if(tile.getTile()=='@')
                    System.out.println("found x: " +tile.getPosition().getX()+" y:"+tile.getPosition().getY());
                board[tile.getPosition().getX()][tile.getPosition().getY()]=tile;
            }
        }
        catch (Exception e) {
            System.out.println();
        }
    }
}