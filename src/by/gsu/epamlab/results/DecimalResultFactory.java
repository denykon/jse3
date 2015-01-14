package by.gsu.epamlab.results;

import by.gsu.epamlab.readers.IResultDAO;
import by.gsu.epamlab.readers.ResultImplXml;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.sql.Date;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 10.01.2015.
 * @version 1.0
 */
public class DecimalResultFactory extends ResultFactory {

    private static final String FILE_NAME = "results";

    @Override
    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new DecimalResult(login, test, date, mark);
    }

    @Override
    public IResultDAO getResultDaoFromFactory(ResultFactory factory) throws IOException, SAXException {
        return new ResultImplXml(FILE_NAME);
    }

    @Override
    public int getDivisor() {
        return 10;
    }
}
