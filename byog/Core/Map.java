package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Map {
    private int WINDOWWIDTH = 80;
    private int WINDOWHEIGHT = 30;
    private Randomizer randomizer;
    int SEED;

    public Map(int SEED) {
        this.SEED = SEED;
        randomizer = new Randomizer(SEED);
    }

    // Sets all tiles as empty initially
    private void initializeTiles(TETile[][] world) {
        for (int y=0; y<WINDOWHEIGHT; y+=1) {
            for (int x=0; x<WINDOWWIDTH; x+=1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    private Position genStart() {
        return randomizer.randomWorldStart();
    }

    public TETile[][] genWorld() {
        TETile[][] world = new TETile[WINDOWWIDTH][WINDOWHEIGHT];
        initializeTiles(world);

        Generator generator = new Generator(randomizer);
        Position start = new Position(35, 15);

        generator.genRoomWithHallways(world, start, "left");
        return world;
    }
}
