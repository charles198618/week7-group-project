
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
        List<String> finalLines = lines;
        List<List<String>> columns = IntStream.range(0, numCols)
                .mapToObj(i -> finalLines.subList(i * numRows, Math.min((i + 1) * numRows, finalLines.size())))
                .collect(Collectors.toList());

        System.out.println(printColumns(numRows, columns));
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
                    return String.format("%-15s", element);
                })
                .collect(Collectors.joining());
    }
}
