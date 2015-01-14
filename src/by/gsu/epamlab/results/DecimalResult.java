package by.gsu.epamlab.results;

import java.sql.Date;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 10.01.2015.
 * @version 1.0
 */
public class DecimalResult extends Result {

    public DecimalResult(String login, String test, Date date, int mark) {
        super(login, test, date, mark);
    }

    @Override
    protected String getStringMark() {
        return String.valueOf(super.getMark() / 10) + "." + String.valueOf(super.getMark() % 10);
    }
}
