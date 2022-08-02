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

    //creators
    public Tile(char tile){
        this.tile = tile;
        position = new Position();
    }
    public static Tile tileFactory(char c,Position p){

        if (c == '#')
            return new Wall(p);
        else
            return new Empty(p);
    }
    public void initialize(Position position){
        this.position = position;
    }

    public char getTile(){return this.tile;}
    public Position getPosition() {
        return position;
    }

    public boolean InRange(Tile tile,int range ){
        return (position.isInRange(tile.getPosition(),range));
    }



    public abstract void accept(Visitor v);

    public void swap(Tile t){
        Position tmp = new Position(this.position);
        this.initialize(t.position);
        t.initialize(tmp);
    }

    @Override
    public int compareTo(Tile tile) {
        return this.position.compareTo(tile.position);
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