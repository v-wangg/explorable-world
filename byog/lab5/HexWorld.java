package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int SEED = 2312335;
    private static TETile[][] tiles = new TETile[WIDTH][HEIGHT];
    private static Random rand = new Random(SEED);

    public static class Position {
        public int x;
        public int y;
        public int middleX;
        public int middleY;

        public Position(int x, int y) {
            middleX = x;
            middleY = y;
            this.x = x;
            this.y = y;
        }
    }

    public static void initializeTiles() {
        for (int y=0; y<HEIGHT; y+=1) {
            for (int x=0; x<WIDTH; x+=1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void addHexagon(int s, int xStart, int yStart) {
        int xEnd = s + xStart;

        TETile tile = randomTile();
        // Draw top half of hexagon
        for (int y = yStart; y > yStart - s; y -= 1) {
            drawRow(xStart, xEnd, y, tile);
            xStart -= 1;
            xEnd += 1;
        }

        // Change xStart, xEnd, and yStart based on indexes needed for bottom half of hexagon
        xStart += 1;
        xEnd -= 1;
        yStart -= s;

        // Draw bottom half of hexagon
        for (int y = yStart; y > yStart - s; y -= 1) {
            drawRow(xStart, xEnd, y, tile);
            xEnd -= 1;
            xStart +=1;
        }
    }

    private static void drawRow(int xStart, int xEnd, int y, TETile tile) {
        for (int x = xStart; x < xEnd; x += 1) {
            tiles[x][y] = tile;
        }
    }

    private static TETile randomTile() {
        int tileNum = rand.nextInt(4);
        switch (tileNum) {
            case 0: return Tileset.MOUNTAIN;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }

    private static Position getBottomRight(int s, Position p) {
        p.x = p.x + (s * 2) - 1;
        p.y = p.y - s;
        return p;
    }

    private static Position getBottomLeft(int s, Position p) {
        p.x = p.x - (s * 2) + 1;
        p.y = p.y - s;
        return p;
    }

    private static void nextCurtain(int s, Position p) {
        p.y = p.y - (s * 2);
        p.middleY = p.middleY - (s * 2);
    }

    private static void drawCurtain(Position p, int length) {
        addHexagon(3, p.middleX, p.middleY);

        for (int i = 0; i < length - 1; i += 1) {
            p = getBottomLeft(3, p);
            addHexagon(3, p.x, p.y);
        }

        p.x = p.middleX;
        p.y = p.middleY;

        for (int i = 0; i < length - 1; i += 1) {
            p = getBottomRight(3, p);
            addHexagon(3, p.x, p.y);
        }

        p.x = p.middleX;
        p.y = p.middleY;
    }

    public static void drawTesselation() {
        Position p = new Position(23, 45);

        for (int i = 0; i < 3; i += 1) {
            drawCurtain(p, 3);
            nextCurtain(3, p);
        }
        drawCurtain(p, 2);
        nextCurtain(3, p);
        drawCurtain(p, 1);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();

        ter.initialize(WIDTH, HEIGHT);
        initializeTiles();

        drawTesselation();

        ter.renderFrame(tiles);
    }
}