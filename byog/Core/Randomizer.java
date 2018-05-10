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

    private Position exitBottom(Room room) {
        int xPos = random.nextInt(room.width) + room.leftCorner.x;
        return new Position(xPos, room.leftCorner.y);
    }

    private Position exitLeft(Room room) {
        int yPos = random.nextInt(room.height) + room.leftCorner.y;
        return new Position(room.leftCorner.x, yPos);
    }

    Position[] exits(Room room) {
        Position[] exits = new Position[2];

        exits[0] = exitBottom(room);
        exits[1] = exitLeft(room);
        return exits;
    }
}
