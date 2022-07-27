package Dungeons_and_Dragons;

import Units.Unit;
import jdk.jshell.spi.ExecutionControl;

public abstract class Tile implements Comparable<Tile> {
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



    protected void initialize(Position position){
        this.position = position;
    }



    public char getTile(){return this.tile;}

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void accept(Unit unit);

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