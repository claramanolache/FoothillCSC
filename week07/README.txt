project folder:
claramanolache-cs1c-project07/

Brief description of submitted files:
resources/FirstTimesExported.xlsx
    - an Excel file containing a dataset of times when I test with smaller sizes to see if formating was proper

resources/FirstGraph.png
  - line graph created by excel using data from FirstTimesExported.xlsx.

resources/FirstGraphComments.txt
    - a text file containing comments and observations about the performance of the FHsort algorithm based on the data
     collected from testing. This file includes insights into the time measurements and any patterns noticed during the testing process.

resources/2ndGraph.png
    - line graph created by excel using data from My2ndTimeExported.xlsx, with larger sizes to see if the pattern of the graph is similar to the first one.

resources/Annotated2ndGraph.png
 - drew trends and optimal regions on the graph to show where the algorithm performs best and where it struggles, based on the data collected from My2ndTimeExported.xlsx.

 resources/2ndGraphComments.txt
    - a conclusion txt file based on annotated 2nd graph

resources/My2ndTimeExported.xlsx
    - an Excel file containing a dataset of times when I test with larger sizes to see if the pattern of the graph is similar to the first one.

FHsort07/FHsort07.java
     - a Java implementation of the FHsort algorithm, which is a sorting algorithm that combines elements of
      quicksort and heapsort to efficiently sort large datasets. This file contains the main logic for the FHsort
      algorithm, including methods for partitioning, heapifying, and sorting arrays. From foothill recources folder
      tested in my code.

FHsort07/FHsortRecursionLimit.java
    - main class which contains the main method to test the FHsort algorithm. It includes code to generate random
    arrays of integers, call the FHsort method to sort them, and print the results. This file serves as a test harness
    for the FHsort implementation in FHsort07.java. From foothill recources folder

FHsort07/TimeConverted.java
    - a utility class that provides methods for converting time measurements into different units (e.g., milliseconds to seconds).
     This class is used in FHsortRecursionLimit.java to display the time taken by the FHsort algorithm in a more readable format.
     From foothill project 02, provided by professor.
