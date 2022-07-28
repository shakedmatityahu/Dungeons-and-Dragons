package GameTiles;
import GameTiles.DesignPatterns.Visited;
import GameTiles.DesignPatterns.Visitor;
import Dungeons_and_Dragons.Position;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;
import GameTiles.Units.Unit;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public abstract class Tile implements Comparable<Tile> ,Visited {
    protected char tile;
    protected Position position;
    private static final List<Character> GAMETILES =List.of('.','#');

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



    protected void initialize(Position position){
        this.position = position;
    }

    public static Tile tileFactory(char c, int x, int y){
        Position p = new Position(x,y);

        if (GAMETILES.contains(c))
            return new Empty(p);

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
        this.setPosition(t.getPosition());
        t.setPosition(tmp);
    }

    @Override
    public int compareTo(Tile tile) {
        try {
            return getPosition().compareTo(tile.getPosition());
        }
        /*catch (ExecutionControl.NotImplementedException e) {
            e.printStackTrace();
        }*/ //לא כזה הבנתי מה זה כי ראיתי בקובץ שלהם שזה לא מופיע

        finally {
            return 0;
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


}