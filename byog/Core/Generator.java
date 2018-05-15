/**
    //TODO: Generate a series of rooms based off the hallways branching off the previous room

    //TODO:
 */

package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Map;

public class Generator {
    private Randomizer randomizer;

    public Generator(Randomizer randomizer) {
        this.randomizer = randomizer;
    }

    private Exits genRoom(TETile[][] world, Position entrance, String entranceSide) {
        int roomWidth = randomizer.randomRoomWidth();
        int roomHeight = randomizer.randomRoomHeight();
        Position roomCorner = randomizer.randomRoomCorner(entrance, entranceSide, roomWidth, roomHeight);

        Room room = new Room(roomCorner, roomWidth, roomHeight, entrance, entranceSide);
        Exits exits = randomizer.randomExits(room);

        room.generate(world);

        return exits;
    }

    private Hallway genHallway(TETile[][] world, Position entry, String side) {
        if (side.equals("left") || side.equals("right")) {
            int length = randomizer.randomHorLength();
            Hallway hallway = new Hallway(side, entry, length);
            hallway.generate(world);
            return hallway;
        } else if (side.equals("top") || side.equals("bottom")) {
            int length = randomizer.randomVertLength();
            Hallway hallway = new Hallway(side, entry, length);
            hallway.generate(world);
            return hallway;
        } else {
            return new Hallway(side, entry, 3);// For L shaped hallways
        }
    }

    Hallway[] genRoomWithHallways(TETile[][] world, Position entrance, String entranceSide) {
        Exits exits = genRoom(world, entrance, entranceSide);

        Position[] hallwayEntrancesArray = exits.getHallwayEntrances();
        String[] hallwaySidesArray = exits.getSides();

        Hallway[] hallwaysArray = new Hallway[hallwayEntrancesArray.length];

        for (int i = 0; i < hallwayEntrancesArray.length; i += 1) {
            hallwaysArray[i] = genHallway(world, hallwayEntrancesArray[i], hallwaySidesArray[i]);
        }

        return hallwaysArray;
    }

    void genWorld(TETile[][] world, Position start) {
        Hallway[] hallways = genRoomWithHallways(world, start, "start");
        for (int i = 0; i < hallways.length; i += 1) {
            Position entrance = hallways[i].getRoomEntrance();
            String entranceSide = hallways[i].getRoomEntranceSide();
            genRoomWithHallways(world, entrance, entranceSide);
        }
    }

}
