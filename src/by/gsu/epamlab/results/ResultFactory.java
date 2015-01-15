package by.gsu.epamlab.results;

import by.gsu.epamlab.readers.IResultDAO;
import by.gsu.epamlab.readers.ResultImplCsv;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 10.01.2015.
 * @version 1.0
 */
public class ResultFactory {

    private static final String FILE_NAME = "results";

    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new Result(login, test, date, mark);
    }

    public IResultDAO getResultDaoFromFactory() {
        return new ResultImplCsv(FILE_NAME, this);
    }

    public int format(String mark) {
        return Integer.parseInt(mark);
    }

    public int getDivisor() {
        return 1;
    }
}
