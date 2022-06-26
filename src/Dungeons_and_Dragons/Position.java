package Dungeons_and_Dragons;

import jdk.jshell.spi.ExecutionControl;

public class Position {
    private int x;
    private int y;


    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this.x = 0;
        this.y = 0;

    }

    public static Position at(int x, int y)
    {
        return new Position(x,y);
    }

    public int compareTo(Position position)
    {
        if(y<position.getY())
            return -1;
        if(y>= position.getY())
            return  1;
        if(x< position.getY())
            return -1;
        if(x>= position.getY())
            return 1;
        return 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void setX(int newX) {
        x = newX;
    }

    private void setY(int newY) {
        y = newY;
    }

    public double Distance(Position p1) {
        return Math.sqrt(Math.pow((this.getX() - p1.getX()), 2) + Math.pow((this.getY() - p1.getY()), 2));
    }

    public boolean isInRange(Position p1, int range) {
        return range >= this.Distance(p1);
    }


    public Position move(String move) {
        if (move == "left")
            this.setX(this.getX() - 1);
        if (move == "right")
            this.setX(this.getX() + 1);
        if (move == "up")
            this.setY(this.getX() + 1);
        if (move == "down")
            this.setY(this.getY() - 1);
        return this;
    }
}
