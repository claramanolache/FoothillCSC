package FHsort07;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class FHsortRecursionLimit {
    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Recursion Limit Data");
        int index = 1; // to count rows, starts at 1 to account for header row
        Row headerRow = sheet.createRow(index++);
        // Create header cells for recursion limits (x-axis)
        for (int lim = 2; lim < 300; lim += 2) {
            Cell cell = headerRow.createCell(lim/2); // divide by 2 to account for step of 2 in lim, starts at 1
            cell.setCellValue(lim); // set recursion limit as header
        }
        long startTime, estimatedTime;
        //for (int size = 20000; size < 100000; size += 20000) { inital test
        for (int size = 20000; size <= 1000000; size += 40000) {
            sheet.createRow(index); // where we will store the size of the array as the first cell in the row, and the rest of the cells will be the times for each recursion limit
            Row sizeData = sheet.getRow(index++); // get the row we just created and increment index for the next row
            Cell firstCell = sizeData.createCell(0);
            firstCell.setCellValue(size); // set the first cell of the row to be the size of the array
            Integer[] randomArray = createRandomArray(size);
            for (int lim = 2; lim < 300; lim += 2){
                if (!FHsort.setRecursionLimit(lim)){
                    continue;
                }
                long totalTime = 0; // total so that we can compute average of three times later
                for (int trail = 0; trail < 3; trail++){ // run three times to filter out noise in the data, and take the average time for this recursion limit and size
                    Integer[] a = duplicateArray(randomArray);
                    startTime = System.nanoTime();
                    FHsort.heapSort(a);
                    totalTime += System.nanoTime() - startTime;
                }
                estimatedTime = totalTime / 3;
                Cell currLim = sizeData.createCell(lim/2); // divide by 2 to account for step of 2 in lim, starts at 1 to account for size
                currLim.setCellValue(estimatedTime); // set the cell value to be the estimated time for this recursion limit and size
            }
            System.out.println("Finished size: " + size);
        }
        try (FileOutputStream fileOut = new FileOutputStream("My2ndTimesExported.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Excel file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { workbook.close(); } catch (IOException e) { e.printStackTrace(); }
        }
    }

    /**
     * Helper method to create an array of random integers of the specified size. To be sorted by FHsort.FHsort.
     * @param size of the array to be created
     * @return filled array of random integers
     */
    private static Integer[] createRandomArray(int size){
        Integer[] a = new Integer[size];
        for (int i = 0; i < size; i++)
            a[i] = (int)(Math.random() * size * 2); // double the size of array to decrease the chance of duplicates
        return a;
    }

    /**
     * Helper method to duplicate an array of integers. To be sorted between trails.
     * @param a, the original array to be duplicated
     * @return a new array with the same values as the original array, but a different reference in memory
     */
    private static Integer[] duplicateArray(Integer[] a){
        Integer[] dup = new Integer[a.length];
        for (int i = 0; i < a.length; i++)
            dup[i] = a[i];
        return dup;
    }
}