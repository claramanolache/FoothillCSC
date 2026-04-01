package assistant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Utility class that reads a CSV files with the key, value format.
 * "[the word key]","[the description]"
 * "[the word key]","[the description]"
 * "[the word key]","[the description]"
 * ...
 * Stores each line as an FHlinkedList of Word object, where the
 * attribute in class Word:
 * term stores [the word key] and,
 * description stores the [the description]
 */
public class FileReader
{
    /**
     * Reads words from an input file. Converts the word into lowercase
     * and adds it to a list.
     * @param filePath The path to the input file.
     * @return A linked list of Word objects.
     */
    public static FHlinkedList<Word> readWords(String filePath)
    {
        FHlinkedList<Word> listOfWords = new FHlinkedList<>();

        // check to make sure the file can be found
        try {
            File inFile = new File(filePath);
            Scanner input = new Scanner(inFile);
            // Loops through the elements
            while (input.hasNextLine()) {
                String line = input.nextLine();
                List<String> tokensList = Stream.of(line.split("\"",-1))
                        .filter(token -> token.length() != 0 && token.equals(",") == false)
                        .map (elem -> new String(elem))
                        .toList();
                // get the new word composed of the word and a description
                listOfWords.add(new Word(tokensList.get(0), tokensList.get(1)));
            }
        } catch (FileNotFoundException e)
        {
            System.err.printf("File name %s not found.", filePath);
        }
        return listOfWords;
    }
}
