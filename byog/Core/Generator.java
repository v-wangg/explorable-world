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
import java.util.Map;

public class Generator {
    private int WINDOWWIDTH = 80;
    private int WINDOWHEIGHT = 30;
    private Randomizer randomizer;

    public Generator(Randomizer randomizer) {
        this.randomizer = randomizer;
    }

    Exits genRoom(TETile[][] world, Position leftCorner) {
        int roomWidth = randomizer.randomWidth();
        int roomHeight = randomizer.randomHeight();

        Room room = new Room(leftCorner, roomWidth, roomHeight, "first");
        Exits exits = randomizer.randomExits(room);
        room.addExits(exits);

        room.generate(world);

        return exits;
    }

    void genHallway(TETile[][] world, Position entry, String side) {
        if (side.equals("left") || side.equals("right")) {
            int length = randomizer.randomHorLength();
            Hallway hallway = new Hallway(side, entry, length);
            hallway.generate(world);
        } else if (side.equals("top") || side.equals("bottom")) {
            int length = randomizer.randomVertLength();
            Hallway hallway = new Hallway(side, entry, length);
            hallway.generate(world);
        } else {
            // For L shaped hallways
        }
    }

    void genChain(TETile[][] world, Position start) {
        Exits exits = genRoom(world, start);

        Position[] entrancesArray = exits.getHallwayEntrances();
        String[] sidesArray = exits.getSides();

        for (int i = 0; i < entrancesArray.length; i += 1) {
            genHallway(world, entrancesArray[i], sidesArray[i]);
        }
    }

}
