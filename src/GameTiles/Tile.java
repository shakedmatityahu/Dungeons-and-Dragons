package GameTiles;
import Dungeons_and_Dragons.GameBoard;
import GameTiles.DesignPatterns.Visited;
import GameTiles.DesignPatterns.Visitor;
import Dungeons_and_Dragons.Position;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;
import GameTiles.Units.Unit;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Tile implements Comparable<Tile> ,Visited {
    protected char tile;
    protected Position position;

    public static boolean RealRandom = true;

    public Tile(char tile){
        this.tile = tile;
        position = new Position();
    }


    /// will not be used... made problems while building
    public Tile() {
        tile = '.';
        position = new Position();

    }


    public void initialize(Position position){
        this.position = position;
    }

    public static Tile tileFactory(char c,Position p){

        if (c == '.')
            return new Empty(p);
        else
            return new Wall(p);
    }

    public boolean InRange(Tile tile,int range ){
        return (position.isInRange(tile.getPosition(),range));
    }

    public char getTile(){return this.tile;}

    public Position getPosition() {
        return position;
    }

    private void setPosition(Position p){
        this.position = new Position(p);
    }


    public abstract  void accept (Visitor v);

    public void swap(Tile t){
        Position tmp = new Position(position);
        this.setPosition(t.position);
        t.setPosition(tmp);
    }

    @Override
    public int compareTo(Tile tile) {
        try {
            return getPosition().compareTo(tile.getPosition());
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(this.position + "    " +tile.position);
            System.out.println( e.getMessage());
            return -7;
        }

    }


    public void setTile(char tile)
    {
        this.tile=tile;
    }

    @Override
    public String toString() {
        return String.valueOf(tile);
    }

    public double Distance(Tile t) {
        return Math.sqrt(Math.pow((this.getPosition().getX() - t.getPosition().getX()), 2) + Math.pow((this.getPosition().getY() - t.getPosition().getY()), 2));
    }

    public boolean isInRange(Tile t, int range) {
        return range >= this.Distance(t);
    }

    public int randomNumber (int lastNumberPossible)
    {
        int randomNumber=0;
        return randomNumber = new Random().nextInt(lastNumberPossible);
    }
}