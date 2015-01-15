package by.gsu.epamlab.buffer;

import by.gsu.epamlab.results.Result;

/**
 * jse3
 *
 * @author Dzianis Kanavalau on 14.01.2015.
 * @version v1.0
 */
public class Buffer {
    private Result result;
    private volatile boolean empty = true;

    public Buffer() {
    }

    public boolean isEmpty() {
        return (!empty);
    }

    public synchronized Result getResult() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        empty = true;
        System.out.println("SET to DB ---> " + result);
        notifyAll();
        return result;
    }

    public synchronized void setResult(Result result) {
        while (!empty){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        empty = false;
        System.out.println("GET from file <--- " + result);
        this.result = result;
        notifyAll();
    }
}
