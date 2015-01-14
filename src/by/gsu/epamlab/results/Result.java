package by.gsu.epamlab.results;

import java.sql.Date;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 10.01.2015.
 * @version 1.0
 */
public class Result {

    private static final String SEPARATOR = ";";
    private String login;
    private String test;
    private Date date;
    private int mark;

    public Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public String getLogin() {
        return login;
    }

    public String getTest() {
        return test;
    }

    public Date getDate() {
        return date;
    }

    public int getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return getLogin() + SEPARATOR + getTest() + SEPARATOR + getDate() + SEPARATOR + getStringMark();
    }

    protected String getStringMark() {
        return String.valueOf(mark);
    }
}
