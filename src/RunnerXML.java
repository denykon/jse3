import by.gsu.epamlab.process.RunnerLogic;
import by.gsu.epamlab.results.DecimalResultFactory;
import by.gsu.epamlab.results.ResultFactory;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 15.01.2015.
 * @version 1.0
 */
public class RunnerXML {

    public static void main(String[] args) {
        ResultFactory resultFactory = new DecimalResultFactory();
        RunnerLogic.go(resultFactory);
    }
}
