import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.stream.*;

public class ColumnDisplay {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // number of columns to display (max 6)
        int numCols;
        if (lines.size() <= 5) numCols = 1;
        else if (lines.size() <= 10) numCols = 2;
        else if (lines.size() <= 50) numCols = ((lines.size() - 1) / 10) + 1;
        else numCols = 6;

        int numRows = (int) Math.ceil((double) lines.size() / numCols);

        // translator: 'lines' broken to a stream, feeding translator line by line
        // outputs List<List<Integer, String>> of line and numerical equivalent in 'combinedList'
        List<List<Object>> combinedList = lines.stream()
                .map(ColumnDisplay::translator)
                .collect(Collectors.toList());

        // sort combinedList by numerical equivalent
        quickSort(combinedList, 0, combinedList.size() - 1);

        // set finalLines equal to the sorted list of words and isolate from numerical equivalents
        List<String> finalLines = combinedList.stream()
                .map(combo -> combo.stream()
                        .reduce((a, b) -> b)
                        .orElse(List.of("")))
                .map(Object::toString)
                .collect(Collectors.toList());

        // sets up columns in proper positions
        List<List<String>> columns = IntStream.range(0, numCols)
                .mapToObj(i -> finalLines.subList(i * numRows, Math.min((i + 1) * numRows, finalLines.size())))
                .collect(Collectors.toList());

        // prints columns
        System.out.println(printColumns(numRows, columns));
    }

    // translates a line to its numerical equivalent. Outputs List<numerical equivalent, line>
    public static List<Object> translator(String line) {
        HashMap<String, Integer> map = new HashMap<>(){{
            put("Zero", 0);
            put("One", 1);
            put("Two", 2);
            put("Three", 3);
            put("Four", 4);
            put("Five", 5);
            put("Six", 6);
            put("Seven", 7);
            put("Eight", 8);
            put("Nine", 9);
            put("Ten", 10);
            put("Eleven", 11);
            put("Twelve", 12);
            put("Thirteen", 13);
            put("Fourteen", 14);
            put("Fifteen", 15);
            put("Sixteen", 16);
            put("Seventeen", 17);
            put("Eighteen", 18);
            put("Nineteen", 19);
            put("Twenty", 20);
            put("Thirty", 30);
            put("Forty", 40);
            put("Fifty", 50);
            put("Sixty", 60);
            put("Seventy", 70);
            put("Eighty", 80);
            put("Ninety", 90);
            put("Hundred", 100);
            put("Thousand", 1000);
            put("Million", 1000000);
            put("Billion", 1000000000);
        }};

        // store each word in the line in an arrayList called lineList
        ArrayList<String> lineList = new ArrayList<>(Arrays.asList(line.split(" ")));
        ArrayList<Integer> numList = new ArrayList<>();

        // if the lineList contains a negative sign without a space like "-5", store the negative separately
        if ((lineList.get(0).length() > 1 && lineList.get(0).contains("-"))) {
            lineList.set(0, lineList.get(0).substring(1));
            lineList.add(0, "-");
        }

        // if the lineList contains the word "negative", replace it with "-"
        if (lineList.get(0).equalsIgnoreCase("negative")) {
            lineList.set(0, "-");
        }

        // This code block converts a line to its numerical equivalent, "num"
        int num = 0; // num equivalent of line

        // keeps track of num since last "thousand", "million", and "billon".
        // handles situations where there is a hundred multiplied by "thousand", "million", or "billion", for example 502 thousand, 610 million
        int subNum = 0;

        for (int i = 0; i < lineList.size(); i++) {
            // provide a value of 0 to any "ands" or "-" found in the line
            if (lineList.get(i).equalsIgnoreCase("and") || lineList.get(i).equals("-")) {
                numList.add(0);
                continue;
            }

            // find the numeric value of a word from the hashmap.
            if (map.containsKey(lineList.get(i))) numList.add(map.get(lineList.get(i)));

            // all words' values will be added to num. If the program comes across a hundred, a thousand, a million, or a billion,
            // it will be multiplied by its predecessor instead. For example five thousand is 5 * 1000
            if (numList.get(i) == 100 || numList.get(i) == 1000 || numList.get(i) == 1000000 || numList.get(i) == 1000000000) {
                num -= numList.get(i-1);
                num += numList.get(i) * numList.get(i - 1);

                subNum -= numList.get(i-1);

                // if element is a hundred, multiply previous element by 100 and add it to subNum just like with num
                if (numList.get(i) == 100) {
                    subNum += 100 * numList.get(i-1);
                }

                // if element is a thousand, a million, or a billion, undo previous addition and add (subNum * thousand/million/billion) to num instead. Reset subNum
                if (numList.get(i) != 100) {
                    num = (num - subNum) + (subNum * numList.get(i));
                    subNum = 0;
                }
            } else {
                num += numList.get(i);
                subNum += numList.get(i);
            }
        }

        // if the word was supposed to be negative, convert the num to be negative
        if (lineList.contains("-")) {
            num -= 2 * num;
        }

        // return list of the line and its numerical equivalent
        return new ArrayList<>(Arrays.asList(num, line));
    }

    public static List<List<Object>> quickSort(List<List<Object>> combinedList, int low, int high)
    {
        // list not empty nor size of 1
        if (low < high) {
            // get the pivot element from the end of the list
            int pivot = (int) combinedList.get(high).get(0);

            // make left < pivot and right > pivot
            int i = low - 1;
            for (int j=low; j < high; j++) {
                // check if current element is less than the pivot
                if ((int) combinedList.get(j).get(0) < pivot) {
                    i++;
                    // swap elements at i and j
                    swap(combinedList, i, j);
                }
            }
            swap(combinedList, i + 1, high);

            pivot = i + 1;

            // do same operation as above recursively to sort two sub arrays
            quickSort(combinedList, low, pivot - 1);
            quickSort(combinedList, pivot + 1, high);
        }
        return combinedList;
    }

    public static void swap (List<List<Object>> combinedList, int i, int j)
    {
        List<Object> temp = combinedList.get(i);
        combinedList.set(i, combinedList.get(j));
        combinedList.set(j, temp);
    }

    // prints columns
    public static String printColumns(int numRows, List<List<String>> columns) {
        return IntStream.range(0, numRows)
                .mapToObj(rowIndex -> getRow(columns, rowIndex) + "\n")
                .collect(Collectors.joining());
    }

    // gets each row
    public static String getRow(List<List<String>> columns, int rowIndex){
        return columns.stream()
                .map(column -> {
                    String element = (rowIndex < column.size()) ? column.get(rowIndex) : "";
                    return String.format("%-30s", element);
                })
                .collect(Collectors.joining());
    }
}
