project folder:
claramanolache-cs1c-project02/

Brief description of submitted files:
resources/groceries1.txt
    - the given groceries file used for testing, renamed for
    formatting and understanding

resources/groceries2.txt
    - a new grocery file to test on, to see if code runs on
     multiple files

resources/music_genre_subset.json
    - List of music genres,  artists, song titles, duration, and id

resources/RUN.txt
    - console output of ShoppingBag.java for two grocery files
    and for FoothillTunesStore.java

src/cs1c/MillionSongDataSubset.java
    - parses a JSON data set and stores each entry in an array

src/cs1c/SongEntry.java
    - stores a simplified version of the genre data set from
    the Million Song Dataset.

src/cs1c/TimeConverter.java
    - Converts duration into a string representation

src/subsetsum/FoothillTunesStore.java
    - creates an object of type MillionSongDataSubset, uses user
     prompts for a play list duration. uses SubsetSumto make a
     play list of SongEntry objects closest to given duration.
    - Tried... to optimize subsetSum for foothillTunesStore
    by creating a class called subset. This was I wouldn't have
    to recompute total. However, code runs out of memory when I try
    to input values over 2700. I also check for best subset as i'm
    iterating, but that doesn't help a lot. If a group cant reach
    duration without adding a specific value, I make sure to remove
    that node as well.

src/subsetsum/GroceriesFileReader.java
    - Reads a file of groceries and their prices and returns
    only the prices in an array list. Used in ShoppingBag.java.

src/subsetsum/ShoppingBag.java
    - Finds best possible grocery shopping list within the given
     budget using an object of type SubsetSum.java.

src/subsetsum/SubsetSum.java
    - Finds a combination of elements in the orginal set (a subset)
    that is closest to the goal as possible

README.txt
    - description of submitted files