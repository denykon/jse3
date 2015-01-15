package by.gsu.epamlab.results;

import by.gsu.epamlab.readers.IResultDAO;
import by.gsu.epamlab.readers.ResultImplCsv;

import java.io.FileNotFoundException;
import java.sql.Date;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 10.01.2015.
 * @version 1.0
 */
public class HalfResultFactory extends ResultFactory {

    private static final String FILE_NAME = "results2";

    @Override
    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new HalfResult(login, test, date, mark);
    }

    @Override
    public IResultDAO getResultDaoFromFactory() {
        return new ResultImplCsv(FILE_NAME, this);
    }

    @Override
    public int format(String mark) {
        return (int) (Double.parseDouble(mark) * 2);
    }

    @Override
    public int getDivisor() {
        return 2;
    }
}
