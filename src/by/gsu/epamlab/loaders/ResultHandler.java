package by.gsu.epamlab.loaders;

import by.gsu.epamlab.buffer.Buffer;
import by.gsu.epamlab.formatters.DateFormatter;
import by.gsu.epamlab.formatters.MarkFormatter;
import by.gsu.epamlab.results.DecimalResult;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


/**
 * jse2
 *
 * @author Dzianis Kanavalau on 15.01.2015.
 * @version 1.0
 */
public class ResultHandler extends DefaultHandler {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private Buffer buffer;
    private boolean isStartElement = false;

    private String element;
    private String login;

    public ResultHandler() {
        super();
        buffer = new Buffer();
    }

    public Buffer getBuffer() {
        return buffer;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        isStartElement = true;
        element = localName.toUpperCase();

        if (element.equals(Tags.TEST.name())) {
            String test = attributes.getValue(Tags.NAME.name().toLowerCase());
            String date = attributes.getValue(Tags.DATE.name().toLowerCase());
            String mark = attributes.getValue(Tags.MARK.name().toLowerCase());
            buffer.setResult(new DecimalResult(login, test, DateFormatter.format(date, DATE_PATTERN),
                    MarkFormatter.formatDec(mark)));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        isStartElement = false;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (element.equals(Tags.LOGIN.name()) && isStartElement) {
            login = new String(ch, start, length).trim();
        }
    }

    private static enum Tags {
        LOGIN,
        TEST,
        NAME,
        DATE,
        MARK
    }
}
