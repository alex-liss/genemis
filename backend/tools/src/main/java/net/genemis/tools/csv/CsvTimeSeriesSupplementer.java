package net.genemis.tools.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Time series supplementer.
 * <br>
 * <br>
 * Original CSV file contains only data from workdays.
 * This tool adds missing data from weekends and holidays: add the date and use previous day's data.
 * This simplifies the filtering of the data, e.g. show values from every first day of the month.
 * To distinguish between workdays and weekends/holidays, the supplemented data is marked with a flag.
 *
 */
public class CsvTimeSeriesSupplementer {

    private static final Logger log = LoggerFactory.getLogger(CsvTimeSeriesSupplementer.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar CsvTimeSeriesSupplementer.jar <input file name>");
            System.exit(1);
        }

        String inputFileName = args[0];
        try
        {
            supplement(inputFileName);
        }
        catch (Exception e)
        {
            log.error("Error supplementing CSV file", e);
        }
    }

    /**
     * Supplements the input CSV file with missing dates and flags and writes the result to a new file.
     *
     * @param inputFileName name of the input CSV file
     * @throws Exception exception
     */
    static void supplement(String inputFileName) throws Exception {

        File inputFile = new File(inputFileName);
        if (!inputFile.exists()) {
            throw new Exception("Input file does not exist: " + inputFileName);
        }
        File outputFile = new File(inputFileName + ".supplemented.csv");
        if (outputFile.exists()) {
            outputFile.delete();
        }

        try (Reader reader = new FileReader(inputFile);
             Writer writer = new FileWriter(outputFile);
             CSVReader csvReader = new CSVReader(reader);
             CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR,
                     CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END)) {

            String[] previousLine = null;
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                if (previousLine != null) {
                    List<String[]> supplementedLines = supplementLines(previousLine, nextLine);
                    for (String[] s : supplementedLines) {
                        csvWriter.writeNext(s);
                    }
                } else {
                    // first line
                    String[] supplementedLine = new String[3];
                    supplementedLine[0] = nextLine[0];
                    supplementedLine[1] = nextLine[1];
                    supplementedLine[2] = "true";
                    csvWriter.writeNext(supplementedLine);
                }

                previousLine = nextLine;
            }
        }
    }

    /**
     * Supplements two CSV lines with missing data.
     * <ul>
     *     <li>If the dates in the lines differ only on one day, just add the flag to the next line, that this is working day.
     *     <li>If the dates in the lines differ on more than one day, add missing dates before the next line and add the next line at the end.
     *     Missing dates are marked as non-working days.
     * </ul>
     *
     * @param previousLine previous line
     * @param nextLine next line
     * @return list of supplemented lines together with the next line at the end
     */
    static List<String[]> supplementLines(String[] previousLine, String[] nextLine) {

        ZonedDateTime previousDate = parseDate(previousLine[0]);
        ZonedDateTime nextDate = parseDate(nextLine[0]);

        if (previousDate.plusDays(1).equals(nextDate)) {
            String[] supplementedLine = new String[3];
            // next date is the next day, only add flag
            supplementedLine[0] = nextLine[0];
            supplementedLine[1] = nextLine[1];
            supplementedLine[2] = "true";

            List<String[]> supplementedLines = new ArrayList<>();
            supplementedLines.add(supplementedLine);
            return supplementedLines;
        } else {
            String[] supplementedLine;
            // current date is not the next day, add missing dates and flags
            List<String[]> supplementedLines = new ArrayList<>();
            ZonedDateTime date = previousDate.plusDays(1);
            while (!date.equals(nextDate)) {
                supplementedLine = new String[3];
                supplementedLine[0] = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
                supplementedLine[1] = previousLine[1];
                supplementedLine[2] = "false";
                date = date.plusDays(1);
                supplementedLines.add(supplementedLine);
            }
            // add next date at the end
            supplementedLine = new String[3];
            supplementedLine[0] = nextLine[0];
            supplementedLine[1] = nextLine[1];
            supplementedLine[2] = "true";
            supplementedLines.add(supplementedLine);
            return supplementedLines;
        }

    }

    /**
     * Parses a date string in ISO format (yyyy-MM-dd) to a ZonedDateTime.
     * @param date date string
     * @return ZonedDateTime
     */
    static ZonedDateTime parseDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        return localDate.atStartOfDay(ZoneId.of("UTC"));
    }


}
