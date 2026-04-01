package assistant;

/**
 * An object of type Word stores sequence of chars and a description.
 * .
 */
public class Word
{
    /**
     * The sequence of chars as a String.
     */
    protected final String term;
    /**
     * The description of the sequence as a String.
     */
    protected final String description;

    public Word(String word, String description)
    {
        this.term = word;
        this.description = description;
    }

    public String getTerm()
    {
        return term;
    }

    public String getTheDescription()
    {
        return description;
    }

    @Override
    public String toString()
    {
        String result = "";
        result += "Word is: " + term + "\nDescription: " + description + "\n";
        return result;
    }
}
