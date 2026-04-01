package subsetsum;

import cs1c.SongEntry;

import java.util.ArrayList;

/**
 * algorithims to find best subsets
 */
public class SubsetSum {
    /**
	 * Finds a combination of elements in the orginal set (a subset) that is closest
     * to the goal as possible.
	 * @param set we will be looking to optimize
     * @param goal we will try to reach
     * @return the best subset possible
     */
    public static ArrayList<Double> findSubset(ArrayList<Double> set,double goal){
        // in subsets, we will store all lists of doubles that will be canidates for closest, starts with one empty set
        ArrayList<ArrayList<Double>> subSets = new ArrayList<>();
        subSets.add(new ArrayList<Double>());
        // where we will store sum of the values not yet iterated through
        double remaining = sum(set);
        // if goal is larger than possible, the closest subset will be the whole set
        if (goal > remaining){
            // so we just return set
            return set;
        }
        // iterating through grocery prices
        for (int s = 0; s < set.size(); s ++){
            // constants to get now to clean up code
            double add = set.get(s);
            // locking loop iteration so we dont go over same subset twice
            int checkSize = subSets.size();
            // iterating though created subsets
            for (int p = 0; p < checkSize; p ++){
                ArrayList<Double> curSet = subSets.get(p);
                remaining -= add; // updating remaining
                // checking if possible for curSet to reach goal (efficency)
                if (remaining + sum(curSet) < goal)
                    curSet.add(set.get(s));
                // do not want to go over goal, cuts down time complexity
                else if (sum(curSet) + add < goal)
                    subSets.add(append(curSet, add));
                // if the total of a subset is exactly the goal, no need to search anymore
                else if (sum(curSet) + add == goal)
                    return append(curSet, add);
                // if sum is neither equal or less than goal, then we ignore and continue
                else
                    continue;
            }
        }

        // finding subset closest to the goal
        double biggestSum = 0;
        ArrayList<Double> closest = subSets.get(0);
        // going through possible sets to check for closest
        for (ArrayList<Double> subsets: subSets){
            // checking if its bigger
            if (sum(subsets) > biggestSum) {
                // if larger, the biggest value found needs to be updated
                biggestSum = sum(subsets);
                // store the arraylist, because we have to return it at the end
                closest = subsets;
            }
        }
        // returning the set that has the largest value
        return closest;
    }

    /**
     * Finds a combination of songs with total length closest to the goal as possible.
     * @param songList the list we will be iterating through holding all songs
     * @param duration the goal duration for the playlist
     * @return subset of SongEntry
     */
    public static ArrayList<SongEntry> findSubsetOfSongs(ArrayList<SongEntry> songList, double duration){
        // storing subsets (to save time) in an arraylist rather than arraylists inside of arraylists
        ArrayList<Subset> subSets = new ArrayList<>();
        // subsets need to be initialzied with one empty Subset so that it will run
        subSets.add(new Subset(new ArrayList<SongEntry>(), 0));
        int remainingTime = totalDuration(songList);
        // if wanted duration is longer than possible with this song list, closest possible will just be the whole set
        if (duration > remainingTime){
            // so we just return set
            return songList;
        }
        // checking for closest as we go, hopefully saves a little bit of time
        Subset closest = subSets.getFirst();
        // declaring them here so I don't have to declare in loop, maybe this saves a little memory?
        int add, checkSize;
        Subset curSet;
        // iterating through songs
        for (int s = 0; s < songList.size(); s ++) {
            // constants saved now to make code cleaner
             add = songList.get(s).getDuration();
             // locking loop iteration so we dont go over same subset twice
             checkSize = subSets.size();
            // iterating through other subsets
            for (int p = 0; p < checkSize; p ++){
                // curSet value updated to make code cleaner and dont have to call .get(p) everytime
                curSet = subSets.get(p);
                // updating the time because now we are adding add
                remainingTime -= add;
                // if this set is impossible to reach duration without adding add, then we have to add add
                if (remainingTime + curSet.sum < duration) {
                    // adding to the arraylist, not a new subset
                    curSet.songs.add(songList.get(s));
                    // updating sum of this set
                    curSet.sum += add;
                    // checking if the newest updated curSet is the current closest
                    if (curSet.sum > closest.sum) {
                        // if so, updating closest
                        closest = curSet;
                    }
                }
                // checking if it doenst go over
                else if (curSet.sum + add < duration) {
                    // creating new subset and adding it to subsets
                    subSets.add(new Subset(append(curSet.songs, songList.get(s)), curSet.sum + add));
                    // checking if the newest added value is the current closest
                    if (curSet.sum + add > closest.sum) {
                        // if so, updating closest
                        closest = subSets.getLast();
                    }
                }
                // if the total of a subset is exactly the goal, no need to search anymore
                else if (curSet.sum + add == duration)
                    // we dont need to iterate anymore, return this
                    return append(curSet.songs, songList.get(s));
                else
                    continue;
            }
        }
        // returning the value closest (but calling .songs so that we return arraylist)
        return closest.songs;
    }

    /**
     * simple summation algorithim for double in ArrayList
     * @param set
     * @return double sum of set
     */
    private static double sum(ArrayList<Double> set){
        double sum = 0;
        for (double d: set){
            sum += d;
        }
        return sum;
    }

    /**
     * simple summation algorithim for SongEntrys in ArrayList
     * @param set
     * @return total length of songs in set
     */
    private static int totalDuration(ArrayList<SongEntry> set){
        int sum = 0;
        for (SongEntry dur: set){
            sum += dur.getDuration();
        }
        return sum;
    }

    /**
     * append returns a new ArrayList of same type as passed in with add at the end
     * @param prevSet
     * @param add
     * @return new ArrayList of type T with add at the end
     * @param <T>
     */
    private static <T> ArrayList<T> append(ArrayList<T> prevSet, T add){
        ArrayList<T> modSet = new ArrayList<>();
        for (T d: prevSet){
            modSet.add(d);
        }
        modSet.add(add);
        return modSet;
    }
}
