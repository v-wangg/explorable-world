package byog.Core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Exits implements Iterable<Position> {
    private List<Position> exits;
    private List<String> filled;

    Exits(String entrance) {
        exits = new ArrayList<>();
        filled = new ArrayList<>();
        filled.add(entrance);
    }

    void addExit(Position exit, String side) {
        if (!filled(side)) {
            exits.add(exit);
            filled.add(side);
        }
    }

    private boolean filled(String side) {
        for (String check: filled) {
            if (check.equals(side)) {
                return true;
            }
        }
        return false;
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
