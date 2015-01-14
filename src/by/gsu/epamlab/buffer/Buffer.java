package by.gsu.epamlab.buffer;

import by.gsu.epamlab.results.Result;

/**
 * jse3
 *
 * @author Dzianis Kanavalau on 14.01.2015.
 * @version v1.0
 */
public class Buffer {
    Result bufferResult;

    public Buffer(Result bufferResult) {
        this.bufferResult = bufferResult;
    }

    public Result getBufferResult() {
        return bufferResult;
    }

    public void setBufferResult(Result bufferResult) {
        this.bufferResult = bufferResult;
    }


}
