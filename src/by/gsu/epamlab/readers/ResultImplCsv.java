package by.gsu.epamlab.readers;

import by.gsu.epamlab.buffer.Buffer;
import by.gsu.epamlab.formatters.DateFormatter;
import by.gsu.epamlab.results.Result;
import by.gsu.epamlab.results.ResultFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 15.01.2015.
 * @version 1.0
 */
public class ResultImplCsv implements IResultDAO {

    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String CSV_PATH = "src/by/gsu/epamlab/resources/";
    private static final String CSV_EXT = ".csv";
    private static final String CSV_SPLIT_BY = ";";
    private static final int LOGIN_POSITION = 0;
    private static final int TEST_POSITION = 1;
    private static final int DATE_POSITION = 2;
    private static final int MARK_POSITION = 3;

    private Scanner scanner;
    private Buffer buffer;
    private ResultFactory resultFactory;

    public ResultImplCsv(String fileName, ResultFactory resultFactory) {
        try {
            this.scanner = new Scanner(new FileReader(CSV_PATH + fileName + CSV_EXT));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File" + fileName + CSV_EXT + " not found!");
        }
        this.resultFactory = resultFactory;
        this.buffer = new Buffer();
    }

    @Override
    public Result nextResult() {
        return buffer.getResult();
    }

    @Override
    public boolean hasResult() {
        return buffer.isEmpty();
    }

    @Override
    public void closeReader() {
        if (scanner != null) {
            scanner.close();
        }
    }

    @Override
    public void run() {
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(CSV_SPLIT_BY);
            String login = line[LOGIN_POSITION];
            String test = line[TEST_POSITION];
            String date = line[DATE_POSITION];
            String mark = line[MARK_POSITION];
            buffer.setResult(resultFactory.getResultFromFactory(
                    login, test, DateFormatter.format(date, DATE_PATTERN), resultFactory.format(mark)));
        }
    }
}
