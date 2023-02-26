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

    }
}
