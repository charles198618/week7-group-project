import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ColumnDisplayTest {
    ColumnDisplay columnDisplay;

    @BeforeEach
    void setUp() {
        columnDisplay = new ColumnDisplay();
    }

    @Test
    void printRow() {
        // Set up test columns
        java.util.List<java.util.List<String>> columns = new ArrayList<>();
        columns.add(new ArrayList<>(java.util.List.of("One", "Two", "Three")));
        columns.add(new ArrayList<>(java.util.List.of("Four", "Five")));
        columns.add(new ArrayList<>(java.util.List.of("Six")));

        String rowOneExpected = "One            Four           Six";
        String rowTwoExpected = "Two            Five";
        String rowThreeExpected = "Three                                        ";

        // Test each row separately
        assertEquals(rowOneExpected.trim(),
                columnDisplay.getRow(columns, 0).trim());
        assertEquals(rowTwoExpected.trim(),
                columnDisplay.getRow(columns, 1).trim());
        assertEquals(rowThreeExpected.trim(),
                columnDisplay.getRow(columns, 2).trim());
    }


    @Test
    public void testMissingInputFile() {
        File file = new File("./input.txt");
        assertTrue(file.exists(), "File does not exist");
    }


    @AfterEach
    void tearDown() {
    }
}

