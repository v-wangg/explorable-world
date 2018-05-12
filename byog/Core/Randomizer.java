//TODO: Generate a random number of hallways and rooms
//TODO: Randomize locations of rooms and hallways
//TODO: Randomize width and height of rooms, and lengths of hallways
//TODO: Randomize whether or not a hallway includes a turn
//TODO: Randomize whether or not a hallway or room closes on itself

package byog.Core;

import java.util.Random;

public class Randomizer {
    Random random;

    public Randomizer(int SEED) {
        random = new Random(SEED);
    }

    int randomHeight() {
        return random.nextInt(9) + 4;
    }

    int randomWidth() {
        return random.nextInt(12) + 4;
    }

    Position randomWorldStart() {
        int x = random.nextInt(79) + 1;
        int y = random.nextInt(29) + 1;
        return new Position(x, y);
    }

    private Position randomSingleExit(Room room, String side) {
        if (side.equals("left")) {

            int xExit = room.leftCorner.x;
            int yExit = random.nextInt(room.height - 2) + room.leftCorner.y + 1;
            return new Position(xExit, yExit);

        } else if (side.equals("right")) {

            int xExit = room.leftCorner.x + room.width - 1;
            int yExit = random.nextInt(room.height - 2) + room.leftCorner.y + 1;
            return new Position(xExit, yExit);

        } else if (side.equals("top")) {

            int xExit = random.nextInt(room.width - 2) + room.leftCorner.x + 1;
            int yExit = room.leftCorner.y + room.height - 1;
            return new Position(xExit, yExit);

        } else {

            int xExit = random.nextInt(room.width - 2) + room.leftCorner.x + 1;
            int yExit = room.leftCorner.y;
            return new Position(xExit, yExit);
        }
    }

    private String randomExitSide() {
        int ref = random.nextInt(5);
        switch (ref) {
            case 0:
                return "left";
            case 1:
                return "right";
            case 2:
                return "top";
            default:
                return "bottom";
        }
    }

    Exits randomExits(Room room) {
        int numOfExits = random.nextInt(3) + 1;
        Exits exits = new Exits(room.entrance);

        for (int i = 0; i < numOfExits; i += 1) {
            String side = randomExitSide();
            Position exit = randomSingleExit(room, side);
            exits.addExit(exit, side);
        }
        return exits;
    }
}
