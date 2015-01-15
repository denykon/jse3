package by.gsu.epamlab.formatters;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 15.01.2015.
 * @version 1.0
 */

public class MarkFormatter {
    public static int formatDec(String mark) {
        return (int) (Double.parseDouble(mark) * 10);
    }
}
