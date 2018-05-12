package byog.Core;

import byog.TileEngine.TETile;

public class Hallway {
    // Properties needed if L-shaped
    boolean Lshaped;
    private Position corner;
    private Position exit;
    private Position entry;
    // Properties needed if not L-shaped
    private String orientation;
    private int length;

    public Hallway(String orientation, boolean Lshaped, Position entry, int length) {
        this.orientation = orientation;
        this.Lshaped = Lshaped;
        this.entry = entry;
        this.length = length;
    }

    void generate(TETile[][] world) {

    }
}
