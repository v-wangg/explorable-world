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

    int height() {
        return random.nextInt(10) + 3;
    }

    int width() {
        return random.nextInt(13) + 3;
    }

    Position worldStart() {
        int x = random.nextInt(79) + 1;
        int y = random.nextInt(29) + 1;
        return new Position(x, y);
    }

    private Position randomExit(Room room) {
        String side = exitSide();

        if (side.equals("left")) {
            int xExit = room.leftCorner.x;
            int yExit = random.nextInt(room.height - 1) + room.leftCorner.y + 1;
            return new Position(xExit, yExit);
        } else if (side.equals("right")) {
            int xExit = room.leftCorner.x + room.width - 1;
            int yExit = random.nextInt(room.height - 1) + room.leftCorner.y + 1;
            return new Position(xExit, yExit);
        } else if (side.equals("top")) {
            int xExit = random.nextInt(room.width - 1) + room.leftCorner.x + 1;
            int yExit = room.leftCorner.y + room.height - 1;
            return new Position(xExit, yExit);
        } else {
            int xExit = random.nextInt(room.width - 1) + room.leftCorner.x + 1;
            int yExit = room.leftCorner.y;
            return new Position(xExit, yExit);
        }
    }

    private String exitSide() {
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

    Position[] randomExits(Room room) {
        int numOfExits = random.nextInt(4) + 1;
        Position[] exits = new Position[numOfExits];


        for (int i = 0; i < numOfExits; i += 1) {
            exits[i] = randomExit(room);
        }
        return exits;
    }
}
