import by.gsu.epamlab.process.RunnerLogic;
import by.gsu.epamlab.results.HalfResultFactory;
import by.gsu.epamlab.results.ResultFactory;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 15.01.2015.
 * @version 1.0
 */
public class RunnerDecCSV {

    public static void main(String[] args) {
        ResultFactory resultFactory = new HalfResultFactory();
        RunnerLogic.go(resultFactory);
    }
}
