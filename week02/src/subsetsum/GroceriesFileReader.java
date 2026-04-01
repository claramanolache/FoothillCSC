package subsetsum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * makes a double arraylist with a file so we can read a groceries list from its formating
 */
public class GroceriesFileReader {
    /**
     * Reads a file of groceries and their prices and returns only the prices in a array list
     * @param filePath the file we will be reading off of
     * @return list of grocery prices read from file, empty arraylist if the file is empty
     */
    public static ArrayList<Double> readFile(String filePath){
        ArrayList<Double> list = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scan = new Scanner(file);
            // error will be thrown here if file does not exist
            while (scan.hasNext()){
                String line = scan.nextLine();
                // price will always follow ",", number after first comma of line is price
                double price = Double.parseDouble(line.substring(line.indexOf(",") + 1));
                list.add(price);
            }
        }catch (FileNotFoundException e){
            // in the case that the file doesn't exist, return empty arraylist
            return new ArrayList<>();
        }
        return list;
    }
}
