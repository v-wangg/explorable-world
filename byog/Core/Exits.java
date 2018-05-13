package byog.Core;

import java.util.*;
import java.util.Map;

//TODO: Might have to store a map of each exit and the side of the room they correspond to

public class Exits implements Iterable<Position> {
    private List<Position> exits;
    private List<String> filledSides;

    Exits(String entranceSide) {
        exits = new ArrayList<>();
        filledSides = new ArrayList<>();
        filledSides.add(entranceSide);
    }

    void addExit(String side, Position exit) {
        if (!filled(side)) {
            exits.add(exit);
            filledSides.add(side);
        }
    }

    private boolean filled(String side) {
        for (String checkSide: filledSides) {
            if (checkSide.equals(side)) {
                return true;
            }
        }
        return false;
    }

    Position[] getHallwayEntrances() {
        Position[] entrancesArray = new Position[exits.size()];
        for (int i = 0; i < exits.size(); i += 1) {
            Position exit = exits.get(i);
            String entranceSide = filledSides.get(i + 1);
            if (entranceSide.equals("left")) {
                entrancesArray[i] = new Position(exit.x - 1, exit.y);
            } else if (entranceSide.equals("right")) {
                entrancesArray[i] = new Position(exit.x + 1, exit.y);
            } else if (entranceSide.equals("top")) {
                entrancesArray[i] = new Position(exit.x, exit.y + 1);
            } else {
                entrancesArray[i] = new Position(exit.x, exit.y - 1);
            }
        }
        return entrancesArray;
    }

    String[] getSides() {
        String[] sidesArray = new String[exits.size()];
        for (int i = 0; i < filledSides.size() - 1; i += 1) {
            sidesArray[i] = filledSides.get(i + 1);
        }
        return sidesArray;
    }

    @Override
    public Iterator<Position> iterator() {
        return new exitIterator();
    }

    public class exitIterator implements Iterator<Position> {
        private int position;

        exitIterator() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position != exits.size();
        }

        @Override
        public Position next() {
            Position returnItem = exits.get(position);
            position += 1;
            return returnItem;
        }
    }
}
