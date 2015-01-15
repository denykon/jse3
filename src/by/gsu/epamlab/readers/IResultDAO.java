package by.gsu.epamlab.readers;

import by.gsu.epamlab.results.Result;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 15.01.2015.
 * @version 1.0
 */
public interface IResultDAO extends Runnable {
    Result nextResult();

    boolean hasResult();

    void closeReader();
}
