package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public interface Structure {
    void generate(TETile[][] world);

    default void horWall(TETile[][] world, Position start, int length) {
        for (int x = start.x; x < start.x + length; x += 1) {
            world[x][start.y] = Tileset.WALL;
        }

    }

    default void vertWall(TETile[][] world, Position start, int length) {
        for (int y = start.y; y < start.y + length; y += 1) {
            world[start.x][y] = Tileset.WALL;
        }
    }
}
