package Dungeons_and_Dragons;

import Units.Unit;
import jdk.jshell.spi.ExecutionControl;

public abstract class Tile implements Comparable<Tile> {
    protected char tile;
    protected Position position;

    protected Tile(char tile){
        this.tile = tile;
    }

    /// will not be used... made problems while building
    public Tile() {

    }

    protected void initialize(Position position){
        this.position = position;
    }

    public char getTile() {
        return tile;
    }

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
        } catch (ExecutionControl.NotImplementedException e) {
            e.printStackTrace();
        }
        finally {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(tile);
    }


}