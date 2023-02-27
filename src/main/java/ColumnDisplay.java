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

    }
}
