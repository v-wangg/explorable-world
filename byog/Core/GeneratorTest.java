package byog.Core;

import byog.Core.Generator;
import byog.TileEngine.TETile;
import byog.TileEngine.TERenderer;


public class GeneratorTest {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(80, 30);

        Map world = new Map(1321);
        TETile[][] worldTiles = world.genWorld();

        ter.renderFrame(worldTiles);
    }
}
