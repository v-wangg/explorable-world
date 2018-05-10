package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Room {
    Position leftCorner;
    int width;
    int height;

    public Room(Position leftCorner, int width, int height) {
        this.leftCorner = leftCorner;
        this.width = width;
        this.height = height;
    }

    void generate(TETile[][] world, Position[] exits) {
        horWall(world, leftCorner, width);
        leftCorner.y = leftCorner.y + height - 1;
        horWall(world, leftCorner, width);
        leftCorner.y = leftCorner.y - height + 1;
        vertWall(world, leftCorner, height);
        leftCorner.x = leftCorner.x + width - 1;
        vertWall(world, leftCorner, height);
        leftCorner.x = leftCorner.x - width + 1;

        addExits(world, exits);
        addFloor(world);
    }

    private void addFloor(TETile[][] world) {
        int xStart = leftCorner.x + 1;
        int yStart = leftCorner.y + 1;
        int yEnd = leftCorner.y + height - 1;
        int xEnd = leftCorner.x + width - 1;

        for (int y = yStart; y < yEnd; y += 1) {
            for (int x = xStart; x < xEnd; x += 1) {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

    private void horWall(TETile[][] world, Position start, int length) {
        for (int x = start.x; x < start.x + length; x += 1) {
            world[x][start.y] = Tileset.WALL;
        }

    }

    private void vertWall(TETile[][] world, Position start, int length) {
        for (int y = start.y; y < start.y + length; y += 1) {
            world[start.x][y] = Tileset.WALL;
        }
    }

    private void addExits(TETile[][] world, Position[] exits) {
        for (Position exit: exits) {
            world[exit.x][exit.y] = Tileset.FLOOR;
        }
    }
}
