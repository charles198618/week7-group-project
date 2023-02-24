
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
    }
}
