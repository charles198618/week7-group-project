import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ColumnDisplayTest {
    ColumnDisplay columnDisplay;

    @BeforeEach
    void setUp() {
        columnDisplay = new ColumnDisplay();
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

