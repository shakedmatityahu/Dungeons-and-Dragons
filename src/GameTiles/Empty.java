package GameTiles;

import Dungeons_and_Dragons.Position;
import Dungeons_and_Dragons.Tile;
import Units.Unit;

public class Empty extends Tile {
    public Empty(char tile) {
        super(tile);
    }

    public Empty(Position p) {

    }

    @Override
    public void accept(Unit unit) {

    }
}
