/**
 //TODO: Generate a firstRoom with a random starting point
        Split the screen into 6 sections of equal area, keeping reference to each intersection point of the grid
 //TODO: Generate a hallway from gaps (either 2 or 3) in the firstRoom based upon which section it is generated within
        For each new room generated, check which section it is in and determine which direction its hallways grow
        If a growing hallway or room collides with a pre-existing room, its size will retract such that the
        hallway or room grows to the edge of the pre-existing room

 */

package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Generator {
    private int WINDOWWIDTH = 80;
    private int WINDOWHEIGHT = 30;
    private Randomizer randomizer;

    public Generator(Randomizer randomizer) {
        this.randomizer = randomizer;
    }

    void genRoom(TETile[][] world, Position leftCorner) {
        int roomWidth = randomizer.width();
        int roomHeight = randomizer.height();
        System.out.println(roomWidth);
        System.out.println(roomHeight);

        Room room = new Room(leftCorner, roomWidth, roomHeight);
        Position[] exits = randomizer.exits(room);

        room.generate(world, exits);
    }
}
