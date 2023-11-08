package net.genemis.tools.csv;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


public class CsvTimeSeriesSupplementerTest {

    @Test
    public void testParseDate() {
        ZonedDateTime expected = ZonedDateTime.of(2023, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime parsed = CsvTimeSeriesSupplementer.parseDate("2023-01-01");
        Assertions.assertEquals(expected, parsed);
    }

    @Test
    public void testSupplementLines_NeighbourDates() {

        String[] previousLine = new String[]{"2023-01-01", "1"};
        String[] currentLine = new String[]{"2023-01-02", "2"};

        String[] expectedLine = new String[]{"2023-01-02", "2", "true"};

        List<String[]> supplementedLines = CsvTimeSeriesSupplementer.supplementLines(previousLine, currentLine);

        Assertions.assertEquals(1, supplementedLines.size());
        Assertions.assertArrayEquals(expectedLine, supplementedLines.get(0));
    }

    @Test
    public void testSupplementLines_MissingOneDate() {

        String[] previousLine = new String[]{"2023-01-01", "1"};
        String[] currentLine = new String[]{"2023-01-03", "3"};

        String[] expectedLine1 = new String[]{"2023-01-02", "1", "false"};
        String[] expectedLine2 = new String[]{"2023-01-03", "3", "true"};

        List<String[]> supplementedLines = CsvTimeSeriesSupplementer.supplementLines(previousLine, currentLine);

        Assertions.assertEquals(2, supplementedLines.size());
        Assertions.assertArrayEquals(expectedLine1, supplementedLines.get(0));
        Assertions.assertArrayEquals(expectedLine2, supplementedLines.get(1));
    }

    @Test
    public void testSupplementLines_MissingMultipleDates() {
        String[] previousLine = new String[]{"2023-01-01", "1"};
        String[] currentLine = new String[]{"2023-01-05", "5"};

        String[] expectedLine1 = new String[]{"2023-01-02", "1", "false"};
        String[] expectedLine2 = new String[]{"2023-01-03", "1", "false"};
        String[] expectedLine3 = new String[]{"2023-01-04", "1", "false"};
        String[] expectedLine4 = new String[]{"2023-01-05", "5", "true"};

        List<String[]> supplementedLines = CsvTimeSeriesSupplementer.supplementLines(previousLine, currentLine);

        Assertions.assertEquals(4, supplementedLines.size());
        Assertions.assertArrayEquals(expectedLine1, supplementedLines.get(0));
        Assertions.assertArrayEquals(expectedLine2, supplementedLines.get(1));
        Assertions.assertArrayEquals(expectedLine3, supplementedLines.get(2));
        Assertions.assertArrayEquals(expectedLine4, supplementedLines.get(3));
    }

    @Test
    public void testSupplement() throws Exception {

        CsvTimeSeriesSupplementer.supplement("src/test/resources/csv/input.csv");
        Assertions.assertTrue(new File("src/test/resources/csv/input.csv.supplemented.csv").exists());

        try (Reader reader = new FileReader("src/test/resources/csv/input.csv.supplemented.csv");
                CSVReader csvReader = new CSVReader(reader)) {
            List<String[]> lines = csvReader.readAll();
            Assertions.assertEquals(7, lines.size());
            Assertions.assertArrayEquals(new String[]{"2023-01-01","1","true"}, lines.get(0));
            Assertions.assertArrayEquals(new String[]{"2023-01-02","2","true"}, lines.get(1));
            Assertions.assertArrayEquals(new String[]{"2023-01-03","2","false"}, lines.get(2));
            Assertions.assertArrayEquals(new String[]{"2023-01-04","2","false"}, lines.get(3));
            Assertions.assertArrayEquals(new String[]{"2023-01-05","2","false"}, lines.get(4));
            Assertions.assertArrayEquals(new String[]{"2023-01-06","6","true"}, lines.get(5));
            Assertions.assertArrayEquals(new String[]{"2023-01-07","7","true"}, lines.get(6));
        }
    }
}
