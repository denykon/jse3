package by.gsu.epamlab.formatters;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 10.01.2015.
 * @version 1.0
 */
public class DateFormatter {
    public static Date format(String date, String pattern) {
        DateFormat formatter = new SimpleDateFormat(pattern);
        java.util.Date myDate = null;
        try {
            myDate = formatter.parse(date);
        } catch (ParseException e) {
            System.out.println("Date format error,\n");
            e.printStackTrace();
            System.exit(1);
        }
        return new java.sql.Date(myDate.getTime());
    }
}