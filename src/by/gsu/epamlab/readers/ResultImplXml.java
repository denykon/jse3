package by.gsu.epamlab.readers;

import by.gsu.epamlab.loaders.ResultHandler;
import by.gsu.epamlab.results.Result;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Iterator;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 10.01.2015.
 * @version 1.0
 */
public class ResultImplXml implements IResultDAO {

    private static final String XML_PATH = "src/by/gsu/epamlab/resources/";
    private static final String XML_EXT = ".xml";
    private Iterator<Result> iterator;

    public ResultImplXml(String fileName) throws IOException, SAXException {
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        ResultHandler resultHandler = new ResultHandler();
        xmlReader.setContentHandler(resultHandler);
        xmlReader.parse(XML_PATH + fileName + XML_EXT);
        this.iterator = resultHandler.getList().listIterator();
    }

    @Override
    public Result nextResult() {
        return iterator.next();
    }

    @Override
    public boolean hasResult() {
        return iterator.hasNext();
    }

    @Override
    public void closeReader() {
    }
}
