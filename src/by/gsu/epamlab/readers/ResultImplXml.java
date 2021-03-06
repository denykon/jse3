package by.gsu.epamlab.readers;

import by.gsu.epamlab.buffer.Buffer;
import by.gsu.epamlab.loaders.ResultHandler;
import by.gsu.epamlab.results.Result;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 15.01.2015.
 * @version 1.0
 */
public class ResultImplXml implements IResultDAO {

    private static final String XML_PATH = "src/by/gsu/epamlab/resources/";
    private static final String XML_EXT = ".xml";
    private String fileName;
    private XMLReader xmlReader;
    private ResultHandler resultHandler;
    private Buffer buffer;

    public ResultImplXml(String fileName) {
        this.fileName = fileName;
        try {
            xmlReader = XMLReaderFactory.createXMLReader();
        } catch (SAXException e) {
            throw new RuntimeException();
        }
        resultHandler = new ResultHandler();
        buffer = resultHandler.getBuffer();
        xmlReader.setContentHandler(resultHandler);
    }

    @Override
    public Result nextResult() {
        return buffer.getResult();
    }

    @Override
    public boolean hasResult() {
        return buffer.isEmpty();
    }

    @Override
    public void closeReader() {
    }

    @Override
    public void run() {
        try {
            xmlReader.parse(XML_PATH + fileName + XML_EXT);
        } catch (SAXException e) {
            throw new RuntimeException("XML parse error." + e);
        } catch (IOException e) {
            throw new RuntimeException("File " + fileName + XML_EXT + " not found.");
        }
    }
}
