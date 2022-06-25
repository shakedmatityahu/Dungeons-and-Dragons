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

    public Position(){
        this.x = 0;
        this.y = 0;

    }

    public static Position at(int x, int y) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("to-do");
    }

    public int compareTo(Position position) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("to-do");
    }
}
