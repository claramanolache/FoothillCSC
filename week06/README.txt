project folder:
claramanolache-cs1c-project06/

Brief description of submitted files:
resources/destinations.txt
    - a sample file containing destination names and descriptions for testing the SearchDestination class.
    gets read by SearchDestination.java to search for specific words and display their descriptions.

resources/friendsAndPlaces.txt
    - another sample file containing names of my friends and places I like along with their descriptions for testing the Search
    created by me to test the SearchDestination class with a different set of data.

resources/RUN.txt
    - console output of SearchDestination.java for various search queries on the two files mentioned above.

resources/RUN1.png
    - screenshot of GUI when using test file friendsAndPlaces.txt, showing search results for the query "Clara".
        expected to show one result found, which is the case.

resources/RUN2.png
    - screenshot of GUI when using test file friendsAndPlaces.txt, showing search results for the query "hello".
    expected to show no results found, which is the case.

resources/RUN3.png
    - screenshot of GUI when using test file friendsAndPlaces.txt, showing search results for the query "a".
    expected to show 2 results found, which is the case.

resources/RUN4.png
    - screenshot of GUI when using test file destinations.txt, showing search results for the query "san".
    expected to show 4 results found, which is the case.

src/assistant/FHlinkedList.java
    - a custom linked list implementation used in the SearchDestination class to store and manage search results

src/assistant/FHmapSC.java
    - a custom hash map implementation used in the SearchDestination class to store and manage the destination data read from the files.

src/assistant/FileReader.java
    - a utility class for reading files and populating the hash map with destination data for the SearchDestination class.

src/assistant/HashEntries.java
    - a class representing individual entries in the hash map, containing a key (the destination name) and a value (the description).
    longer than HashEntry because of collison resolution used.

src/assistant/HashEntry.java
    - a class representing a single entry in the hash map, used internally by FHmapSC to manage collisions and store key-value pairs.

src/assistant/SearchDestination.java
    - the main class that implements the search functionality. It reads destination data from a file, stores it in a hash map, and allows users to search for specific words to retrieve their descriptions.

src/assistant/Word.java
    - a class representing a word and its associated description, used to store search results in the linked list.

src/assistant/WordList.java
    - a class representing a list of Word objects, used to manage and display search results in the SearchDestination class.