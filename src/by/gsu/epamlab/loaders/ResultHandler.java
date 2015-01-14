package by.gsu.epamlab.loaders;

import by.gsu.epamlab.formatters.DateFormatter;
import by.gsu.epamlab.formatters.MarkFormatter;
import by.gsu.epamlab.results.DecimalResult;
import by.gsu.epamlab.results.Result;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 10.01.2015.
 * @version 1.0
 */
public class ResultHandler extends DefaultHandler {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private List<Result> list = new ArrayList<>();
    private boolean isStartElement = false;

    private String element;
    private String login;

    public List<Result> getList() {
        return list;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        isStartElement = true;
        element = localName.toUpperCase();

        if (element.equals(Tags.TEST.name())) {
            String test = attributes.getValue(Attr.NAME.name().toLowerCase());
            String date = attributes.getValue(Attr.DATE.name().toLowerCase());
            String mark = attributes.getValue(Attr.MARK.name().toLowerCase());
            list.add(new DecimalResult(login, test, DateFormatter.format(date, DATE_PATTERN),
                    MarkFormatter.formatDec(mark)));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        isStartElement = false;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (element.equals(Tags.LOGIN.name()) && isStartElement) {
            login = new String(ch, start, length).trim();
        }
    }

    private static enum Tags {
        LOGIN,
        TEST
    }

    private static enum Attr {
        NAME,
        DATE,
        MARK
    }
}
