import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ColumnDisplayTest {
    ColumnDisplay columnDisplay;

    @BeforeEach
    void setUp() {
        columnDisplay = new ColumnDisplay();
    }

    @Test
    void translator() {
        String s = "Zero";
        String s1 = "One";
        String s2 = "Two";
        assertEquals("[0, Zero]", ColumnDisplay.translator(s).toString());
        assertEquals("[1, One]", ColumnDisplay.translator(s1).toString());
        assertEquals("[2, Two]", ColumnDisplay.translator(s2).toString());

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

