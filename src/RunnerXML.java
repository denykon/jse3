import by.gsu.epamlab.process.RunnerLogic;
import by.gsu.epamlab.results.DecimalResultFactory;
import by.gsu.epamlab.results.ResultFactory;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 10.01.2015.
 * @version 1.0
 */
public class RunnerXML {

    public static void main(String[] args) {
        try {
            ResultFactory resultFactory = new DecimalResultFactory();
            RunnerLogic.run(resultFactory);
        } catch (SQLException | SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("File is not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
