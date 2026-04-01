//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Path filePath = Path.of("output.csv");
    int[] sizes = {20000, 100000, 500000, 2000000};

    try  {
        FileWriter w = new FileWriter("output.csv");
        w.append("array size, random_num_1, random_num_2, ...random_num_size_of_array\n");
        for (int size : sizes) {
            w.append("["+size+"], ");
            for (int j = 0; j < size; j++) {
                w.append("" + (int) (Math.random() * size * 2) + ((j != size - 1)? ", ": ""));
            }
            w.append("\n");
        }
        w.close();
    } catch (IOException e) {
        System.out.println("Error writing to CSV file: " + e.getMessage());
        e.printStackTrace();
    }

}
