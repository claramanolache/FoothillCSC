package assistant;

import java.util.ArrayList;

/**
 * Class that creates a hash table with sepeate chaining of the sequences of chars from the input word list.
 * The keys of the hash map are the sequences of chars and the values are lists of Word objects that match the sequence.
 */
public class WordAssistant {
    private FHmapSC<String, Word> sequenceTable;
    /**
     * Constructor for WordAssistant. Initializes the sequence table and populates it with the sequences of chars from the input word list.
     * @param wordList A list of Word objects to be added to the sequence table.
     */
    public WordAssistant (FHlinkedList<Word> wordList)
    {
        sequenceTable = new FHmapSC<>();
        for (Word w : wordList){
            String s = w.getTerm();
            for (int i = 0; i < s.length(); i++){
                String sub = s.substring(0, i + 1).toLowerCase();
                sequenceTable.insert(sub, w);
            }
        }
    }

    /**
     * Returns a list of Word objects that match the input sequence. If no matches are found, returns null.
     * @param sequence The sequence of chars to search for in sequence table.
     * @return null if no matches are found, otherwise a list of Word objects that match the input sequence.
     */
    public ArrayList<Word> findSequenceInTable(String sequence)
    {
        return sequenceTable.containsKey(sequence.toLowerCase());
    }
    /**
     * Accessor for the sequence table.
     * @return the sequence table
     */
    public FHmapSC<String, Word> getSequenceTable()
    {
        return sequenceTable;
    }
}
