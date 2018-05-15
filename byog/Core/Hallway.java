package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Hallway implements Structure {
    private Position entry;
    private String side;
    private int length;

    public Hallway(String side, Position entry, int length) {
        this.entry = entry;
        this.side = side;
        this.length = length;
    }

    @Override
    public void generate(TETile[][] world) {
        if (side.equals("right")) {
            Position start = new Position(entry.x, entry.y - 1);
            horWall(world, start, length);
            start.y += 2;
            horWall(world, start, length);
            start.y -= 1;
            genHorFloor(world, start);
        } else if (side.equals("left")) {
            Position start = new Position(entry.x - length + 1, entry.y - 1);
            horWall(world, start, length);
            start.y += 2;
            horWall(world, start, length);
            start.y -= 1;
            genHorFloor(world, start);
        } else if (side.equals("top")) {
            Position start = new Position(entry.x - 1, entry.y);
            vertWall(world, start, length);
            start.x += 2;
            vertWall(world, start, length);
            start.x -= 1;
            genVertFloor(world, start);
        } else {
            Position start = new Position(entry.x - 1, entry.y - length + 1);
            vertWall(world, start, length);
            start.x += 2;
            vertWall(world, start, length);
            start.x -= 1;
            genVertFloor(world, start);
        }
    }

    Position getRoomEntrance() {
        if (side.equals("right")) {
            return new Position(entry.x - length, entry.y);
        } else if (side.equals("left")) {
            return new Position(entry.x + length, entry.y);
        } else if (side.equals("top")) {
            return new Position(entry.x, entry.y + length);
        } else {
            return new Position(entry.x, entry.y - length);
        }
    }

    String getRoomEntranceSide() {
        if (side.equals("right")) {
            return "left";
        } else if (side.equals("left")) {
            return "right";
        } else if (side.equals("top")) {
            return "bottom";
        } else {
            return "top";
        }
    }

    private void genHorFloor(TETile[][] world, Position start) {
        int xStart = start.x;
        int xEnd = start.x + length;
        int y = entry.y;
        for (int x = xStart; x < xEnd; x += 1) {
            world[x][y] = Tileset.FLOOR;
        }
    }

    private void genVertFloor(TETile[][] world, Position start) {
        int yStart = start.y;
        int yEnd = start.y + length;
        int x = entry.x;
        for (int y = yStart; y < yEnd; y += 1) {
            world[x][y] = Tileset.FLOOR;
        }
    }
}
