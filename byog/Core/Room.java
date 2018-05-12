package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Room implements Structure {
    Position leftCorner;
    int width;
    int height;
    String entrance;
    Exits exits;

    public Room(Position leftCorner, int width, int height, String entrance) {
        this.leftCorner = leftCorner;
        this.width = width;
        this.height = height;
        this.entrance = entrance;
    }

    void addExits(Exits exits) {
        this.exits = exits;
    }

    @Override
    public void generate(TETile[][] world) {
        horWall(world, leftCorner, width);
        leftCorner.y = leftCorner.y + height - 1;
        horWall(world, leftCorner, width);
        leftCorner.y = leftCorner.y - height + 1;
        vertWall(world, leftCorner, height);
        leftCorner.x = leftCorner.x + width - 1;
        vertWall(world, leftCorner, height);
        leftCorner.x = leftCorner.x - width + 1;

        genExits(world);
        genFloor(world);
    }

    private void genFloor(TETile[][] world) {
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

    private void genExits(TETile[][] world) {
        for (Position exit : exits) {
            world[exit.x][exit.y] = Tileset.FLOOR;
        }
    }
}


