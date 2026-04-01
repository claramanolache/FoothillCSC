package subsetsum;

import cs1c.SongEntry;

import java.util.ArrayList;

/**
 Subset class has an instance variable ArrayList<SongEntry> and an int sum
 * Because it also has sum, I can keep track of it as I update subsets and
 * I dont have to call totalduration again!
 */
public class Subset {
    ArrayList<SongEntry> songs;
    int sum;

    /**
     * @param songs the subset of songs
     * @param sum the running (updated when new thing gets added) total of all songs
     */
    Subset(ArrayList<SongEntry> songs, int sum) {
        this.songs = songs;
        this.sum = sum;
    }

}
