package Dungeons_and_Dragons;

import jdk.jshell.spi.ExecutionControl;

public class Position {

    private int _x;
    private int _y;
    public static Position at(int x, int y) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("to-do");
    }

    public int compareTo(Position position) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("to-do");
    }

    public int getX(){return _x;}

    public int getY(){return _y;}

    public double Range (Position p1)
    {
        return Math.sqrt(Math.pow((this.getX()-p1.getX()),2)+Math.pow((this.getY()-p1.getY()),2));
    }
}
